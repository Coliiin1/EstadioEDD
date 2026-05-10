package Archivos;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import estadio.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarPDF
{

    public void generarPDF(Reporte rep)
    {
        LocalDateTime fechaActual = (LocalDateTime) rep.getFecha();

        DateTimeFormatter formatoArchivo =
                DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String fechaFormateada = fechaActual.format(formatoArchivo);

        String nombrePDF =
                "Reporte_" + fechaFormateada + ".pdf";

        Document documento = new Document(PageSize.A4, 40, 40, 60, 40);

        try
        {
            File carpeta = new File("Reportes");

            if (!carpeta.exists())
            {
                carpeta.mkdir();
            }

            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream("Reportes/" + nombrePDF)
            );

            documento.open();

            // =========================
            // FUENTES
            // =========================
            Font titulo = new Font(
                    Font.FontFamily.HELVETICA,
                    24,
                    Font.BOLD,
                    new BaseColor(34, 45, 65)
            );

            Font subtitulo = new Font(
                    Font.FontFamily.HELVETICA,
                    12,
                    Font.NORMAL,
                    BaseColor.DARK_GRAY
            );

            Font encabezadoTabla = new Font(
                    Font.FontFamily.HELVETICA,
                    13,
                    Font.BOLD,
                    BaseColor.WHITE
            );

            Font contenidoTabla = new Font(
                    Font.FontFamily.HELVETICA,
                    12,
                    Font.NORMAL,
                    BaseColor.BLACK
            );

            Font totalFont = new Font(
                    Font.FontFamily.HELVETICA,
                    16,
                    Font.BOLD,
                    new BaseColor(0, 120, 80)
            );

            // =========================
            // TITULO
            // =========================
            Paragraph tituloPDF =
                    new Paragraph("REPORTE DE VENTAS", titulo);

            tituloPDF.setAlignment(Element.ALIGN_CENTER);

            documento.add(tituloPDF);

            documento.add(new Paragraph(" "));

            // =========================
            // INFORMACION GENERAL
            // =========================
            Paragraph info = new Paragraph(
                    "Fecha del reporte: "
                    + rep.getFecha().toString(),
                    subtitulo
            );

            info.setAlignment(Element.ALIGN_CENTER);

            documento.add(info);

            documento.add(new Paragraph(" "));

            // =========================
            // TABLA
            // =========================
            PdfPTable tabla = new PdfPTable(4);

            tabla.setWidthPercentage(100);

            tabla.setSpacingBefore(20f);

            tabla.setSpacingAfter(20f);

            tabla.setWidths(new float[]{3f, 2f, 4f, 2f});

            // =========================
            // ENCABEZADOS
            // =========================
            String[] encabezados =
            {
                "Fecha",
                "Categoría",
                "Boletos",
                "Ingreso"
            };

            for (String encabezado : encabezados)
            {
                PdfPCell cell = new PdfPCell(
                        new Phrase(encabezado, encabezadoTabla)
                );

                cell.setBackgroundColor(
                        new BaseColor(34, 45, 65)
                );

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell.setPadding(10);

                tabla.addCell(cell);
            }

            // =========================
            // DATOS
            // =========================
            tabla.addCell(
                    new PdfPCell(
                            new Phrase(
                                    rep.getFecha().toString(),
                                    contenidoTabla
                            )
                    )
            );

            tabla.addCell(
                    new PdfPCell(
                            new Phrase(
                                    rep.getCategoria().toString(),
                                    contenidoTabla
                            )
                    )
            );

            StringBuilder boletos = new StringBuilder();

            for (Boleto b : rep.getBoletos())
            {
                boletos.append(b.getIdBoleto()).append("\n");
            }

            tabla.addCell(
                    new PdfPCell(
                            new Phrase(
                                    boletos.toString(),
                                    contenidoTabla
                            )
                    )
            );

            tabla.addCell(
                    new PdfPCell(
                            new Phrase(
                                    "$" + rep.getIngreso(),
                                    contenidoTabla
                            )
                    )
            );

            documento.add(tabla);

            // =========================
            // TOTAL
            // =========================
            Paragraph total = new Paragraph(
                    "INGRESO TOTAL: $" + rep.getIngreso(),
                    totalFont
            );

            total.setAlignment(Element.ALIGN_RIGHT);

            documento.add(total);

            documento.add(new Paragraph(" "));

            // =========================
            // FOOTER
            // =========================
            Paragraph footer = new Paragraph(
                    "Documento generado automáticamente por el sistema.",
                    subtitulo
            );

            footer.setAlignment(Element.ALIGN_CENTER);

            documento.add(footer);

            documento.close();

            System.out.println("PDF generado correctamente");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}