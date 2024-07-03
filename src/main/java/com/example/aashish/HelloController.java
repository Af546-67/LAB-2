package com.example.aashish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField uid;
    @FXML
    private TextField uname;
    @FXML
    private TextField uemail;
    @FXML
    private TextField upassword;

    // Database connection
    private Connection conn;
    private DatabaseMetaData DatabaseConnection;

    public void initialize() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createUser() {
        String query = "INSERT INTO tbl_user_login (username, userEmail, userpassword) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, uname.getText());
            stmt.setString(2, uemail.getText());
            stmt.setString(3, upassword.getText());
            stmt.executeUpdate();
            clearFields();
            welcomeText.setText("User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void readUser() {
        int userId = Integer.parseInt(uid.getText());
        String query = "SELECT * FROM tbl_user_login WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                uname.setText(rs.getString("username"));
                uemail.setText(rs.getString("userEmail"));
                upassword.setText(rs.getString("userpassword"));
                welcomeText.setText("User found.");
            } else {
                clearFields();
                welcomeText.setText("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateUser() {
        int userId = Integer.parseInt(uid.getText());
        String query = "UPDATE tbl_user_login SET username = ?, userEmail = ?, userpassword = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, uname.getText());
            stmt.setString(2, uemail.getText());
            stmt.setString(3, upassword.getText());
            stmt.setInt(4, userId);
            stmt.executeUpdate();
            clearFields();
            welcomeText.setText("User updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteUser() {
        int userId = Integer.parseInt(uid.getText());
        String query = "DELETE FROM tbl_user_login WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            clearFields();
            welcomeText.setText("User deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        uid.clear();
        uname.clear();
        uemail.clear();
        upassword.clear();
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
}
