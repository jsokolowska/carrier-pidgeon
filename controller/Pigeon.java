package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * @author Joanna Sokołowska
 */

public class Pigeon extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/logIn.fxml"));
        //primaryStage.setOnCloseRequest(e-> cleanUpResources()); //close event needs to be consumed if you want to not close the program after all...
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void cleanUpResources (){
        //clean up resources
    }
}