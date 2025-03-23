module com.coretech.coretech {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens com.coretech.coretech to javafx.fxml;
    exports com.coretech.coretech;
}