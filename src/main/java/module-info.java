module com.example.javafxconsulta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxconsulta to javafx.fxml;
    exports com.example.javafxconsulta;
}