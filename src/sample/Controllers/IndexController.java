package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    public void goToPredictor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/estimatorView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("JavaFx");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public void goToModel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/modelView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("JavaFx");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public void exitButtonOnAction(ActionEvent event){
        Stage discardStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        discardStage.close();
    }
}
