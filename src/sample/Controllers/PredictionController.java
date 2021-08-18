package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PredictionController implements Initializable {

    @FXML
    private ChoiceBox<String> Countychoice;
    @FXML
    private Slider Yearslider;
    @FXML
    private TextField Ntext;
    @FXML
    private TextField Ptext;
    @FXML
    private TextField Ktext;
    @FXML
    private TextField GrossAreatext;
    @FXML
    private TextField NetAreatext;
    @FXML
    private TextField Ctext;
    @FXML
    private Label yearDisplayLabel;

    @FXML
    private Label nlabel;
    @FXML
    private Label plabel;
    @FXML
    private Label klabel;

    @FXML
    private BarChart<String,Number> predictionChart;
    @FXML
    private CategoryAxis xaxis;
    @FXML
    private NumberAxis yaxis;

    private String[] countyNames  = {"Alabama","Arizona","Arkansas","California","Georgia","Louisiana","Mississippi","Missouri","New Mexico","North Carolina","Oklahoma","South Carolina","Tennessee","Texas"};

    private String output,input1,input2,input3,input4,input5,input6,input7,input8;

    private HashMap<String, String> map = new HashMap<>();

    XYChart.Series series1 = new XYChart.Series(), series2 = new XYChart.Series(), series3 = new XYChart.Series();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeCollection();

        Countychoice.getItems().addAll(countyNames);
        Countychoice.setOnAction(this::selectCounty);

        Yearslider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int year = (int)Yearslider.getValue();
                input2 = Integer.toString(year);
                yearDisplayLabel.setText(input2);
            }
        });
        series1.setName("Nitrogen");
        series2.setName("Phosphorus");
        series3.setName("Potassium");
    }

    public void initializeCollection(){
        map.put("Alabama","1");
        map.put("Arizona","2");
        map.put("Arkansas","3");
        map.put("California","4");
        map.put("Georgia","5");
        map.put("Louisiana","6");
        map.put("Mississippi","7");
        map.put("Missouri","8");
        map.put("New Mexico","9");
        map.put("North Carolina","10");
        map.put("Oklahoma","11");
        map.put("South Carolina","12");
        map.put("Tennessee","13");
        map.put("Texas","14");
    }

    public void selectCounty(ActionEvent event){
        input1=map.get(Countychoice.getValue());
    }

    public void predictButtonOnAction(ActionEvent event){
        predict();
    }

    public void predict(){
        input3 = Ntext.getText(); input4 = Ptext.getText();
        input5 = Ktext.getText(); input6 = GrossAreatext.getText();
        input7 = NetAreatext.getText(); input8 = Ctext.getText();

        StringBuffer outputValues = new StringBuffer();

        try{
            Process process = Runtime.getRuntime().exec("python -u C:/Users/prash/Desktop/DataScienceProjects/FertilizerRecommendationandYield/project.py "+input1+" "+
                    input2+" "+input3+" "+input4+" "+input5+" "+input6+" "+input7+" "+input8);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            DecimalFormat df = new DecimalFormat("#.##");
            int i=0;

            while ((output = stdInput.readLine()) != null) {
                    outputValues.append(output);
                    String[] outputString = (outputValues.delete(0,2).delete(outputValues.length()-2,
                            outputValues.length())).toString().split(" ");
                    System.out.print(outputString[0]);

                    Double nVal=0.0,pVal=0.0,kVal=0.0;
                    try {
                        nVal = Double.parseDouble(outputString[0]);
                        pVal = Double.parseDouble(outputString[1]);
                        kVal = Double.parseDouble(outputString[2]);
                    }
                    catch(NumberFormatException e) {
                        e.printStackTrace();
                    }

                    float[] outputSeries = {Float.parseFloat(df.format(nVal)), Float.parseFloat(df.format(pVal)),
                            Float.parseFloat(df.format(kVal))};
                    series1.getData().add(new XYChart.Data("-",outputSeries[0]));
                    series2.getData().add(new XYChart.Data("-",outputSeries[1]));
                    series3.getData().add(new XYChart.Data("-",outputSeries[2]));

                    nlabel.setText("Nitrogen: "+outputSeries[0]+" lb/acre");
                    plabel.setText("Phosphorus: "+outputSeries[1]+" lb/acre");
                    klabel.setText("Potash: "+outputSeries[2]+" lb/acre");

                    predictionChart.setDisable(false);
                    predictionChart.getData().addAll(series1,series2,series3);
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.exit(-1);
        }
    }

    public void goToIndex(ActionEvent event) throws IOException{
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
