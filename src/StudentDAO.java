import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // UPDATE these constants if your DB uses different credentials
    private static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ensure driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("roll"),
                    rs.getString("department"),
                    rs.getString("email")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(Student s) {
        String sql = "INSERT INTO students (name, roll, department, email) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRoll());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Student s) {
        String sql = "UPDATE students SET name=?, roll=?, department=?, email=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRoll());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            ps.setInt(5, s.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
