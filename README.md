# 🚗 CoreTech Auto Care- Vehicle Maintenance & Service Management System

Welcome to the **CoreTech** project Wiki!  
This system is a Java-based Vehicle Maintenance & Service Management solution, streamlining operations for automotive service centers.

It features:
- A **JavaFX** user interface
- An **Oracle** database using **PL/SQL stored procedures** and **triggers**

> 🎓 This project aligns with academic requirements for:
> - `ITE5220 - PL/SQL Oracle Database Programming`
> - `ITE5215 - Advanced Java SE Application Development`  
> at **Humber Polytechnic**.

---

## 📌 Project Overview

### 🎯 Purpose
CoreTech AutoCare aims to provide an efficient system for managing:
- Customer appointments
- Service history
- Inventory tracking


---

## ⚙️ Key Functionalities

- **JavaFX-Based UI**: Modern, intuitive interface
- **Dynamic Displays**: Real-time updates of schedules, bills & inventory
- **Customer Interaction**: Book appointments, view history, make payments
- **Responsive Design**: Optimized for various screen resolutions
- **PL/SQL Procedures**: Manage records and transactions
- **Triggers**: Automate inventory alerts, service reminders
- **Exception Handling**: Rollback mechanisms and validations
- **Role-Based Access Control**: Admins, mechanics, and customers
- **Inventory Management**: Track, alert, and update stock levels
- **Appointment Scheduling**: Notifications and calendar-ready flow
- **Secure Transactions**: Data integrity with backend encryption

---

## 🎯 Project Objectives

- Centralized Oracle database for all key records
- JavaFX frontend for interactive user experience
- Efficient PL/SQL logic for data consistency
- Security with audit logging
- Generate detailed invoices via stored functions

---


## 📁 Project Structure

The project follows a Maven-based structure, organizing source code, resources, and database scripts into logical directories:

- `CoreTech/` *(Root Directory)*
  - `mvnw`, `mvnw.cmd` – Maven wrapper scripts for Windows and Unix
  - `pom.xml` – Maven configuration file for dependencies and build settings
  - `.gitignore` – Specifies files/folders to be excluded from version control
  - `database/`
    - `coretech.sql` – SQL script to create and populate the Oracle database schema
  - `Images/`
    - `WhiteVersion.png`, `BlackVersion.png` – Project logos (light and dark versions)
    - `icons/` – UI icon assets used in the JavaFX frontend
      - `appointment.png`
      - `car.png`
      - `logout.png`
      - _... (other icons)_
  - `src/`
    - `main/`
      - `java/`
        - `com/coretech/coretech/`
          - `App.java` – Main application launcher (e.g., `HelloApplication.java`)
          - `controllers/` – JavaFX controllers
            - `LoginController.java`
            - `AppointmentController.java`
            - `DashboardController.java`
            - _..._
          - `models/` – Java entity/model classes
            - `Customer.java`
            - `Vehicle.java`
            - `Appointment.java`
            - `Invoice.java`
            - _..._
          - `db/` – Data Access Object (DAO) classes
            - `CustomerDAO.java`
            - `VehicleDAO.java`
            - `AppointmentDAO.java`
            - _..._
          - `util/` – Utility classes
            - `PasswordUtil.java`
            - `ValidatorUtil.java`
        - `resources/`
          - `fxml/` – JavaFX UI layout files
            - `Login.fxml`
            - `Dashboard.fxml`
            - `AddAppointment.fxml`
            - _..._
          - `styles/`
            - `main.css` – Styling for JavaFX components
          - `images/` – Additional images used in UI
  - `target/` *(Generated after Maven build - ignored by Git)*
    - `classes/` – Compiled `.class` files and copied resources
      - Mirrors structure of `src/main/java` and `src/main/resources`



---

## 🎨 Design Mockup

You can explore the UI design mockup via Figma:  
[👉 **View Figma Mockup**](https://www.figma.com/design/t9sFAusrmjo2HL9uO6L4E6/CoreTech?node-id=0-1&t=IuObELlH0I1GOEx9-1)

---


## 🛠️ Technology Stack

| Layer     | Technology         |
|-----------|--------------------|
| Frontend  | JavaFX             |
| Backend  | Oracle 19c + PL/SQL|
| Build     | Maven              |
| Language  | Java (SE)          |

---

## 🚀 Getting Started

1. **Clone the Repo**
   ```bash
   git clone <https://github.com/FawzaanJuhoor/CoreTech.git>

2. **Set Up the Database**

Open Oracle SQL Developer or your preferred tool.

Run the script:

@database/coretech.sql

3. **Build and Run the Application**
