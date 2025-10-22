import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    private StudentDAO dao = new StudentDAO();
    private JTable table;
    private DefaultTableModel model;
    private JTextField tfName, tfRoll, tfDept, tfEmail;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;

    public Main() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top form
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Name:"), c);
        c.gridx = 1;
        tfName = new JTextField(15);
        form.add(tfName, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Roll:"), c);
        c.gridx = 1; tfRoll = new JTextField(15); form.add(tfRoll, c);

        c.gridx = 2; c.gridy = 0; form.add(new JLabel("Department:"), c);
        c.gridx = 3; tfDept = new JTextField(15); form.add(tfDept, c);

        c.gridx = 2; c.gridy = 1; form.add(new JLabel("Email:"), c);
        c.gridx = 3; tfEmail = new JTextField(15); form.add(tfEmail, c);

        add(form, BorderLayout.NORTH);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnAdd = new JButton("Add"); btnUpdate = new JButton("Update"); btnDelete = new JButton("Delete"); btnClear = new JButton("Clear"); btnRefresh = new JButton("Refresh");
        btnPanel.add(btnAdd); btnPanel.add(btnUpdate); btnPanel.add(btnDelete); btnPanel.add(btnClear); btnPanel.add(btnRefresh);
        add(btnPanel, BorderLayout.SOUTH);

        // Table
        model = new DefaultTableModel(new Object[]{"ID","Name","Roll","Department","Email"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // Load data
        loadData();

        // Listeners
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadData());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    tfName.setText(model.getValueAt(r,1).toString());
                    tfRoll.setText(model.getValueAt(r,2).toString());
                    tfDept.setText(model.getValueAt(r,3).toString());
                    tfEmail.setText(model.getValueAt(r,4).toString());
                }
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        List<Student> list = dao.getAll();
        for (Student s : list) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getRoll(), s.getDepartment(), s.getEmail()});
        }
    }

    private void addStudent() {
        String name = tfName.getText().trim();
        String roll = tfRoll.getText().trim();
        if (name.isEmpty() || roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Roll are required", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Student s = new Student(name, roll, tfDept.getText().trim(), tfEmail.getText().trim());
        boolean ok = dao.add(s);
        if (ok) { JOptionPane.showMessageDialog(this, "Student added"); loadData(); clearForm(); }
        else JOptionPane.showMessageDialog(this, "Failed to add student", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void updateStudent() {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Select a student to update", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) model.getValueAt(r,0);
        Student s = new Student(id, tfName.getText().trim(), tfRoll.getText().trim(), tfDept.getText().trim(), tfEmail.getText().trim());
        boolean ok = dao.update(s);
        if (ok) { JOptionPane.showMessageDialog(this, "Updated"); loadData(); clearForm(); }
        else JOptionPane.showMessageDialog(this, "Failed to update", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void deleteStudent() {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Select a student to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) model.getValueAt(r,0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete student id="+id+"?","Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = dao.delete(id);
            if (ok) { JOptionPane.showMessageDialog(this, "Deleted"); loadData(); clearForm(); }
            else JOptionPane.showMessageDialog(this, "Failed to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        tfName.setText(""); tfRoll.setText(""); tfDept.setText(""); tfEmail.setText(""); table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new Main().setVisible(true);
        });
    }
}
