package StudentManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExportToCSV {
    public static void exportData() {
        String fileName = "students_data.csv";
        try (FileWriter writer = new FileWriter(fileName);
             Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            writer.write("ID, Name, Age, Email, Course\n");
            while (rs.next()) {
                writer.write(rs.getInt("id") + "," + rs.getString("name") + "," +
                        rs.getInt("age") + "," + rs.getString("email") + "," +
                        rs.getString("course") + "\n");
            }
            System.out.println("✅ Data Exported to CSV Successfully!");

        } catch (IOException | SQLException e) {
            System.out.println("⚠ Error Exporting CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        exportData();
    }
}

