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

CREATE OR REPLACE PROCEDURE GetAllVehicles(p_cursor OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_cursor FOR
        SELECT 
            v.VehicleID,
            v.VIN,
            v.CustomerID,
            c.EmailID,
            v.Make,
            v.Model,
            v.Year,
            v.ServiceHistory
        FROM Vehicle v
        JOIN Customer c ON v.CustomerID = c.CustomerID;
END;
/

CREATE OR REPLACE PROCEDURE GetVehiclesByEmail(
    p_email IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
)
AS
    v_customer_id INT;
BEGIN
    -- Get Customer ID
    SELECT CustomerID INTO v_customer_id FROM Customer WHERE EmailID = p_email;
    
    -- Fetch associated vehicles
    OPEN p_cursor FOR
    SELECT VehicleID, Make, Model, Year
    FROM Vehicle
    WHERE CustomerID = v_customer_id;
END;
/

CREATE OR REPLACE PROCEDURE GetVehicleByID(
    p_vehicle_id IN INT,
    p_customer_id OUT INT,
    p_make OUT VARCHAR2,
    p_model OUT VARCHAR2,
    p_year OUT INT,
    p_vin OUT CHAR,
    p_service_history OUT VARCHAR2
)
AS
BEGIN
    SELECT CustomerID, Make, Model, Year, VIN, ServiceHistory
    INTO p_customer_id, p_make, p_model, p_year, p_vin, p_service_history
    FROM Vehicle
    WHERE VehicleID = p_vehicle_id;
END;
/


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
    EmailID VARCHAR(100) UNIQUE
);

CREATE OR REPLACE PROCEDURE InsertSampleMechanics AS
BEGIN
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('John Carter', 'Engine Repair', 9876543210, 'john.carter@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Emily Davis', 'Transmission Systems', 9123456780, 'emily.davis@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Michael Smith', 'Brake Systems', 9988776655, 'michael.smith@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Sarah Johnson', 'Electrical Systems', 9765432101, 'sarah.johnson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('David Wilson', 'Air Conditioning', 9090909090, 'david.wilson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Laura Brown', 'Suspension and Steering', 9812345678, 'laura.brown@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('James Miller', 'Fuel Systems', 9944221100, 'james.miller@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Olivia Taylor', 'Hybrid Vehicles', 9001122334, 'olivia.taylor@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Daniel Moore', 'Clutch and Gearbox', 9345678923, 'daniel.moore@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Sophia Anderson', 'Bodywork and Painting', 9871212121, 'sophia.anderson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Christopher Thomas', 'Wheel Alignment', 9012345678, 'chris.thomas@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Isabella Jackson', 'Cooling Systems', 9888877665, 'isabella.jackson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Matthew White', 'Engine Diagnostics', 9776655443, 'matthew.white@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Ava Harris', 'Emission Control', 9665544332, 'ava.harris@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Joshua Martin', 'Diesel Engines', 9554433221, 'joshua.martin@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Grace Thompson', 'Electric Vehicles', 9898989898, 'grace.thompson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Andrew Garcia', 'Hydraulics', 9321654789, 'andrew.garcia@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Chloe Martinez', 'Tire and Rim Repair', 9111223344, 'chloe.martinez@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Ethan Robinson', 'Auto Electricals', 9223344556, 'ethan.robinson@example.com');
    INSERT INTO Mechanic (MechanicName, Expertise, PhoneNo, EmailID) VALUES ('Lily Clark', 'Interior Repairs', 9334455667, 'lily.clark@example.com');

    COMMIT;
END InsertSampleMechanics;
/

BEGIN
    InsertSampleMechanics;
END;
/

CREATE OR REPLACE PROCEDURE get_all_mechanics (
    p_mechanics OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_mechanics FOR
        SELECT MechanicID, MechanicName, Expertise FROM Mechanic;
END;
/



CREATE TABLE ServiceAppointment (
    AppointmentID INT DEFAULT seq_appointment.NEXTVAL PRIMARY KEY,
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

CREATE OR REPLACE PROCEDURE GetVehicleIdByVIN (
    p_VIN IN VARCHAR2,           -- Input parameter for VIN
    p_VehicleID OUT INT          -- Output parameter for VehicleID
)
AS
BEGIN
    -- Query to select VehicleID based on VIN
    SELECT VehicleID
    INTO p_VehicleID
    FROM Vehicle
    WHERE VIN = p_VIN;

EXCEPTION
    -- If no data is found for the VIN, handle the exception
    WHEN NO_DATA_FOUND THEN
        p_VehicleID := -1;  -- Return -1 if VIN not found
    WHEN OTHERS THEN
        -- Handle other exceptions
        p_VehicleID := -1;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE InsertAppointment (
    p_VehicleID IN INT,                 -- Input parameter for VehicleID
    p_MechanicID IN INT,                -- Input parameter for MechanicID
    p_UserID IN INT,                    -- Input parameter for UserID
    p_ServiceType IN VARCHAR2,          -- Input parameter for ServiceType
    p_ServiceDate IN DATE,              -- Input parameter for ServiceDate
    p_ServiceStatus IN VARCHAR2         -- Input parameter for ServiceStatus
)
AS
BEGIN
    -- Insert the appointment into ServiceAppointment table
    INSERT INTO ServiceAppointment (
        VehicleID, MechanicID, UserID, ServiceType, ServiceDate, ServiceStatus
    ) VALUES (
        p_VehicleID, p_MechanicID, p_UserID, p_ServiceType, p_ServiceDate, p_ServiceStatus
    );

    -- Commit the transaction
    COMMIT;

EXCEPTION
    -- Exception handling if anything goes wrong
    WHEN OTHERS THEN
        -- Rollback in case of error
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END InsertAppointment;
/


CREATE OR REPLACE PROCEDURE get_appointment_by_id (
    p_appointment_id IN ServiceAppointment.AppointmentID%TYPE,
    p_vin OUT Vehicle.VIN%TYPE,
    p_service_type OUT ServiceAppointment.ServiceType%TYPE,
    p_service_date OUT ServiceAppointment.ServiceDate%TYPE,
    p_status OUT ServiceAppointment.ServiceStatus%TYPE,
    p_mechanic_id OUT ServiceAppointment.MechanicID%TYPE
)
AS
BEGIN
    SELECT v.VIN, a.ServiceType, a.ServiceDate, a.ServiceStatus, a.MechanicID
    INTO p_vin, p_service_type, p_service_date, p_status, p_mechanic_id
    FROM ServiceAppointment a
    JOIN Vehicle v ON a.VehicleID = v.VehicleID
    WHERE a.AppointmentID = p_appointment_id;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_vin := NULL;
        p_service_type := NULL;
        p_service_date := NULL;
        p_status := NULL;
        p_mechanic_id := NULL;
END;
/

CREATE OR REPLACE PROCEDURE update_appointment_by_id (
    p_appointment_id IN ServiceAppointment.AppointmentID%TYPE,
    p_service_type   IN ServiceAppointment.ServiceType%TYPE,
    p_service_date   IN ServiceAppointment.ServiceDate%TYPE,
    p_status         IN ServiceAppointment.ServiceStatus%TYPE,
    p_mechanic_id    IN ServiceAppointment.MechanicID%TYPE
)
AS
BEGIN
    UPDATE ServiceAppointment
    SET ServiceType = p_service_type,
        ServiceDate = p_service_date,
        ServiceStatus = p_status,
        MechanicID = p_mechanic_id
    WHERE AppointmentID = p_appointment_id;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in update_appointment_by_id: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE delete_appointment_by_id (
    p_appointment_id IN ServiceAppointment.AppointmentID%TYPE
)
AS
BEGIN
    DELETE FROM ServiceAppointment
    WHERE AppointmentID = p_appointment_id;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in delete_appointment_by_id: ' || SQLERRM);
END;
/



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

INSERT INTO Inventory (ItemID, ItemName, Quantity, Price, MinStockLevel, UpdatedDate) 
VALUES (1, 'Oil Filter', 100, 15.50, 25, TIMESTAMP '2025-04-01 09:30:00');

INSERT INTO Inventory (ItemID, ItemName, Quantity, Price, MinStockLevel, UpdatedDate) 
VALUES (2, 'Brake Pads', 75, 35.00, 20, TIMESTAMP '2025-04-03 14:15:00');


CREATE TABLE ServiceInventory (
    AppointmentID INT,
    ItemID INT,
    PRIMARY KEY (AppointmentID, ItemID),
    CONSTRAINT fk_service_inventory_appointment FOREIGN KEY (AppointmentID) REFERENCES ServiceAppointment(AppointmentID),
    CONSTRAINT fk_service_inventory_item FOREIGN KEY (ItemID) REFERENCES Inventory(ItemID)
);


-- Procedure for monthly report of inventory
CREATE OR REPLACE PROCEDURE Get_Inventory_Summary (
    p_cursor OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_cursor FOR
        SELECT ItemName, Quantity, MinStockLevel
        FROM Inventory;
END;
/

-- Test for Get_Inventory_Summary
SET SERVEROUTPUT ON;
DECLARE
    inv_cursor SYS_REFCURSOR;
    v_itemname Inventory.ItemName%TYPE;
    v_quantity Inventory.Quantity%TYPE;
    v_minstock Inventory.MinStockLevel%TYPE;
BEGIN
    Get_Inventory_Summary(inv_cursor);

    LOOP
        FETCH inv_cursor INTO v_itemname, v_quantity, v_minstock;
        EXIT WHEN inv_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Item: ' || v_itemname || ', Qty: ' || v_quantity || ', MinStock: ' || v_minstock);
    END LOOP;

    CLOSE inv_cursor;
END;
/


-- Procedure to servicing monthly report
CREATE OR REPLACE PROCEDURE Get_Monthly_Service_Report (
    p_cursor OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_cursor FOR
        SELECT c.CustomerName,
               v.Make || ' ' || v.Model AS VehicleName,
               s.ServiceType
        FROM ServiceAppointment s
        JOIN Vehicle v ON s.VehicleID = v.VehicleID
        JOIN Customer c ON v.CustomerID = c.CustomerID
        ORDER BY s.ServiceDate;
END;
/

-- Test the procedure
SET SERVEROUTPUT ON;
DECLARE
    service_cursor SYS_REFCURSOR;
    v_customer_name Customer.CustomerName%TYPE;
    v_vehicle_name VARCHAR2(200);
    v_service_type ServiceAppointment.ServiceType%TYPE;
BEGIN
    Get_Monthly_Service_Report(service_cursor);

    LOOP
        FETCH service_cursor INTO v_customer_name, v_vehicle_name, v_service_type;
        EXIT WHEN service_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer: ' || v_customer_name || ', Vehicle: ' || v_vehicle_name || ', Service: ' || v_service_type);
    END LOOP;

    CLOSE service_cursor;
END;
/