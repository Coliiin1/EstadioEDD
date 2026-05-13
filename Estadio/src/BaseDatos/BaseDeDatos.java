package BaseDatos;
import java.sql.*;
import javax.swing.JOptionPane;

import estadio.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
/**
 *
 * @author danie
 */
public class BaseDeDatos {
    String url = "jdbc:mariadb://localhost:3306/estadio";
    String usuario = "root";
    String clave = "ProyEDD'2026.";
    Connection conexion;
    
    public void IniciarConexion(){
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa a MariaDB");
            // Operaciones con la base de datos...
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error "+e.getMessage()+" al intentar abrir la base de datos");
        }
    }
    
    
    
    public void CerrarConexion(){
        try {
        conexion.close();
            System.out.println("Base cerrada");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error "+e.getMessage()+" al intentar cerrar la base de datos");
        }
    }
    
    public void GuardarAsientos(Evento evento) throws SQLException{
        int FILAS = evento.estadio.getFILAS();
        int COLUMNAS = evento.estadio.getCOLUMNAS();
        for (int f = 0; f < FILAS; f++) {

            for (int c = 0; c < COLUMNAS; c++) {

                Asientos a = evento.estadio.getMatriz()[f][c];

                if(a == null){
                    continue;
                }

                PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO asientos(fila,columna,categoria,estado) VALUES(?,?,?,?)"
                );

                ps.setInt(1, f);
                ps.setInt(2, c);
                ps.setString(3, a.getCategoria().toString());
                ps.setString(4, a.getEstado().toString());

                ps.executeUpdate();
            }
        }
    }
    
    public void RecuperarAsientos(Evento evento){
        Statement st;
        ResultSet rs;
        
        try{
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM asientos");
            
            while(rs.next()){
                int fila = rs.getInt("fila");
                int columna = rs.getInt("columna");

                evento.estadio.getMatriz()[fila][columna] = new Asientos(fila,columna);

                evento.estadio.getMatriz()[fila][columna].setEstado(
                    EstadoAsientos.valueOf(rs.getString("estado"))
                );

                evento.estadio.getMatriz()[fila][columna].setCategoria(
                    Categoria.valueOf(rs.getString("categoria"))
                );
            }
        } catch(SQLException e){
            System.out.println("Nosepudo :p");
        }
    }
    
    public void ActualizarAsientos(Evento evento) throws SQLException{
        int FILAS = evento.estadio.getFILAS();
        int COLUMNAS = evento.estadio.getCOLUMNAS();
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                Asientos asiento = evento.estadio.getMatriz()[f][c];

                if(asiento == null){
                    continue;
                }
                PreparedStatement ps =
                conexion.prepareStatement(

                    """
                    UPDATE asientos
                    SET categoria = ?,
                        estado = ?
                    WHERE fila = ?
                    AND columna = ?
                    """
                );

                ps.setString(
                    1,
                    asiento.getCategoria().name()
                );
                ps.setString(
                    2,
                    asiento.getEstado().name()
                );
                ps.setInt(
                    3,
                    asiento.getFila()
                );
                ps.setInt(
                    4,
                    asiento.getColumna()
                );
                ps.executeUpdate();
            }
        }
    }
    
    
    public void GuardarReporte(Reporte reporte) throws SQLException{
        PreparedStatement ps = conexion.prepareStatement(
               """
               INSERT INTO reportes(
                   fecha,
                   num_Boletos,
                   categoria,
                   ingreso
               )
               VALUES(?,?,?,?)
               """,

               Statement.RETURN_GENERATED_KEYS
           );
        ps.setObject(1, reporte.getFecha());
        ps.setInt(2, reporte.getNumero_boletos());
        ps.setString(3, reporte.getCategoria().name());
        ps.setDouble(4, reporte.getIngreso());
        ps.executeUpdate();
        // ==========================
        // RECUPERAR ID GENERADO
        // ==========================
        ResultSet rs = ps.getGeneratedKeys();
        int idVentaGenerado = 0;
        if(rs.next()){
            idVentaGenerado = rs.getInt(1);
        }
        // ==========================
        // GUARDAR BOLETOS
        // ==========================

        for (Boleto boleto : reporte.getBoletos()) {
            PreparedStatement ps2 = conexion.prepareStatement(
                """
                INSERT INTO boletos_vendidos(
                    id_boleto,
                    categoria,
                    fila,
                    columna,
                    estado,
                    precio,
                    id_venta
                )
                VALUES(?,?,?,?,?,?,?)
                """
            );
            ps2.setString(1, boleto.getIdBoleto());
            ps2.setString(2, boleto.getCategoria().name());
            ps2.setInt(3, boleto.getNumeroAsiento().getFila());
            ps2.setInt(4, boleto.getNumeroAsiento().getColumna());
            ps2.setString(5, boleto.getEstado().name());
            ps2.setDouble(6, boleto.getPrecio());
               // ID AUTOGENERADO
            ps2.setInt(7, idVentaGenerado);
            ps2.executeUpdate();
        }
    }
    
    public void recuperarCola(Evento evento){
         String sqlReportes = "SELECT * FROM reportes";
        String sqlBoletos = "SELECT * FROM boletos_vendidos WHERE id_Venta = ?";

        try
        {
            Statement stReportes = conexion.createStatement();
            ResultSet rsReportes = stReportes.executeQuery(sqlReportes);

            while (rsReportes.next())
            {
                int idVenta = rsReportes.getInt("id_Venta");

                PreparedStatement psBoletos = conexion.prepareStatement(sqlBoletos);
                psBoletos.setInt(1, idVenta);

                ResultSet rsBoletos = psBoletos.executeQuery();

                LinkedList<Boleto> listaTemp = new LinkedList<>();

                while (rsBoletos.next())
                {
                    String idBoleto = rsBoletos.getString("id_Boleto");
                    Categoria categoria = Categoria.valueOf(
                            rsBoletos.getString("categoria")
                    );
                    int fila = rsBoletos.getInt("fila");
                    int columna = rsBoletos.getInt("columna");
                    EstadoAsientos estado = EstadoAsientos.valueOf(
                            rsBoletos.getString("estado")
                    );
                    double precio = rsBoletos.getDouble("precio");


                    Boleto boleto = new Boleto(idBoleto, categoria, fila, columna, estado, precio);

                    listaTemp.add(boleto);
                }
                LocalDateTime fecha = rsReportes.getTimestamp("fecha").toLocalDateTime();
                Reporte rep = new Reporte(
                        idVenta,
                        fecha,
                        listaTemp,
                        rsReportes.getInt("num_Boletos"),
                        Categoria.valueOf(
                            rsReportes.getString("categoria")
                ));

                evento.colaReportes.add(rep);

                rsBoletos.close();
                psBoletos.close();
            }

            rsReportes.close();
            stReportes.close();

        }
        catch (SQLException e)
        {
            System.out.println("No se pudo recuperar la cola");
            e.printStackTrace();
        }
    }
    
    public void GuardarEvento(Evento evento)throws SQLException{
        PreparedStatement ps =
            conexion.prepareStatement(
                """
                INSERT INTO evento(
                    Nombre,
                    Imagen,
                    Fecha,
                    Descripcion
                )
                VALUES(?,?,?,?)
                """
            );
        ps.setString(1, evento.nombreEvento);
        ps.setString(2, evento.rutaImg);
        ps.setObject(3, evento.Fecha);
        ps.setString(4, evento.Descripcion);    
        ps.executeUpdate();
    }
    
    public void RecuperarEvento(Evento evento){
        Statement st;
        ResultSet rs;
        
        try{
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM evento");
            
            while(rs.next()){
                evento.nombreEvento = rs.getString("Nombre");
                evento.rutaImg = rs.getString("Imagen");
                evento.Descripcion = rs.getString("Descripcion");
                evento.Fecha = rs.getDate("Fecha");
            }
        } catch(SQLException e){
            System.out.println("Nosepudo :p");
        }
    }
    
    public void ActualizarEvento(Evento evento) throws SQLException{
        PreparedStatement ps =
        conexion.prepareStatement(

            """
            UPDATE evento
            SET Nombre = ?,
                Imagen = ?,
                Descripcion = ?,
                Fecha = ?
            WHERE Id_Evento = 1
            """
        );

        ps.setString(
            1,
            evento.nombreEvento
        );
        ps.setString(
            2,
            evento.rutaImg
        );
        ps.setString(
            3,
            evento.Descripcion
        );
        ps.setObject(
            4,
            evento.Fecha
        );
        ps.executeUpdate();
    }
    
    public void insertarCategorias(Categoria cat) throws SQLException{
        PreparedStatement ps =
            conexion.prepareStatement(
                """
                INSERT INTO categorias(
                    categoria,
                    precio
                )
                VALUES(?,?)
                """
            );
        ps.setString(1, cat.name());
        ps.setDouble(2, cat.getPrecio()); 
        ps.executeUpdate();
    }
    
    public void RecuperarCategorias(Categoria cat){
        Statement st;
        ResultSet rs;
        
        try{
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM categorias");
            
            while(rs.next()){
                
                cat.setPrecio(rs.getDouble("precio")); 
                
                
            }
        } catch(SQLException e){
            System.out.println("Nosepudo :p");
        }
    }
    
    public void ActualizarCategorias(Categoria cat) throws SQLException{
        PreparedStatement ps =
        conexion.prepareStatement(

            """
            UPDATE categorias
            SET precio = ?
            WHERE categoria = ?
            """
        );

        ps.setDouble(
            1,
            cat.getPrecio()
            
        );
        ps.setString(2, cat.name());
        ps.executeUpdate();
    }
    
    public static void main(String args[]){
        BaseDeDatos baseDatos = new BaseDeDatos();
        baseDatos.IniciarConexion();
        baseDatos.CerrarConexion();
    }
}

