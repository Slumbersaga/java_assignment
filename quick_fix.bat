@echo off
echo Quick Fix - Recompiling and running the system...
echo.

:: Clean previous compilation
if exist "classes" rmdir /s /q classes
mkdir classes

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
echo FIXED ISSUES:
echo ========================================
echo 1. Fixed registration bug - users are now properly added to system
echo 2. Added debug information to track user count
echo 3. Fixed ID generation to prevent duplicates
echo.
echo ========================================
echo TESTING STEPS:
echo ========================================
echo 1. Register a new student
echo 2. Try to login with the same credentials
echo 3. It should work now!
echo.
echo ========================================
echo Starting the system...
echo ========================================
echo.

:: Run the application
java -cp classes main.App

pause
