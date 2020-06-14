package controller;

import controller.util.FXMLResourcesManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.util.SharedResources;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Joanna Sokołowska
 */

public class Pigeon extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        try{
            FXMLResourcesManager FXMLResourcesManager = new FXMLResourcesManager();
            FXMLResourcesManager.loadAllResources();
            primaryStage.setScene(controller.util.FXMLResourcesManager.getWelcomeScene());
            primaryStage.setTitle("Carrier Pigeon");
            primaryStage.setOnCloseRequest(e-> cleanUpResources());
            //close event needs to be consumed if you want to not close the program after all...
            primaryStage.show();
        }catch (IOException exception){
            System.out.println("FATAL ERROR: Could not load visual resources");
            exception.printStackTrace();
        }
    }
    private void cleanUpResources (){
        System.out.println("Cleaning up resources...");
        System.exit(0);
    }
}