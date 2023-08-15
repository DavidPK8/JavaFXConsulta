package com.example.javafxconsulta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class Usuarios {

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    private TextField textfield4;

    @FXML
    private TextField textfield5;

    static final String DB_URL = "jdbc:mysql://localhost/usuarios";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    @FXML
    void ClearButton(ActionEvent event) {
        textfield1.setText("");
        textfield2.setText("");
        textfield3.setText("");
        textfield4.setText("");
        textfield5.setText("");
    }

    @FXML
    void Createbutton(ActionEvent event) {
        String CODIGOF = textfield1.getText();
        String NOMBREF = textfield2.getText();
        String CEDULAF = textfield3.getText();
        String NACIMIENTOF = textfield4.getText();
        String EDADF = textfield5.getText();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ) {
            String sql = "INSERT INTO USUARIOS (Codigo, Nombre, Cedula, Nacimiento, Edad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try {
                preparedStatement.setString(1, CODIGOF);
                preparedStatement.setString(2, NOMBREF);
                preparedStatement.setString(3, CEDULAF);
                preparedStatement.setString(4, NACIMIENTOF);
                preparedStatement.setString(5, EDADF);
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Registro insertado con éxito.");
                } else {
                    System.out.println("Error al insertar el registro.");
                }
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }
    }

    @FXML
    void Deletebutton(ActionEvent event) {
        String CodigoRegistroABorrar = textfield1.getText();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "DELETE FROM Usuarios WHERE Codigo = " + CodigoRegistroABorrar;
            int filasAfectadas = stmt.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
    }

    @FXML
    void UpdateButton(ActionEvent event) {
        String CODIGON = textfield1.getText();
        String NOMBREN = textfield2.getText();
        String CEDULAN = textfield3.getText();
        String NACIMIENTON = textfield4.getText();
        String EDADN = textfield5.getText();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ){
            String sql = "UPDATE USUARIOS SET Nombre = ?, Cedula = ?, Nacimiento = ?, Edad = ? WHERE Codigo = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, NOMBREN);
            preparedStatement.setString(2, CEDULAN);
            preparedStatement.setString(3, NACIMIENTON);
            preparedStatement.setString(4, EDADN);
            preparedStatement.setString(5, CODIGON);

            int filasAfectadas = ((PreparedStatement) preparedStatement).executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro actualizado con éxito.");
            } else {
                System.out.println("No se encontró el registro con el ID especificado.");
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e3) {
            e3.printStackTrace();
        }
    }

}
