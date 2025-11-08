# Edunity Management System

A Java-based educational management application taht I made for tracking students, courses, payments, and attendance records effectively and efficiently.

## Features

- **Student Management**: Add, update, search, and display student information
- **Course Management**: Create and manage course offerings with scheduling details  
- **Payment Tracking**: Record and view payment transactions for enrolled students
- **Attendance System**: Track student attendance with remarks and date records
- **Data Persistence**: File-based storage for all records

## Project Structure

```
src/edunity/
├── model/          # Data models (Student, Course, Payment, Attendance)
├── service/        # Business logic layer
├── storage/        # File-based data persistence
└── ui/             # Console user interface
```

## Requirements

- Java 8 or higher
- No external dependencies required

## How to Build and Run

### Compile:
```bash
javac -d bin src/edunity/*/*.java
```

### Run:
```bash
java -cp bin edunity.ui.EdunityApp
```

## Usage

The application presents a menu-driven interface with the following options:

1. Student Management - Add, view, search, and update student records
2. Course Management - Create and display course information
3. Payment Management - Record and track payment transactions
4. Attendance Management - Mark and view attendance records
5. Exit - Save data and close the application

## Data Storage

All data is stored in the `data/` directory:
- `students.dat` - Student records
- `courses.dat` - Course information
- `payments.dat` - Payment transactions
- `attendance.dat` - Attendance records

## Development Notes

- Uses Java Serialization for data persistence
- Implements a service layer pattern for separation of concerns
- Console-based UI for simplicity and portability

## Author

Personal project developed to demonstrate Java programming concepts including:
- Object-oriented design
- Package organization
- File I/O operations
- Menu-driven applications
