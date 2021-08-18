package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    private Parent root;
    private Stage signupStage;
    private Scene scene;

    public void loginButtonOnAction(ActionEvent event) throws IOException{

        if(usernameTextField.getText().isBlank()==false&&passwordPasswordField.getText().isBlank()==false){

            validateLogin(event);

        }
        else{
            loginMessageLabel.setText("Enter UserName and Password");
        }
    }

    public void cancelButtonOnAction(){
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(ActionEvent event) throws IOException{
        String UserName = usernameTextField.getText();
        String Password = passwordPasswordField.getText();
        if (UserName.equals("Admin") && Password.equals("1234")) {
            createAccountForm(event);
        }
        else {
            loginMessageLabel.setText("INVALID USER");
        }
    }
    public void createAccountForm(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Views/index.fxml"));
        signupStage=new Stage();
        signupStage.setTitle("JavaFx");
        scene = new Scene(root);
        signupStage.setScene(scene);
        signupStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        signupStage.setX((primScreenBounds.getWidth() - signupStage.getWidth()) / 2);
        signupStage.setY((primScreenBounds.getHeight() - signupStage.getHeight()) / 2);

        Stage discardStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        discardStage.close();
    }
}





