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
            String createColumnCountry = "ALTER TABLE students " + // create new column country
                    "ADD country varchar(30)";
            stmt.execute(createColumnCountry);
            System.out.println("Create column country");
            //populate column country with 4 query
            String setCountryQuery1 = "UPDATE `newdb`.`students` SET `country`='Italy' WHERE  `student_id`=1";
            String setCountryQuery2 = "UPDATE `newdb`.`students` SET `country`='Italy' WHERE  `student_id`=2";
            String setCountryQuery3 = "UPDATE `newdb`.`students` SET `country`='Germany' WHERE  `student_id`=3" ;
            String setCountryQuery4 = "UPDATE `newdb`.`students` SET `country`='Germany' WHERE  `student_id`=4" ;
            stmt.execute(setCountryQuery1);
            stmt.execute(setCountryQuery2);
            stmt.execute(setCountryQuery3);
            stmt.execute(setCountryQuery4);
            System.out.println("Column upgrade");


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
