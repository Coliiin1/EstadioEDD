/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadio;

/**
 *
 * @author Alberto
 */
/**
 *Se coloco la clase como "enum" ya que es unt ipo de dato especial utilizado para difinir 
 * las colecciones de las constantes inmutables que en este caso serian las categorias de
 * los boletos
 */
public enum Categoria
{

    VIP(1500.0,"VIP"),
    GENERAL(300.0,"GENERAL"),
    PREFERENCIAL(800.0,"PREFERENCIAL");
    private String id;
    
    private double precio;

    private Categoria(double precio, String id)
    {
        this.precio = precio;
        this.id = id;
    }

    /**
     * @return the precio
     */
    public double getPrecio()
    {
        return precio;
    }

    /**
     * @param nuevoPrecio the precio to set
     * precio puede cambiar pero la categoria se queda como inmutable
     */
    public void setPrecio(double nuevoPrecio)
    {
        if (nuevoPrecio<=0)
        {
            throw new IllegalArgumentException("El precio debe de ser mayor a 0.");
        }
        this.precio = nuevoPrecio;
    }
    
    @Override
    public String toString() {
        return name();
    }
}
