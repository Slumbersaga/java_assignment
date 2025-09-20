package features;

import models.CourseModel;
import util.Utils;
import java.util.ArrayList;

public class CourseManagement {
    private ArrayList<CourseModel> courses;
    private int courseCount = 0;

    public CourseManagement() {
        this.courses = new ArrayList<>();
    }

    // Add new course (Admin only)
    public boolean addCourse() {
        Utils.printHeader("ADD NEW COURSE");
        
        String courseName = Utils.getInput("Enter course name: ");
        if (courseName.isEmpty()) {
            System.out.println("Course name cannot be empty!");
            return false;
        }

        // Check if course already exists
        if (isCourseExists(courseName)) {
            System.out.println("Course already exists! Please choose a different name.");
            return false;
        }

        String courseDuration = Utils.getInput("Enter course duration (e.g., 3 months, 6 weeks): ");
        if (courseDuration.isEmpty()) {
            System.out.println("Course duration cannot be empty!");
            return false;
        }

        String courseId = Utils.generateId("CRS", courseCount);
        CourseModel newCourse = new CourseModel(courseId, courseName, courseDuration);
        courses.add(newCourse);
        courseCount++;

        System.out.println("Course added successfully!");
        System.out.println("Course ID: " + courseId);
        return true;
    }

    // Delete course (Admin only)
    public boolean deleteCourse() {
        Utils.printHeader("DELETE COURSE");
        
        if (courses.isEmpty()) {
            System.out.println("No courses found!");
            return false;
        }

        System.out.println("Current Courses:");
        Utils.printNumberedList(courses);

        int choice = Utils.getIntInput("Enter course number to delete (0 to cancel): ");
        
        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return false;
        }

        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        CourseModel courseToDelete = courses.get(choice - 1);
        
        if (Utils.confirmAction("Are you sure you want to delete course: " + courseToDelete.getCourseName() + "?")) {
            courses.remove(courseToDelete);
            System.out.println("Course deleted successfully!");
            return true;
        } else {
            System.out.println("Operation cancelled.");
            return false;
        }
    }

    // Get all courses
    public ArrayList<CourseModel> getAllCourses() {
        return new ArrayList<>(courses);
    }

    // Check if course exists
    private boolean isCourseExists(String courseName) {
        for (CourseModel course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return true;
            }
        }
        return false;
    }

    // Find course by ID
    public CourseModel findCourseById(String courseId) {
        for (CourseModel course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    // Find course by name
    public CourseModel findCourseByName(String courseName) {
        for (CourseModel course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    // Display all courses
    public void displayAllCourses() {
        Utils.printHeader("ALL COURSES");
        if (courses.isEmpty()) {
            System.out.println("No courses found!");
        } else {
            Utils.printNumberedList(courses);
        }
    }

    // Update course
    public boolean updateCourse() {
        Utils.printHeader("UPDATE COURSE");
        
        if (courses.isEmpty()) {
            System.out.println("No courses found!");
            return false;
        }

        System.out.println("Current Courses:");
        Utils.printNumberedList(courses);

        int choice = Utils.getIntInput("Enter course number to update (0 to cancel): ");
        
        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return false;
        }

        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        CourseModel courseToUpdate = courses.get(choice - 1);
        
        System.out.println("Current course details:");
        System.out.println("1. Course Name: " + courseToUpdate.getCourseName());
        System.out.println("2. Course Duration: " + courseToUpdate.getCourseDuration());
        
        int fieldChoice = Utils.getIntInput("Enter field number to update (0 to cancel): ");
        
        switch (fieldChoice) {
            case 0:
                System.out.println("Operation cancelled.");
                return false;
            case 1:
                String newName = Utils.getInput("Enter new course name: ");
                if (!newName.isEmpty() && !isCourseExists(newName)) {
                    courseToUpdate.setCourseName(newName);
                    System.out.println("Course name updated successfully!");
                    return true;
                } else {
                    System.out.println("Invalid course name or name already exists!");
                    return false;
                }
            case 2:
                String newDuration = Utils.getInput("Enter new course duration: ");
                if (!newDuration.isEmpty()) {
                    courseToUpdate.setCourseDuration(newDuration);
                    System.out.println("Course duration updated successfully!");
                    return true;
                } else {
                    System.out.println("Invalid duration!");
                    return false;
                }
            default:
                System.out.println("Invalid choice!");
                return false;
        }
    }
}
