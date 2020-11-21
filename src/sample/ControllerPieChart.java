package sample;

import com.opencsv.CSVReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ControllerPieChart implements Initializable {

    @FXML
    private BarChart<?, ?> nbChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addChar();
        addChar2();
        addChar3();
    }

    public void addChar(){
        try {
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data("BUS", AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("bus.csv")))),30));
            nbChart.getData().addAll(set1);
        }catch (Exception e){
            new Exception();
        }
    }

    public void addChar2(){
        try {
            XYChart.Series set2 = new XYChart.Series<>();
            set2.getData().add(new XYChart.Data("TRAIN", AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("train.csv")))),1));
            nbChart.getData().addAll(set2);
        }catch (Exception e){
            new Exception();
        }
    }

    public void addChar3(){
        try {
            XYChart.Series set3 = new XYChart.Series<>();
            set3.getData().add(new XYChart.Data("CAR", AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("car.csv")))),1));
            nbChart.getData().addAll(set3);
        }catch (Exception e){
            new Exception();
        }
    }

    public int AllTrajet(CSVReader csvReader) throws IOException {
        int nb=0;
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            nb++;
        }return nb;
    }

}
