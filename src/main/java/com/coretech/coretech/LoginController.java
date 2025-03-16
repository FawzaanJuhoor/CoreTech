package com.coretech.coretech;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
public class LoginController {

    @FXML
    private TextField userIdTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Text welcomeText;

    @FXML
    private ImageView logoImageView;
}
