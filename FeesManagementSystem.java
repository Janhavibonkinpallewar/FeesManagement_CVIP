import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FeesManagementSystem {
    private List<Student> students = new ArrayList<>();
    private JFrame frame;
    private JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeesManagementSystem app = new FeesManagementSystem();
            app.createGUI();
        });
    }

    private void createGUI() {
        frame = new JFrame("Fees Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student Fee");
        addButton.addActionListener(new AddButtonListener());
        JButton viewButton = new JButton("View Student Fees");
        viewButton.addActionListener(new ViewButtonListener());
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(frame, "Enter student name:");
            if (name != null) {
                String feeStr = JOptionPane.showInputDialog(frame, "Enter fee amount:");
                if (feeStr != null) {
                    try {
                        double fee = Double.parseDouble(feeStr);
                        Student student = new Student(name, fee);
                        students.add(student);
                        textArea.append("Student Name: " + student.getName() + ", Fee: $" + student.getFee() + "\n");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid fee amount. Please enter a valid number.");
                    }
                }
            }
        }
    }

    private class ViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No student fee records to display.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Student student : students) {
                    sb.append("Student Name: ").append(student.getName()).append(", Fee: $").append(student.getFee()).append("\n");
                }
                textArea.setText(sb.toString());
            }
        }
    }

    private class Student {
        private String name;
        private double fee;

        public Student(String name, double fee) {
            this.name = name;
            this.fee = fee;
        }

        public String getName() {
            return name;
        }

        public double getFee() {
            return fee;
        }
    }
}
