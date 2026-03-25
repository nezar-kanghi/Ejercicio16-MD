import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String url ="jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String password = "ribera";

        try(Connection conn = DriverManager.getConnection(url, usuario, password)){

            System.out.println("Marca la id del departamento que quieres buscar");
            int buscar = sc.nextInt();

            String sql = "SELECT E_ID, E_NOMBRE, E_SALARIO, DEPARTAMENTO_ID, P_NOMBRE FROM P_EMPLEADO JOIN P_DEPARTAMENTO ON P_ID = DEPARTAMENTO_ID" +
                    " WHERE DEPARTAMENTO_ID = ?";


            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, buscar);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("E_ID");
                String Enombre = rs.getString("E_NOMBRE");
                int salario = rs.getInt("E_SALARIO");
                int departamentoid = rs.getInt("departamento_id");
                String Pnombre = rs.getString("P_NOMBRE");


                System.out.println("ID: " + id);
                System.out.println("Nombre del empleado: " + Enombre);
                System.out.println("Salario: " + salario);
                System.out.println("Departamento ID: " + departamentoid);
                System.out.println("Nombre del departamento: " + Pnombre);
                System.out.print("");
                System.out.println("--------------------------------------");
            }

        }catch(SQLException e ){
            System.out.println("Error al conectar" + e.getMessage());
        }
    }
}