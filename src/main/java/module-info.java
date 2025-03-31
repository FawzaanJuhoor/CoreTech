module com.coretech.coretech {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;
    requires jbcrypt;

    opens com.coretech.coretech to javafx.fxml;
    opens Models to javafx.base, javafx.fxml;
    exports com.coretech.coretech;
}