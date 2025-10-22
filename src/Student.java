public class Student {
    private int id;
    private String name;
    private String roll;
    private String department;
    private String email;

    public Student() {}

    public Student(int id, String name, String roll, String department, String email) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.department = department;
        this.email = email;
    }

    public Student(String name, String roll, String department, String email) {
        this.name = name;
        this.roll = roll;
        this.department = department;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRoll() { return roll; }
    public void setRoll(String roll) { this.roll = roll; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
