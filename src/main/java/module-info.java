module com.example.javafxconsulta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxconsulta to javafx.fxml;
    exports com.example.javafxconsulta;
}