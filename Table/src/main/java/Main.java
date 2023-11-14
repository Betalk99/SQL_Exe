import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;

        List<String> lastName = new ArrayList<>();
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/newdb";
            String user      = "developer";
            String password  = "developer123";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT first_name, last_name FROM students");

            while(rs.next()){
                System.out.println(rs.getString("first_name"));
                lastName.add(rs.getString("last_name"));
            }

            System.out.println(lastName);


        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }


    }
}
