package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    Ici la méthode vas lancer le fxml
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage primarysecondStage = new Stage();
        Parent rootFXML = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primarysecondStage.setScene(new Scene(rootFXML, 848, 566));
        primarysecondStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
