module com.example.modul6_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modul6_final to javafx.fxml;
    exports com.example.modul6_final;
}