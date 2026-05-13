package Main;
import GUI.*;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        MenuPrincipal mainPrincipal = new MenuPrincipal();
        mainPrincipal.ContenedorPrincipal();
        mainPrincipal.setVisible(true);
    }
}