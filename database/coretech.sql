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
    CustomerID INT DEFAULT seq_customer.NEXTVAL PRIMARY KEY,
    CustomerName VARCHAR(50) NOT NULL,
    PhoneNo NUMBER(10,0),
    EmailID VARCHAR(100) UNIQUE,
    Address VARCHAR(255)
);

CREATE OR REPLACE PROCEDURE InsertCustomer(
    p_CustomerName IN VARCHAR2,
    p_PhoneNo IN VARCHAR2,
    p_EmailID IN VARCHAR2,
    p_Address IN VARCHAR2
)
AS
BEGIN
    INSERT INTO Customer (CustomerName, PhoneNo, EmailID, Address)
    VALUES (p_CustomerName, p_PhoneNo, p_EmailID, p_Address);

    COMMIT; 
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END InsertCustomer;
/

CREATE OR REPLACE PROCEDURE UpdateCustomer(
    p_CustomerName IN VARCHAR2,
    p_PhoneNo IN VARCHAR2,
    p_EmailID IN VARCHAR2,
    p_Address IN VARCHAR2
)
AS
BEGIN
    UPDATE Customer 
    SET CustomerName = p_CustomerName,EmailID = p_EmailID, Address = p_Address
    WHERE PhoneNo = p_PhoneNo;

    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateCustomer;
/

CREATE OR REPLACE PROCEDURE DeleteCustomer(
    p_EmailID IN VARCHAR2
)
AS
BEGIN
    DELETE FROM Customer WHERE EmailID = p_EmailID;

    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END DeleteCustomer;
/


CREATE OR REPLACE PROCEDURE SearchCustomer(
    p_CustomerName OUT VARCHAR2,
    p_EmailID      IN  VARCHAR2,
    p_PhoneNo      OUT VARCHAR2,
    p_Address      OUT VARCHAR2
)
AS
BEGIN
    SELECT CustomerName, PhoneNo, Address
    INTO p_CustomerName, p_PhoneNo, p_Address
    FROM Customer
    WHERE EmailID = p_EmailID;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_CustomerName := NULL;
        p_PhoneNo := NULL;
        p_Address := NULL;
END SearchCustomer;
/


CREATE TABLE Vehicle (
    VehicleID INT DEFAULT seq_vehicle.NEXTVAL PRIMARY KEY,
    CustomerID INT,
    Make VARCHAR(100),
    Model VARCHAR(100),
    Year NUMBER(4),
    VIN CHAR(17) UNIQUE,
    ServiceHistory VARCHAR(255),
    CONSTRAINT fk_vehicle_customer FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

CREATE OR REPLACE FUNCTION GetCustomerIdByEmail(p_email VARCHAR2)
RETURN NUMBER
IS
    v_customer_id NUMBER;
BEGIN
    SELECT CustomerID INTO v_customer_id
    FROM Customer
    WHERE EmailID = p_email;

    RETURN v_customer_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN -1;
END;
/



CREATE OR REPLACE PROCEDURE InsertVehicle(
    p_CustomerID IN NUMBER,
    p_Make IN VARCHAR2,
    p_Model IN VARCHAR2,
    p_Year IN NUMBER,
    p_VIN IN CHAR,
    p_ServiceHistory IN VARCHAR2
)
AS
BEGIN
    INSERT INTO Vehicle (CustomerID, Make, Model, Year, VIN, ServiceHistory)
    VALUES (p_CustomerID, p_Make, p_Model, p_Year, p_VIN, p_ServiceHistory);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE UpdateVehicle(
    p_VIN             IN VARCHAR2,
    p_CustomerID      IN NUMBER,
    p_Make            IN VARCHAR2,
    p_Model           IN VARCHAR2,
    p_Year            IN NUMBER,
    p_ServiceHistory  IN VARCHAR2
)
IS
BEGIN
    UPDATE Vehicle
    SET
        CustomerID = p_CustomerID,
        Make = p_Make,
        Model = p_Model,
        Year = p_Year,
        ServiceHistory = p_ServiceHistory
    WHERE VIN = p_VIN;
 
    IF SQL%ROWCOUNT > 0 THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE GetVehicleByVIN (
    p_vin IN Vehicle.VIN%TYPE,
    p_customer_id OUT Vehicle.CustomerID%TYPE,
    p_make OUT Vehicle.Make%TYPE,
    p_model OUT Vehicle.Model%TYPE,
    p_year OUT Vehicle.Year%TYPE,
    p_service_history OUT Vehicle.ServiceHistory%TYPE
)
IS
BEGIN
    SELECT CustomerID, Make, Model, Year, ServiceHistory
    INTO p_customer_id, p_make, p_model, p_year, p_service_history
    FROM Vehicle
    WHERE VIN = p_vin;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_customer_id := NULL;
        p_make := NULL;
        p_model := NULL;
        p_year := NULL;
        p_service_history := NULL;
END;
/

CREATE OR REPLACE FUNCTION GetEmailByCustomerId (
    p_customer_id IN Customer.CustomerID%TYPE
) RETURN VARCHAR2
IS
    v_email Customer.EmailID%TYPE;
BEGIN
    SELECT EmailID INTO v_email FROM Customer WHERE CustomerID = p_customer_id;
    RETURN v_email;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL;
END;
/


CREATE OR REPLACE PROCEDURE DeleteVehicleByVIN (
    p_vin IN Vehicle.VIN%TYPE
)
AS
BEGIN
    DELETE FROM Vehicle WHERE VIN = p_vin;
    
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Vehicle not found');
    END IF;
    
    COMMIT;
END;
/

--Add Sales or Admin User
CREATE OR REPLACE PROCEDURE ADD_SYSTEM_USER (
    p_username IN VARCHAR2,
    p_phone    IN NUMBER,
    p_email    IN VARCHAR2,
    p_password IN VARCHAR2,
    p_role     IN VARCHAR2
)
AS
BEGIN
    INSERT INTO SystemUser (UserName, PhoneNo, EmailID, password, Role)
    VALUES (p_username, p_phone, p_email, p_password, p_role);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Error inserting system user: ' || SQLERRM);
END;
/

--get all system users
CREATE OR REPLACE PROCEDURE GET_ALL_SYSTEM_USERS (
    p_cursor OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_cursor FOR
        SELECT USERID, USERNAME, PHONENO, ROLE
        FROM SYSTEMUSER;
END;
/

--Update system user by id
CREATE OR REPLACE PROCEDURE UPDATE_USER_BY_USERNAME (
    p_username IN VARCHAR2,
    p_phone    IN NUMBER,
    p_email    IN VARCHAR2,
    p_password IN VARCHAR2,
    p_role     IN VARCHAR2
)
AS
BEGIN
    UPDATE SystemUser
    SET
        PhoneNo  = p_phone,
        EmailID  = p_email,
        Password = p_password,
        Role     = p_role
    WHERE LOWER(UserName) = LOWER(p_username);  -- ✅ Important: match case-insensitively

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'User not found with given username.');
    END IF;

    COMMIT;
END;
/


--Delete system user by name
CREATE OR REPLACE PROCEDURE DELETE_USER_BY_USERNAME (
    p_username IN VARCHAR2
)
AS
BEGIN
    DELETE FROM SystemUser
    WHERE LOWER(UserName) = LOWER(p_username);

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No user found with that username.');
    END IF;

    COMMIT;
END;
/




CREATE TABLE Mechanic (
    MechanicID INT DEFAULT seq_mechanic.NEXTVAL PRIMARY KEY,
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


