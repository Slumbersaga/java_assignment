@echo off
echo Compiling Student Management System...

:: Create classes directory if it doesn't exist
if not exist "classes" mkdir classes

:: Compile all Java files
javac -d classes -cp . models/*.java util/*.java features/*.java main/*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting Student Management System...
echo.

:: Run the application
java -cp classes main.App

pause
