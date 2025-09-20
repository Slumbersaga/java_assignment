# Student Management System

A comprehensive Java-based Student Management System with user authentication, course management, and enrollment features.

## Features

### For Administrators:
- **User Management**: Add and delete students
- **Course Management**: Add, delete, and update courses
- **View All Enrollments**: Monitor all student enrollments

### For Students:
- **Course Enrollment**: Enroll in available courses
- **View Enrolled Courses**: See all enrolled courses
- **Unenroll**: Remove themselves from courses

### Authentication:
- **Login System**: Secure login for both admins and students
- **Registration**: New user registration
- **User Types**: Admin and Student roles

## Project Structure

```
├── main/
│   └── App.java                    # Main application entry point
├── models/
│   ├── User.java                   # User model with properties
│   ├── CourseModel.java            # Course model
│   └── LoginModel.java             # Login credentials model
├── features/
│   ├── UserManagement.java         # User management operations
│   ├── CourseManagement.java       # Course management operations
│   └── EnrollmentManagement.java   # Enrollment and authentication
├── util/
│   └── Utils.java                  # Helper utility methods
├── compile_and_run.bat             # Windows compilation script
└── README.md                       # This file
```

## How to Run

### Method 1: Using the Batch File (Windows)
1. Double-click `compile_and_run.bat`
2. The system will compile and run automatically

### Method 2: Manual Compilation
1. Open Command Prompt in the project directory
2. Create classes directory: `mkdir classes`
3. Compile: `javac -d classes -cp . models/*.java util/*.java features/*.java main/*.java`
4. Run: `java -cp classes main.App`

## Default Admin Account
- **Email**: admin@school.com
- **Password**: admin123
- **Username**: admin

## Usage Instructions

1. **First Time Setup**: Use the default admin account to login
2. **Add Students**: Use Admin menu → User Management → Add Student
3. **Add Courses**: Use Admin menu → Course Management → Add Course
4. **Student Registration**: Students can register using the registration option
5. **Course Enrollment**: Students can enroll in courses after logging in

## Key Features

- **Input Validation**: Email format, phone number, password strength validation
- **Duplicate Prevention**: Prevents duplicate usernames and emails
- **Role-based Access**: Different menus for admin and student users
- **User-friendly Interface**: Clear menus and prompts
- **Data Persistence**: Uses ArrayList for data storage during session

## Technical Details

- **Language**: Java
- **Data Storage**: In-memory using ArrayList
- **Architecture**: MVC pattern with separate model, view, and controller layers
- **Validation**: Comprehensive input validation and error handling
- **User Experience**: Interactive console-based interface

## Sample Workflow

1. Login as admin (admin@school.com / admin123)
2. Add some courses (e.g., "Java Programming", "Web Development")
3. Add some students
4. Logout and login as a student
5. Enroll in courses
6. View enrolled courses
7. Unenroll if needed

Enjoy using the Student Management System!
