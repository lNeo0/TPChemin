package sample;

import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

public class Controller implements Initializable {

    private int cptD = 0,scale = 15,espace = 15, compteur = 0;
    private String VVdepart,VVarrive;

    Path path = new Path();
    AnchorPane root = new AnchorPane(path);
    HashMap<String, Ville> villeList = new HashMap<>();
    HashMap<String, Ville> AllVilleList = new HashMap<>();
    private List<TrajetSimple> trajetSimpleList = new ArrayList<>();
    private List<TrajetSimple> AllTrajetSimple = new ArrayList<>();


        @FXML
        private AnchorPane AnchorPane;

        // Define TABLE
        @FXML
        private TableView<TrajetSimple> table;
        @FXML
        private TableColumn<TrajetSimple, String> depart = new TableColumn("depart");
        @FXML
        private TableColumn<TrajetSimple, String> arrive = new TableColumn<>("arrive");
        @FXML
        private TableColumn<TrajetSimple, Moyen> moyen = new TableColumn<>("moyen");
        @FXML
        private TableColumn<TrajetSimple, Float> cout = new TableColumn<>("cout");
        @FXML
        private TableColumn<TrajetSimple, Float> duree = new TableColumn<>("duree");
        @FXML
        private TableColumn<TrajetSimple, Float> co2 = new TableColumn<>("co2");
        @FXML
        private TableColumn<TrajetSimple, Float> confort = new TableColumn<>("confort");

        @FXML
         public void openCard(){
                while(cptD>1)cptD--;
                if(cptD==1) {
                    try {
                        Stage primaryStage = new Stage();
                        root.setId("pane");
                        Scene scene1 = new Scene(root, 1000, 1000);
                        scene1.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                        primaryStage.setTitle("Trajet");
                        primaryStage.setScene(scene1);
                        primaryStage.show();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Informations");
                        alert.setHeaderText("Veuillez re-charger le trajet");
                        alert.showAndWait();
                    }
                }else new Alert(Alert.AlertType.ERROR);

        }

        //Define FORM && Critère
        @FXML
        private TextField Vdepart;
        @FXML
        private TextField Varrive;
        @FXML
        private TextField TCout;
        @FXML
        private TextField TTemps;
        @FXML
        private TextField TCO2;
        @FXML
        private TextField Tconfort;



    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void tab(){
        ObservableList<TrajetSimple> trajetSimpleObservableList = FXCollections.observableArrayList();
        ArrayList<TrajetSimple> trajetSimpleList2 = new ArrayList<>();
        trajetSimpleList2.add(BestTrajet());
        trajetSimpleList = trajetSimpleList2;
        trajetSimpleObservableList.addAll(trajetSimpleList);
        depart.setCellValueFactory(new PropertyValueFactory<TrajetSimple,String>("depart"));
        arrive.setCellValueFactory(new PropertyValueFactory<TrajetSimple,String>("arrive"));
        moyen.setCellValueFactory(new PropertyValueFactory<TrajetSimple,Moyen>("moyen"));
        cout.setCellValueFactory(new PropertyValueFactory<TrajetSimple,Float>("cout"));
        duree.setCellValueFactory(new PropertyValueFactory<TrajetSimple,Float>("duree"));
        co2.setCellValueFactory(new PropertyValueFactory<TrajetSimple,Float>("co2"));
        confort.setCellValueFactory(new PropertyValueFactory<TrajetSimple,Float>("confort"));
        table.setItems(trajetSimpleObservableList);
    }

    public void VilleDepartButtonClick() throws IOException {
        cptD++;
        if (compteur >= 1) {
             root = new AnchorPane(path);
        }
        compteur++;
        villeList = new HashMap<>();
        trajetSimpleList = new ArrayList<>();

        VVdepart = Vdepart.getText();
        VVarrive = Varrive.getText();
        //Les points pour les villes
        initVilles(root, VVdepart, VVarrive);
        //System.out.println(AllVilleList);

        if (Vdepart.getText().trim().isEmpty() ||
                Varrive.getText().trim().isEmpty()
               ) {
                    Alert fail = new Alert(Alert.AlertType.WARNING);
                    fail.setHeaderText("Compléter tous les champs !");
                    fail.showAndWait();
        } else {
            int cpt = 0;
            for (Map.Entry<String, Ville> e : villeList.entrySet()) {
                String key = e.getKey();
                if (VVdepart.equals(key) || VVarrive.equals(key)) {
                    cpt++;
                }
            }

            if (cpt == 2) {
                //ALL Transport
                AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("bus.csv"))));
                AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("train.csv"))));
                AllTrajet(new CSVReader(Files.newBufferedReader(Paths.get("car.csv"))));

                /*
                    pour les trajets composés
                 */
                //List<TrajetSimple> listDef = ChoiceTrajet(VVdepart);
                //BestTrajetCompose(listDef);

                // BUS
                initTrajets(new CSVReader(Files.newBufferedReader(Paths.get("bus.csv"))), root, VVdepart, VVarrive);

                // CAR
                initTrajets(new CSVReader(Files.newBufferedReader(Paths.get("car.csv"))), root, VVdepart, VVarrive);

                // TRAIN
                initTrajets(new CSVReader(Files.newBufferedReader(Paths.get("train.csv"))), root, VVdepart, VVarrive);

                TracerTrajet(root, VVdepart, VVarrive);
                tab();
            } else {
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setHeaderText("Insérer des villes correctes !");
                fail.showAndWait();
            }
        }
    }

    public void initVilles (AnchorPane root, String v1, String v2) throws IOException {

            Reader reader = Files.newBufferedReader(Paths.get("villes.csv"));
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {

                Ville vill = new Ville(nextRecord[0],Integer.parseInt(nextRecord[1]),Integer.parseInt(nextRecord[2]));
                AllVilleList.put(vill.getNom(),vill);

                if (nextRecord[0].equals(v1) || nextRecord[0].equals(v2)) {

                    Ville vil = new Ville(nextRecord[0],Integer.parseInt(nextRecord[1]),Integer.parseInt(nextRecord[2]));
                    villeList.put(vil.getNom(),vil);
                    Circle circle = new Circle(vil.getX()* scale + 5,vil.getY() * scale - 2,espace/2);
                    circle.setFill(Color.BLACK);
                    root.getChildren().add(circle);
                    Text villeName = new Text(nextRecord[0]);
                    villeName.setX(Integer.parseInt(nextRecord[1]) * scale + 12);
                    villeName.setY(Integer.parseInt(nextRecord[2]) * scale - 2);
                    villeName.setFill(Color.BLACK);
                    villeName.setStyle("-fx-font: 29 arial;");
                    root.getChildren().add(villeName);
                }
            }
        }


    public TrajetSimple BestTrajetCompose(List<TrajetSimple> listTrajetSimple){
        List<TrajetSimple> listTrajetSimpleSecond = new ArrayList<>();
        double result = 0;
        double minVal = Double.MAX_VALUE;
        TrajetSimple test = new TrajetSimple();
        ArrayList<Double> tabIndex = new ArrayList<>();

        List<TrajetSimple> listTrajetSimpleStock = new ArrayList<>();
        int Valueconfort = 0;
        int ValueCO2 = 0 ;
        int ValueTemps= 0;
        int ValueCout= 0 ;

        if(!(TCO2.getText().trim().isEmpty())){
            ValueCO2 = 1;
        }
        if(!(TTemps.getText().trim().isEmpty())){
            ValueTemps = 1;
        }
        if(!(Tconfort.getText().trim().isEmpty())){
            Valueconfort = 1;
        }
        if(!(TCout.getText().trim().isEmpty())){
            ValueCout = 1;
        }
        for (TrajetSimple simple : listTrajetSimple) {
            result = (Valueconfort * simple.confort + ValueCO2 * simple.co2 + ValueCout * simple.cout + ValueTemps * simple.duree);
            tabIndex.add(result);
        }
        for (int j = 0; j < tabIndex.size(); j++) {
            if (tabIndex.get(j) < minVal) {
                minVal = tabIndex.get(j);
                test = trajetSimpleList.get(j);
            }
        }

        listTrajetSimpleSecond = ChoiceTrajet(test.arrive.getNom());
        ArrayList<Double> tabIndexSecond = new ArrayList<>();
        for (int i = 0;i < listTrajetSimpleSecond.size();i++) {
            result = tabIndex.get(i) +(Valueconfort * listTrajetSimpleSecond.get(i).confort + ValueCO2 * listTrajetSimpleSecond.get(i).co2 + ValueCout * listTrajetSimpleSecond.get(i).cout + ValueTemps * listTrajetSimpleSecond.get(i).duree);
            tabIndexSecond.add(result);
        }
        for (int j = 0; j < tabIndexSecond.size(); j++) {
            if (tabIndexSecond.get(j) < minVal) {
                minVal = tabIndexSecond.get(j);
                test = listTrajetSimpleSecond.get(j);
            }
        }
        return test;
    }


    public TrajetSimple BestTrajet() {
        double minVal = Double.MAX_VALUE;
        TrajetSimple test = new TrajetSimple();
        ArrayList<Double> tabIndex = new ArrayList<>();
        double result = 0;
        int Valueconfort = 0;
        int ValueCO2 = 0 ;
        int ValueTemps= 0;
        int ValueCout= 0 ;

        if(!(TCO2.getText().trim().isEmpty())){
            ValueCO2 = 1;
        }
        if(!(TTemps.getText().trim().isEmpty())){
            ValueTemps = 1;
        }
        if(!(Tconfort.getText().trim().isEmpty())){
            Valueconfort = 1;
        }
        if(!(TCout.getText().trim().isEmpty())){
            ValueCout = 1;
        }
            for (TrajetSimple trajetSimple : trajetSimpleList) {
              result = Valueconfort*trajetSimple.confort + ValueCO2*trajetSimple.co2 + ValueCout*trajetSimple.cout + ValueTemps*trajetSimple.duree;
              tabIndex.add(result);
            }
             for (int j = 0; j < tabIndex.size(); j++) {
                 if (tabIndex.get(j) < minVal) {
                       minVal = tabIndex.get(j);
                       test = trajetSimpleList.get(j);
                 }
             }
             if (!(TCO2.getText().trim().isEmpty()) && !(TTemps.getText().trim().isEmpty()) && !(Tconfort.getText().trim().isEmpty()) && !(TCout.getText().trim().isEmpty())) {
            for (TrajetSimple trajetSimple : trajetSimpleList) {
                result = trajetSimple.confort + trajetSimple.co2 + trajetSimple.cout + trajetSimple.duree;
                tabIndex.add(result);
            }
            for (int j = 0; j < tabIndex.size(); j++) {
                if (tabIndex.get(j) < minVal) {
                    minVal = tabIndex.get(j);
                    test = trajetSimpleList.get(j);
                }
            }
        }
        return test;
    }



    public void AllTrajet(CSVReader csvReader) throws IOException {
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
                TrajetSimple currentTrajet = new TrajetSimple();
                currentTrajet.depart = returnVille(nextRecord[0]);
                currentTrajet.arrive = returnVille(nextRecord[1]);
                currentTrajet.dateDepart = LocalTime.now().plusMinutes(Integer.parseInt(nextRecord[3]));
                currentTrajet.duree = Integer.parseInt(nextRecord[4]);
                currentTrajet.cout = Integer.parseInt(nextRecord[5]);
                currentTrajet.dateArrivee = currentTrajet.dateDepart.plusMinutes(currentTrajet.duree);
                currentTrajet.distance = Ville.getDist(currentTrajet.depart, currentTrajet.arrive);
                currentTrajet.moyen = Moyen.valueOf(nextRecord[2].toUpperCase());
                currentTrajet.confort = Integer.parseInt(nextRecord[7]);
                currentTrajet.co2 = Integer.parseInt(nextRecord[6]);
                AllTrajetSimple.add(currentTrajet);
        }
    }

    public Ville returnVille(String s1){
        Ville GoodVille = new Ville();
        String key;
            for(Map.Entry<String,Ville> entry : AllVilleList.entrySet()){
                key = entry.getKey();
                if(s1.equals(key)){
                    GoodVille = entry.getValue();
                }
            }
        return GoodVille;
    }

    public List<TrajetSimple> ChoiceTrajet(String depart){
        List<TrajetSimple> ListChoiceTrajet = new ArrayList<>();
        for (TrajetSimple trajetSimple : AllTrajetSimple) {
            if (trajetSimple.depart.getNom().equals(depart)) {
                ListChoiceTrajet.add(trajetSimple);
            }
        }
        return ListChoiceTrajet;
    }


    public void initTrajets(CSVReader csvReader,AnchorPane root, String v1, String v2) throws IOException {
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {

            //System.out.println(villeList);

            if (!(villeList.get(nextRecord[0]) == null || villeList.get(nextRecord[1]) == null) && (villeList.get(nextRecord[0]).getNom().equals(v1) && villeList.get(nextRecord[1]).getNom().equals(v2))) {

                TrajetSimple currentTrajet = new TrajetSimple();
                currentTrajet.depart = villeList.get(nextRecord[0]);
                currentTrajet.arrive = villeList.get(nextRecord[1]);
                currentTrajet.dateDepart = LocalTime.now().plusMinutes(Integer.parseInt(nextRecord[3]));
                currentTrajet.duree = Integer.parseInt(nextRecord[4]);
                currentTrajet.cout = Integer.parseInt(nextRecord[5]);
                currentTrajet.dateArrivee = currentTrajet.dateDepart.plusMinutes(currentTrajet.duree);
                currentTrajet.distance = Ville.getDist(currentTrajet.depart, currentTrajet.arrive);
                currentTrajet.moyen = Moyen.valueOf(nextRecord[2].toUpperCase());
                currentTrajet.confort = Integer.parseInt(nextRecord[7]);
                currentTrajet.co2 = Integer.parseInt(nextRecord[6]);
                trajetSimpleList.add(currentTrajet);
            }
        }
    }


    public void TracerTrajet(AnchorPane root, String v1, String v2){

        TrajetSimple  ts = BestTrajet();
        Line line = new Line(ts.depart.getX() * scale, ts.depart.getY() * scale, ts.arrive.getX() * scale, ts.arrive.getY() * scale);
        switch (ts.moyen) {
            case BUS:
                line.setStroke(Color.GOLD);
                line.setStrokeWidth(4);
                root.getChildren().add(line);
                break;

            case CAR:
                line.setStroke(Color.BLUEVIOLET);
                line.setStrokeWidth(4);
                root.getChildren().add(line);
                break;

            case TRAIN:
                line.setStroke(Color.RED);
                line.setStrokeWidth(4);
                root.getChildren().add(line);
                break;

            default:
                new Alert(Alert.AlertType.ERROR);
                break;
        }

        Label label = new Label();
        label.setText(" CAR : ");
        label.setStyle("-fx-font: 29 arial;");
        label.setTranslateY(700);
        label.setTranslateX(30);
        root.getChildren().add(label);
        Line line4 = new Line(170, 720, 270, 720);
        line4.setStroke(Color.BLUEVIOLET);
        line4.setStrokeWidth(20);
        root.getChildren().add(line4);

        Label label1 = new Label();
        label1.setText(" TRAIN : ");
        label1.setStyle("-fx-font: 29 arial;");
        label1.setTranslateY(660);
        label1.setTranslateX(30);
        root.getChildren().add(label1);
        Line line1 = new Line(170, 680, 270, 680);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(20);
        root.getChildren().add(line1);

        Label label2 = new Label();
        label2.setText(" BUS : ");
        label2.setStyle("-fx-font: 29 arial;");
        label2.setTranslateY(740);
        label2.setTranslateX(30);
        root.getChildren().add(label2);
        Line line2 = new Line(170, 760, 270, 760);
        line2.setStroke(Color.GOLD);
        line2.setStrokeWidth(20);
        root.getChildren().add(line2);
    }

    @FXML
    public void Diagramme() throws IOException {
        cptD++;
        Stage primarysecondStagez = new Stage();
        Parent rootFXMLL = FXMLLoader.load(getClass().getResource("SamplePieChart.fxml"));
        primarysecondStagez.setScene(new Scene(rootFXMLL, 800, 500));
        primarysecondStagez.show();
    }
}