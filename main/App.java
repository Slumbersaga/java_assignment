package main;

import features.UserManagement;
import features.CourseManagement;
import features.EnrollmentManagement;
import util.Utils;

public class App {
    private UserManagement userManagement;
    private CourseManagement courseManagement;
    private EnrollmentManagement enrollmentManagement;

    public App() {
        this.userManagement = new UserManagement();
        this.courseManagement = new CourseManagement();
        this.enrollmentManagement = new EnrollmentManagement(userManagement, courseManagement);
    }

    public void run() {
        Utils.printHeader("STUDENT MANAGEMENT SYSTEM");
        System.out.println("Welcome to the Student Management System!");
        System.out.println("Please login or register to continue.");
        
        // Main application loop
        while (true) {
            if (!enrollmentManagement.isLoggedIn()) {
                showLoginMenu();
            } else {
                if (enrollmentManagement.isAdmin()) {
                    showAdminMenu();
                } else {
                    showStudentMenu();
                }
            }
        }
    }

    private void showLoginMenu() {
        Utils.printHeader("LOGIN MENU");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        
        int choice = Utils.getIntInput("Enter your choice (1-3): ");
        
        switch (choice) {
            case 1:
                if (enrollmentManagement.login()) {
                    Utils.pause();
                }
                break;
            case 2:
                if (enrollmentManagement.register()) {
                    Utils.pause();
                }
                break;
            case 3:
                System.out.println("Thank you for using Student Management System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void showAdminMenu() {
        Utils.printHeader("ADMIN MENU");
        System.out.println("Welcome, " + enrollmentManagement.getCurrentUser().getUsername() + "!");
        System.out.println("1. User Management");
        System.out.println("2. Course Management");
        System.out.println("3. View All Enrollments");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        
        int choice = Utils.getIntInput("Enter your choice (1-5): ");
        
        switch (choice) {
            case 1:
                showUserManagementMenu();
                break;
            case 2:
                showCourseManagementMenu();
                break;
            case 3:
                enrollmentManagement.displayAllEnrollments();
                Utils.pause();
                break;
            case 4:
                enrollmentManagement.logout();
                Utils.pause();
                break;
            case 5:
                System.out.println("Thank you for using Student Management System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void showStudentMenu() {
        Utils.printHeader("STUDENT MENU");
        System.out.println("Welcome, " + enrollmentManagement.getCurrentUser().getUsername() + "!");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View My Enrolled Courses");
        System.out.println("4. Unenroll from Course");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        
        int choice = Utils.getIntInput("Enter your choice (1-6): ");
        
        switch (choice) {
            case 1:
                courseManagement.displayAllCourses();
                Utils.pause();
                break;
            case 2:
                enrollmentManagement.enrollStudentInCourse();
                Utils.pause();
                break;
            case 3:
                enrollmentManagement.displayEnrolledCourses();
                Utils.pause();
                break;
            case 4:
                enrollmentManagement.removeStudentFromCourse();
                Utils.pause();
                break;
            case 5:
                enrollmentManagement.logout();
                Utils.pause();
                break;
            case 6:
                System.out.println("Thank you for using Student Management System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void showUserManagementMenu() {
        Utils.printHeader("USER MANAGEMENT");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. View All Students");
        System.out.println("4. View All Users");
        System.out.println("5. Back to Admin Menu");
        
        int choice = Utils.getIntInput("Enter your choice (1-5): ");
        
        switch (choice) {
            case 1:
                userManagement.addStudent();
                Utils.pause();
                break;
            case 2:
                userManagement.deleteStudent();
                Utils.pause();
                break;
            case 3:
                userManagement.displayAllStudents();
                Utils.pause();
                break;
            case 4:
                userManagement.displayAllUsers();
                Utils.pause();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void showCourseManagementMenu() {
        Utils.printHeader("COURSE MANAGEMENT");
        System.out.println("1. Add Course");
        System.out.println("2. Delete Course");
        System.out.println("3. Update Course");
        System.out.println("4. View All Courses");
        System.out.println("5. Back to Admin Menu");
        
        int choice = Utils.getIntInput("Enter your choice (1-5): ");
        
        switch (choice) {
            case 1:
                courseManagement.addCourse();
                Utils.pause();
                break;
            case 2:
                courseManagement.deleteCourse();
                Utils.pause();
                break;
            case 3:
                courseManagement.updateCourse();
                Utils.pause();
                break;
            case 4:
                courseManagement.displayAllCourses();
                Utils.pause();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
