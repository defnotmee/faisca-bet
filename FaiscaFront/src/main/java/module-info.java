module com.fazol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.net.http;

    opens com.fazol to javafx.fxml;
    exports com.fazol;
}
