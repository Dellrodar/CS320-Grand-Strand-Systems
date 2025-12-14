# CS320-Grand-Strand-Systems
A repo for the final project of CS320, a mobile application developed and tested in Java

# Project Reflection

## How can I ensure that my code, program, or software is functional and secure?

I ensure my software is functional and secure by following a requirement driven and test focused development process. Each feature is implemented to meet specific constraints and is verified using unit tests that cover both valid and invalid inputs. Testing edge cases such as null values, invalid dates, and duplicate identifiers helps prevent incorrect behavior and reduces the risk of unexpected failures. Consistent testing also ensures that changes do not introduce regressions.

## How do I interpret user needs and incorporate them into a program?

I interpret user needs by translating written requirements into clear program logic and test cases. In this project, user expectations were defined through service requirements for managing contacts, tasks, and appointments. These requirements were enforced directly in the code and validated through unit tests, ensuring that the application behaves as expected from a user perspective.

## How do I approach designing software?

I approach software design with an emphasis on simplicity, separation of concerns, and testability. Core data objects are kept focused on enforcing their own rules, while service classes handle higher level operations. This structure makes the code easier to test, understand, and maintain, and allows the application to be extended more easily in the future.

## Project Overview

This project is a Java-based service layer application designed to manage three core entities: **Tasks**, **Contacts**, and **Appointments**. The application provides a clean, testable architecture with comprehensive data validation and service management capabilities.

### Components

The project consists of three main service modules:

#### 1. **Task Service** (`taskservice`)
- Manages task entities with unique IDs, names, and descriptions
- Supports CRUD operations: create, read, update, and delete tasks
- Validates task data (ID ≤ 10 chars, name ≤ 20 chars, description ≤ 50 chars)
- Ensures unique task IDs across the service

#### 2. **Contact Service** (`contactservice`)
- Manages contact information including first name, last name, phone number, and address
- Supports full CRUD operations with individual field updates
- Enforces data constraints:
  - ID, first name, last name: ≤ 10 characters
  - Phone number: exactly 10 characters
  - Address: ≤ 30 characters
- Maintains unique contact IDs

#### 3. **Appointment Service** (`appointmentservice`)
- Manages appointments with dates and descriptions
- Validates appointment dates (cannot be null or in the past)
- Enforces description length (≤ 50 characters)
- Uses HashMap for efficient appointment lookup by ID

### Key Features

- **Data Validation**: All entities enforce strict validation rules to ensure data integrity
- **Immutable IDs**: Entity IDs are set only during construction and cannot be modified
- **Service Layer Pattern**: Clean separation between domain objects and service logic
- **Comprehensive Testing**: Each module includes unit tests covering valid and invalid scenarios
- **Error Handling**: Proper exception handling with descriptive error messages

### Project Structure

```
src/
├── appointmentservice/
│   ├── Appointment.java
│   ├── AppointmentService.java
│   └── AppointmentServiceTest.java
├── contactservice/
│   ├── Contact.java
│   ├── ContactService.java
│   └── ContactServiceTest.java
└── taskservice/
    ├── Task.java
    ├── TaskService.java
    └── TaskServiceTest.java
```

