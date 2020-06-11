package controller;

import controller.util.FXMLResources;
import javafx.application.Application;
import javafx.stage.Stage;
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
            FXMLResources fxmlResources = new FXMLResources();
            fxmlResources.loadAllResources();
            primaryStage.setScene(FXMLResources.getWelcomeScene());
            primaryStage.setTitle("Carrier Pigeon");
            primaryStage.setOnCloseRequest(e-> cleanUpResources()); //close event needs to be consumed if you want to not close the program after all...
            primaryStage.show();
        }catch (IOException exception){
            System.out.println("FATAL ERROR: Could not load visual resources");
            exception.printStackTrace();
        }
    }
    private void cleanUpResources (){
        System.out.println("Cleaning up resources...");
        /*todo save messages
         * - save contacts?
         * - clean up resources*/
    }
}