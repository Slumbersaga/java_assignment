package features;

import models.User;
import util.Utils;
import java.util.ArrayList;

public class UserManagement {
    private ArrayList<User> users;
    private int studentCount = 0;

    public UserManagement() {
        this.users = new ArrayList<>();
        // Add default admin user
        addDefaultAdmin();
    }

    // Add default admin user
    private void addDefaultAdmin() {
        User admin = new User("ADM001", "admin", "admin@school.com", "1234567890", "admin123", "admin");
        users.add(admin);
    }

    // Add new student (Admin only)
    public boolean addStudent() {
        Utils.printHeader("ADD NEW STUDENT");
        
        String username = Utils.getInput("Enter student username: ");
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }

        // Check if username already exists
        if (isUsernameExists(username)) {
            System.out.println("Username already exists! Please choose a different username.");
            return false;
        }

        String email = Utils.getInput("Enter student email: ");
        if (!Utils.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return false;
        }

        // Check if email already exists
        if (isEmailExists(email)) {
            System.out.println("Email already exists! Please use a different email.");
            return false;
        }

        String phone = Utils.getInput("Enter student phone number: ");
        if (!Utils.isValidPhone(phone)) {
            System.out.println("Invalid phone number! Please enter 10-15 digits.");
            return false;
        }

        String password = Utils.getInput("Enter student password: ");
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long!");
            return false;
        }

        String userId = Utils.generateId("STU", studentCount);
        User newStudent = new User(userId, username, email, phone, password, "student");
        users.add(newStudent);
        studentCount++;

        System.out.println("Student added successfully!");
        System.out.println("Student ID: " + userId);
        return true;
    }

    // Delete student (Admin only)
    public boolean deleteStudent() {
        Utils.printHeader("DELETE STUDENT");
        
        if (getAllStudents().isEmpty()) {
            System.out.println("No students found!");
            return false;
        }

        System.out.println("Current Students:");
        Utils.printNumberedList(getAllStudents());

        int choice = Utils.getIntInput("Enter student number to delete (0 to cancel): ");
        
        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return false;
        }

        ArrayList<User> students = getAllStudents();
        if (choice < 1 || choice > students.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        User studentToDelete = students.get(choice - 1);
        
        if (Utils.confirmAction("Are you sure you want to delete student: " + studentToDelete.getUsername() + "?")) {
            users.remove(studentToDelete);
            System.out.println("Student deleted successfully!");
            return true;
        } else {
            System.out.println("Operation cancelled.");
            return false;
        }
    }

    // Get all students
    public ArrayList<User> getAllStudents() {
        ArrayList<User> students = new ArrayList<>();
        for (User user : users) {
            if ("student".equals(user.getUserType())) {
                students.add(user);
            }
        }
        return students;
    }

    // Get all users
    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Check if username exists
    private boolean isUsernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Check if email exists
    private boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Find user by email
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        Utils.printHeader("ALL STUDENTS");
        ArrayList<User> students = getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            Utils.printNumberedList(students);
        }
    }

    // Display all users (Admin only)
    public void displayAllUsers() {
        Utils.printHeader("ALL USERS");
        if (users.isEmpty()) {
            System.out.println("No users found!");
        } else {
            Utils.printNumberedList(users);
        }
    }
}
