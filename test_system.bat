@echo off
echo Testing Student Management System...
echo.

:: Create classes directory if it doesn't exist
if not exist "classes" mkdir classes

:: Compile all Java files
echo Compiling...
javac -d classes -cp . models/*.java util/*.java features/*.java main/*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo ========================================
echo TESTING INSTRUCTIONS:
echo ========================================
echo 1. First, login as admin:
echo    Email: admin@school.com
echo    Password: admin123
echo.
echo 2. Add some courses:
echo    - Go to Course Management
echo    - Add Course
echo    - Add courses like "Java Programming", "Web Development"
echo.
echo 3. Add some students:
echo    - Go to User Management
echo    - Add Student
echo    - Add students with different emails
echo.
echo 4. Logout and register a new student:
echo    - Choose Register
echo    - Fill in details
echo    - Choose Student type
echo.
echo 5. Login with the new student account
echo.
echo 6. Enroll in courses
echo.
echo ========================================
echo Starting the system...
echo ========================================
echo.

:: Run the application
java -cp classes main.App

pause
