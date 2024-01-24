import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


    class Hall {
String name;
double price;
int size;
public Hall(String name, double price, int size) {
this.name = name;
this.price = price;
this.size = size;
}
public void getDetails() {
System.out.println("Hall Name: " + name);
System.out.println("Price: " + price);
System.out.println("Size: " + size + " seats");
}
}


class Employee {
int employeeID;
String name;
int yearOfExperience;
int age;
public Employee(int employeeID, String name, int yearOfExperience, int age) {
this.employeeID = employeeID;
this.name = name;
this.yearOfExperience = yearOfExperience;
this.age = age;
}
public void getDetails() {
System.out.println("Employee ID: " + employeeID);
System.out.println("Name: " + name);
System.out.println("Experience: " + yearOfExperience + " years");
System.out.println("Age: " + age);
}
}
class AdminEmployee extends Employee {
String userAccount;
String password;
public AdminEmployee(int employeeID, String name, int yearOfExperience, int
age,
String userAccount, String password) {
super(employeeID, name, yearOfExperience, age);
this.userAccount = userAccount;
this.password = password;
}
public void manageOperation() {
System.out.println("Admin managing operations...");
}
}
class Event {
String name;
String type;
String ownerFullName;
String ownerPhone;
String address;
List<Hall> rooms = new ArrayList<>();
List<Employee> assignedEmployees = new ArrayList<>();
public Event(String name, String type, String ownerFullName, String
ownerPhone, String address) {
this.name = name;
this.type = type;
this.ownerFullName = ownerFullName;
this.ownerPhone = ownerPhone;
this.address = address;
}
public void addRoom(Hall hall) {
rooms.add(hall);
}
public void removeRoom(Hall hall) {
rooms.remove(hall);
}
public void addEmployee(Employee employee) {
assignedEmployees.add(employee);
}
public void removeEmployee(Employee employee) {
assignedEmployees.remove(employee);
}
public void getDetails() {
System.out.println("Event Name: " + name);
System.out.println("Type: " + type);
System.out.println("Owner: " + ownerFullName);
System.out.println("Owner Phone: " + ownerPhone);
System.out.println("Address: " + address);
}
}
class ConferenceCenter {
List<Hall> halls = new ArrayList<>();
List<Event> events = new ArrayList<>();
List<AdminEmployee> adminEmployees = new ArrayList<>();
public void addHall(Hall hall) {
halls.add(hall);
}
public void removeHall(Hall hall) {
halls.remove(hall);
}
public void addEvent(Event event) {
events.add(event);
}
public void removeEvent(Event event) {
events.remove(event);
}
public void manageOperation(AdminEmployee adminEmployee) {
adminEmployee.manageOperation();
}
}
 class ConferenceManagementSystem {
public static void main(String[] args) {
// Sample usage
var conferenceCenter = new ConferenceCenter();
Hall hall1 = new Hall("Hall A", 1000.0, 200);
Hall hall2 = new Hall("Hall B", 800.0, 150);
conferenceCenter.addHall(hall1);
conferenceCenter.addHall(hall2);
Event event1 = new Event("Conference 1", "Business", "John Doe", 
"123456789", "123 Main St");
Event event2 = new Event("Seminar 1", "Education", "Jane Smith", 
"987654321", "456 Oak St");
event1.addRoom(hall1);
event1.addEmployee(new Employee(1, "Alice", 5, 30));
event2.addRoom(hall2);
event2.addEmployee(new Employee(2, "Bob", 3, 28));
conferenceCenter.addEvent(event1);
conferenceCenter.addEvent(event2);
AdminEmployee admin = new AdminEmployee(0, "Admin Name", 10, 40, "admin", 
"password");
conferenceCenter.manageOperation(admin);
}

}

/*class ConferenceManagementSystemGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
    frame.setSize(300,300);
    frame.setVisible(true);
}


}*/


 class ConferenceManagementSystemGUI extends JFrame {
    private JTextArea outputTextArea;
    private JButton retrieveHallsButton;
    private JButton retrieveEmployeesButton;
    private JButton retrieveEventsButton;

    private Connection connection;

    public ConferenceManagementSystemGUI() {
        // Set up the GUI
        setSize(800, 400);
        setTitle("Conference Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        retrieveHallsButton = new JButton("Retrieve Halls");
        retrieveEmployeesButton = new JButton("Retrieve Employees");
        retrieveEventsButton = new JButton("Retrieve Events");
        buttonPanel.add(retrieveHallsButton);
        buttonPanel.add(retrieveEmployeesButton);
        buttonPanel.add(retrieveEventsButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register action listeners for the buttons
        retrieveHallsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveHalls();
            }
        });

        retrieveEmployeesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveEmployees();
            }
        });

        retrieveEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveEvents();
            }
        });

        // Establish the database connection
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String jdbcUrl = "jdbc:sqlserver://DESKTOP-IVJF0DT\\SQLEXPRESS;databaseName=java;integratedSecurity=false;encrypt=false";
            String username = "usr";
            String password = "1234";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveHalls() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hall");

            outputTextArea.setText("");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int size = resultSet.getInt("size");

                outputTextArea.append("Hall ID: " + id + "\n");
                outputTextArea.append("Name: " + name + "\n");
                outputTextArea.append("Price: " + price + "\n");
                outputTextArea.append("Size: " + size + " seats\n");
                outputTextArea.append("---------------------------\n");
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveEmployees() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");

            outputTextArea.setText("");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int yearOfExperience = resultSet.getInt("yearOfExperience");
                int age = resultSet.getInt("age");

                outputTextArea.append("Employee ID: " + id + "\n");
                outputTextArea.append("Name: " + name + "\n");
                outputTextArea.append("Experience: " + yearOfExperience + " years\n");
                outputTextArea.append("Age: " + age + "\n");
                outputTextArea.append("---------------------------\n");
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void retrieveEvents() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Event");
    
            outputTextArea.setText("");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String ownerFullName = resultSet.getString("ownerFullName");
                String ownerPhone = resultSet.getString("ownerPhone");
                String address = resultSet.getString("address");
    
                outputTextArea.append("Event ID: " + id + "\n");
                outputTextArea.append("Name: " + name + "\n");
                outputTextArea.append("Type: " + type + "\n");
                outputTextArea.append("Owner: " + ownerFullName + "\n");
                outputTextArea.append("Owner Phone: " + ownerPhone + "\n");
                outputTextArea.append("Address: " + address + "\n");
                outputTextArea.append("---------------------------\n");
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {  // Catch SQLException instead of generic Exception
            e.printStackTrace();
        }
    }
    
        // ... Existing code ...
    
        public static void main(String[] args) {
            try {
                SwingUtilities.invokeLater(() -> {
                    ConferenceManagementSystemGUI gui = new ConferenceManagementSystemGUI();
                    gui.setVisible(true);
                });
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
          
        }
    
}