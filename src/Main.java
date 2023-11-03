import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static  final String DB_URL = "jdbc:postgresql://localhost:5432/training_hw_5";
    static final String NAME = "postgres";
    static final String PASSWORD = "Gfhjkm";

    public static void main(String[] args) {

        String select = """
               select *
               from audit_department
               """;

        Map<Integer, String> obj = executeSelect(select);

        obj.forEach((key, value) -> System.out.println(key + ":" + value));

    }

    public static Map<Integer, String> executeSelect(String select) {
        try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
             Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(select);

            Map<Integer, String> obj = new HashMap<>();
            int i = 1;
            while (result.next()) {
                i+=1;
                obj.put(i, result.getString(2));
            }

            return obj;
        } catch (SQLException ex) {
            System.out.println("Cannot connect to DB: " + ex.getMessage());
            return Map.of();
        }
    }
}