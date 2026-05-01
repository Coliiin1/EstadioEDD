/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

/**
 *
 * @author Alberto
 */
public class GeneradorID
{

    char letraUno;
    char letraDos;

    public GeneradorID()
    {
    }

    /**
     * Para que empice en AA
     */
    public GeneradorID(char letraUno, char letraDos)
    {
        this.letraUno = 'A';
        this.letraDos = 'A';
    }

    public GeneradorID(String prefijo)
    {
        if (prefijo == null || prefijo.length() != 2)
        {
            throw new IllegalArgumentException("El prefijo debe tener 2 letras.");
        }
        this.letraUno = Character.toUpperCase(prefijo.charAt(0));
        this.letraDos = Character.toUpperCase(prefijo.charAt(1));
    }
    
    public String getPrefijoActual() {
        return "" + letraUno + letraDos;
    }

    /**
     * Genera ID: "AA37" (prefijo + fila + columna)
     * como habia comentado Faxz para no complicarnosla tanto
     */
    public String generarId(int fila, int columna) {
        return getPrefijoActual() + fila + columna;
    }

    // Avanza: AA → AB → ... → AZ → BA → BB → ... → ZZ
    public void avanzarPrefijo() {
        if (letraDos < 'Z') {
            letraDos++;
        } else if (letraUno < 'Z') {
            letraUno++;
            letraDos = 'A';
        }
    }
}
