import java.sql.*;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Student> italianStudents = new ArrayList<>();
        ArrayList<Student> germanStudents = new ArrayList<>();

        try{

            String url = "jdbc:mysql://localhost:3306/newdb";
            String user      = "developer";
            String password  = "developer123";

            conn = DriverManager.getConnection(url,user, password);

            stmt = conn.createStatement();

            String italian_students = "CREATE VIEW italian_students AS " +   // CREAZIONE VIEW ITALIAN STUDENTS
                    "SELECT `students`.`last_name` AS `last_name`," +
                    "`students`.`first_name` AS `first_name` " +
                    "FROM `students` " +
                    "WHERE (`students`.`country` = 'Italy');";

            String german_students = "CREATE VIEW german_students AS " +    // CREAZIONE VIEW GERMANY STUDENTS
                    "SELECT `students`.`last_name` AS `last_name`," +
                    "`students`.`first_name` AS `first_name` " +
                    "FROM `students` " +
                    "WHERE (`students`.`country` = 'Germany');";


            stmt.execute(italian_students);                         //  ESECUZIONE QUERY PER LA CREAZIONE DELLA VIEW
            stmt.execute(german_students);                          //  ESECUZIONE QUERY PER LA CREAZIONE DELLA VIEW

            rs = stmt.executeQuery("SELECT * FROM italian_students");

            while(rs.next()){               // INSERIMENTO IN ARRAYLIST DI STUDENTI, STUDENTI SOLO CON COUNTRY = ITALY
                Student s1 = new Student(rs.getString("first_name"), rs.getString("last_name"));
                italianStudents.add(s1);
            }

            rs = stmt.executeQuery("SELECT * FROM german_students");

            while(rs.next()){               // INSERIMENTO IN ARRAYLIST DI STUDENTI, STUDENTI SOLO CON COUNTRY = GERMANY
                Student s1 = new Student(rs.getString("first_name"), rs.getString("last_name"));
                germanStudents.add(s1);
            }

            System.out.println("List italian students : " + italianStudents);     // STAMPA LISTA STUDENTI ITALY
            System.out.println("List germay students : " + germanStudents);       // STAMPA LISTA STUDENTI GERMANY

        }catch(SQLException e) {
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