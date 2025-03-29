SET SERVEROUTPUT ON;
-- ========================
-- DROP SEQUENCES
-- ========================
DROP SEQUENCE seq_user;
DROP SEQUENCE seq_customer;
DROP SEQUENCE seq_vehicle;
DROP SEQUENCE seq_mechanic;
DROP SEQUENCE seq_appointment;
DROP SEQUENCE seq_invoice;
DROP SEQUENCE seq_payment;
DROP SEQUENCE seq_log;
DROP SEQUENCE seq_item;

-- ========================
-- DROP TABLES
-- ========================
DROP TABLE SystemUser CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Vehicle CASCADE CONSTRAINTS;
DROP TABLE Mechanic CASCADE CONSTRAINTS;
DROP TABLE ServiceAppointment CASCADE CONSTRAINTS;
DROP TABLE Invoice CASCADE CONSTRAINTS;
DROP TABLE Payment CASCADE CONSTRAINTS;
DROP TABLE AuditLog CASCADE CONSTRAINTS;
DROP TABLE Inventory CASCADE CONSTRAINTS;
DROP TABLE ServiceInventory CASCADE CONSTRAINTS;

-- ========================
-- SEQUENCES
-- ========================
CREATE SEQUENCE seq_user START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_customer START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_vehicle START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_mechanic START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_appointment START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_invoice START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_payment START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_log START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_item START WITH 1 INCREMENT BY 1;

-- ========================
-- TABLES WITH CONSTRAINTS
-- ========================
CREATE TABLE SystemUser (
    UserID INT DEFAULT seq_user.NEXTVAL PRIMARY KEY,
    UserName VARCHAR2(50) NOT NULL,
    PhoneNo NUMBER(10,0),
    EmailID VARCHAR2(100),
    password VARCHAR2(255) NOT NULL,
    Role VARCHAR2(20) CHECK (Role IN ('Admin', 'Sales Representative')) NOT NULL
);

INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'trusha', 9876543210, 'trusha@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Admin');
INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'bhakti', 9876543210, 'bhakti@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Admin');
INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'frin', 9876543210, 'frin@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Admin');
INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'fawzzan', 9876543210, 'fawzzan@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Admin');
        
INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'abc', 9876543210, 'abc@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Sales Representative');
INSERT INTO SystemUser (UserID, UserName, PhoneNo, EmailID, Password, Role) 
VALUES (seq_user.NEXTVAL, 'xyz', 9876543210, 'xyz@example.com', 
        '$2a$12$SiuF4D3vGru4GSXi2j/id.aW6ggvC3xWKLnVffXDZXiCMT9uAw66S', 'Sales Representative');
commit;

CREATE OR REPLACE PROCEDURE GET_USER_PASSWORD_ROLE(
    p_username IN VARCHAR2,
    p_password OUT VARCHAR2,
    p_role OUT VARCHAR2
) AS
BEGIN
    SELECT Password, Role INTO p_password, p_role
    FROM SystemUser 
    WHERE UserName = p_username;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_password := NULL;
        p_role := NULL;
END;
/


DROP PROCEDURE GET_USER_PASSWORD;

CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR(50) NOT NULL,
    PhoneNo NUMBER(10,0),
    EmailID VARCHAR(100),
    Address VARCHAR(255)
);

CREATE TABLE Vehicle (
    VehicleID INT PRIMARY KEY,
    CustomerID INT,
    Make VARCHAR(100),
    Model VARCHAR(100),
    Year NUMBER(4),
    VIN CHAR(17),
    ServiceHistory VARCHAR(255),
    CONSTRAINT fk_vehicle_customer FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

CREATE TABLE Mechanic (
    MechanicID INT PRIMARY KEY,
    MechanicName VARCHAR(50) NOT NULL,
    Expertise VARCHAR(255),
    PhoneNo NUMBER(10,0),
    EmailID VARCHAR(100)
);



CREATE TABLE ServiceAppointment (
    AppointmentID INT PRIMARY KEY,
    VehicleID INT,
    MechanicID INT,
    UserID INT,
    ServiceType VARCHAR(100),
    ServiceDate DATE,
    ServiceStatus VARCHAR(20),
    CONSTRAINT fk_appointment_vehicle FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID),
    CONSTRAINT fk_appointment_mechanic FOREIGN KEY (MechanicID) REFERENCES Mechanic(MechanicID),
    CONSTRAINT fk_appointment_user FOREIGN KEY (UserID) REFERENCES SystemUser(UserID)
);

CREATE TABLE Invoice (
    InvoiceID INT PRIMARY KEY,
    AppointmentID INT,
    Amount NUMBER(8,2),
    PaymentStatus VARCHAR(20),
    GeneratedDate DATE,
    CONSTRAINT fk_invoice_appointment FOREIGN KEY (AppointmentID) REFERENCES ServiceAppointment(AppointmentID)
);

CREATE TABLE Payment (
    PaymentID INT PRIMARY KEY,
    InvoiceID INT,
    PaymentMethod VARCHAR(20),
    AmountPaid NUMBER(8,2),
    PaymentDate DATE,
    CONSTRAINT fk_payment_invoice FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID)
);

CREATE TABLE AuditLog (
    LogID INT PRIMARY KEY,
    UserID INT,
    Action VARCHAR(50),
    TimeStamp TIMESTAMP,
    CONSTRAINT fk_log_user FOREIGN KEY (UserID) REFERENCES SystemUser(UserID)
);

CREATE TABLE Inventory (
    ItemID INT PRIMARY KEY,
    ItemName VARCHAR(50),
    Quantity INT,
    Price NUMBER(8,2),
    MinStockLevel INT,
    UpdatedDate TIMESTAMP
);

CREATE TABLE ServiceInventory (
    AppointmentID INT,
    ItemID INT,
    PRIMARY KEY (AppointmentID, ItemID),
    CONSTRAINT fk_service_inventory_appointment FOREIGN KEY (AppointmentID) REFERENCES ServiceAppointment(AppointmentID),
    CONSTRAINT fk_service_inventory_item FOREIGN KEY (ItemID) REFERENCES Inventory(ItemID)
);
