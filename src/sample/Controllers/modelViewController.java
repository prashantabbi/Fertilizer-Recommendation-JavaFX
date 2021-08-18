package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class modelViewController implements Initializable {
    @FXML
    private ImageView graphView;
    Image image1 = new Image(getClass().getResourceAsStream("../Images/graph1.jfif"));
    Image image2 = new Image(getClass().getResourceAsStream("../Images/graph2.jfif"));
    Image image3 = new Image(getClass().getResourceAsStream("../Images/graph3.jfif"));
    Image image4 = new Image(getClass().getResourceAsStream("../Images/graph4.jfif"));

    @FXML
    private Label graphTitle;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphTitle.setOpacity(0.5);
        graphView.setOpacity(0.5);
    }

    public void showHist(){
        graphTitle.setOpacity(1);
        graphView.setOpacity(1);
        graphView.setImage(image1);
        graphTitle.setText("Histogram: Fractional frequency of NPK yields");
    }

    public void showCorr(){
        graphTitle.setOpacity(1);
        graphView.setOpacity(1);
        graphView.setImage(image3);
        graphTitle.setText("Graph: Pearson's coefficient of regression for various fields");
    }

    public void showRel(){
        graphTitle.setOpacity(1);
        graphView.setOpacity(1);
        graphTitle.setText("Graph: Relation between Lint and Nitrogen");
        graphView.setImage(image2);
    }

    public void showOutp(){
        graphTitle.setOpacity(1);
        graphView.setOpacity(1);
        graphTitle.setText("Graph: Modelled on test data");
        graphView.setImage(image4);
    }

    public void goToIndexfromModel(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Views/index.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("JavaFx");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }


}
