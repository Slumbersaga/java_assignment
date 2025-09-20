package features;

import models.User;
import models.CourseModel;
import models.LoginModel;
import util.Utils;
import java.util.ArrayList;

public class EnrollmentManagement {
    private UserManagement userManagement;
    private CourseManagement courseManagement;
    private User currentUser;

    public EnrollmentManagement(UserManagement userManagement, CourseManagement courseManagement) {
        this.userManagement = userManagement;
        this.courseManagement = courseManagement;
        this.currentUser = null;
    }

    // Login method
    public boolean login() {
        Utils.printHeader("LOGIN");
        
        String email = Utils.getInput("Enter email: ");
        String password = Utils.getInput("Enter password: ");
        
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Email and password cannot be empty!");
            return false;
        }

        User user = userManagement.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + user.getUsername() + "!");
            System.out.println("User Type: " + user.getUserType().toUpperCase());
            return true;
        } else {
            System.out.println("Invalid email or password!");
            return false;
        }
    }

    // Registration method
    public boolean register() {
        Utils.printHeader("REGISTRATION");
        
        String username = Utils.getInput("Enter username: ");
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }

        // Check if username already exists
        if (userManagement.getAllUsers().stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username))) {
            System.out.println("Username already exists! Please choose a different username.");
            return false;
        }

        String email = Utils.getInput("Enter email: ");
        if (!Utils.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return false;
        }

        // Check if email already exists
        if (userManagement.findUserByEmail(email) != null) {
            System.out.println("Email already exists! Please use a different email.");
            return false;
        }

        String phone = Utils.getInput("Enter phone number: ");
        if (!Utils.isValidPhone(phone)) {
            System.out.println("Invalid phone number! Please enter 10-15 digits.");
            return false;
        }

        String password = Utils.getInput("Enter password: ");
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long!");
            return false;
        }

        String confirmPassword = Utils.getInput("Confirm password: ");
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return false;
        }

        // Ask for user type
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Admin");
        int userTypeChoice = Utils.getIntInput("Enter choice (1-2): ");
        
        String userType;
        if (userTypeChoice == 1) {
            userType = "student";
        } else if (userTypeChoice == 2) {
            userType = "admin";
        } else {
            System.out.println("Invalid choice! Defaulting to student.");
            userType = "student";
        }

        // Create new user
        String userId = Utils.generateId(userType.equals("admin") ? "ADM" : "STU", 
                                       userManagement.getAllUsers().size());
        User newUser = new User(userId, username, email, phone, password, userType);
        userManagement.getAllUsers().add(newUser);

        System.out.println("Registration successful!");
        System.out.println("User ID: " + userId);
        System.out.println("User Type: " + userType.toUpperCase());
        return true;
    }

    // Logout method
    public void logout() {
        if (currentUser != null) {
            System.out.println("Goodbye, " + currentUser.getUsername() + "!");
            currentUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Get current user
    public User getCurrentUser() {
        return currentUser;
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Check if current user is admin
    public boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getUserType());
    }

    // Enroll student in course
    public boolean enrollStudentInCourse() {
        if (!isLoggedIn()) {
            System.out.println("Please login first!");
            return false;
        }

        if (!"student".equals(currentUser.getUserType())) {
            System.out.println("Only students can enroll in courses!");
            return false;
        }

        Utils.printHeader("ENROLL IN COURSE");
        
        ArrayList<CourseModel> availableCourses = courseManagement.getAllCourses();
        if (availableCourses.isEmpty()) {
            System.out.println("No courses available!");
            return false;
        }

        System.out.println("Available Courses:");
        Utils.printNumberedList(availableCourses);

        int choice = Utils.getIntInput("Enter course number to enroll (0 to cancel): ");
        
        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return false;
        }

        if (choice < 1 || choice > availableCourses.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        CourseModel selectedCourse = availableCourses.get(choice - 1);
        
        // Check if already enrolled
        if (currentUser.getCourses().contains(selectedCourse)) {
            System.out.println("You are already enrolled in this course!");
            return false;
        }

        currentUser.addCourse(selectedCourse);
        System.out.println("Successfully enrolled in course: " + selectedCourse.getCourseName());
        return true;
    }

    // Remove student from course
    public boolean removeStudentFromCourse() {
        if (!isLoggedIn()) {
            System.out.println("Please login first!");
            return false;
        }

        if (!"student".equals(currentUser.getUserType())) {
            System.out.println("Only students can unenroll from courses!");
            return false;
        }

        Utils.printHeader("UNENROLL FROM COURSE");
        
        ArrayList<CourseModel> enrolledCourses = currentUser.getCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses!");
            return false;
        }

        System.out.println("Your Enrolled Courses:");
        Utils.printNumberedList(enrolledCourses);

        int choice = Utils.getIntInput("Enter course number to unenroll (0 to cancel): ");
        
        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return false;
        }

        if (choice < 1 || choice > enrolledCourses.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        CourseModel selectedCourse = enrolledCourses.get(choice - 1);
        
        if (Utils.confirmAction("Are you sure you want to unenroll from: " + selectedCourse.getCourseName() + "?")) {
            currentUser.removeCourse(selectedCourse);
            System.out.println("Successfully unenrolled from course: " + selectedCourse.getCourseName());
            return true;
        } else {
            System.out.println("Operation cancelled.");
            return false;
        }
    }

    // Display student's enrolled courses
    public void displayEnrolledCourses() {
        if (!isLoggedIn()) {
            System.out.println("Please login first!");
            return;
        }

        if (!"student".equals(currentUser.getUserType())) {
            System.out.println("Only students can view enrolled courses!");
            return;
        }

        Utils.printHeader("YOUR ENROLLED COURSES");
        ArrayList<CourseModel> enrolledCourses = currentUser.getCourses();
        
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses!");
        } else {
            Utils.printNumberedList(enrolledCourses);
        }
    }

    // Display all enrollments (Admin only)
    public void displayAllEnrollments() {
        if (!isLoggedIn() || !isAdmin()) {
            System.out.println("Access denied! Admin privileges required.");
            return;
        }

        Utils.printHeader("ALL ENROLLMENTS");
        
        ArrayList<User> students = userManagement.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        for (User student : students) {
            System.out.println("\nStudent: " + student.getUsername() + " (" + student.getEmail() + ")");
            ArrayList<CourseModel> courses = student.getCourses();
            if (courses.isEmpty()) {
                System.out.println("  No enrolled courses");
            } else {
                System.out.println("  Enrolled Courses:");
                for (CourseModel course : courses) {
                    System.out.println("    - " + course.getCourseName() + " (" + course.getCourseDuration() + ")");
                }
            }
        }
    }
}
