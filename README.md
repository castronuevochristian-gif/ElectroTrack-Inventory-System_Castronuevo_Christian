# ElectroTrack: Component and Inventory Management System

ElectroTrack is a robust, production-ready desktop information management system designed for tracking electronics engineering components, device inventories, and system operator authorizations. Built natively on Java Swing (GUI) and driven by a MySQL relational database, this project utilizes the Model-View-Controller (MVC) structural pattern and the Data Access Object (DAO) enterprise architectural design pattern to ensure robust, clean code isolation.

---

## System Architecture Features

* Multi-Table Relational Database: Driven by a normalized schema implementing primary and foreign key constraints to eliminate redundancy.
* Complete CRUD Capabilities: Standardized workflows to Create, Read, Update, and Delete component records in real-time.
* Role-Based Authentication Engine: Secure gateway system restricting software utilities dynamically based on identity clearance levels (Admin vs Staff).
* Asynchronous Table Realignment: Live UI triggers that query the underlying database driver and refresh the data grids dynamically without requiring a software restart.

---

## Core OOP and Database Concepts Implemented

### 1. Object-Oriented Software Principles
* Encapsulation: All structural model state data (e.g., User.java, Component.java) use strictly private properties exposed safely via type-checked public getters and setters.
* Abstraction and Interfaces: Heavy utilization of decoupling architectures via Java Generics contracts (BaseDAO<T>) to separate raw query execution blocks from frame operations.

### 2. Relational Database Design
* Data Integrity and Constraints: Custom relational rules including UNIQUE attributes on critical properties (like part_number) to natively mitigate duplication errors at the hardware layer.

---

## Technology Stack and Dependencies

* Development Platform: Java Development Kit (JDK 11 or higher)
* Desktop Interface Library: Java Swing and AWT API
* Database Engine: MySQL Server 8.0+
* Connection Driver: JDBC (Java Database Connectivity) via mysql-connector-j

---

## Directory and Package Mapping Structure

```text
src/com/electrotrack/
│
├── database/     # Core JDBC Singleton instance configuration patterns 
├── model/        # Encapsulated Entity POJOs (User, Component)
├── dao/          # Interface concrete query drivers (ComponentDAO, UserDAO)
└── view/         # Interactive JFrame view windows (LoginFrame, MainDashboard, RegisterFrame)
