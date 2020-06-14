package controller;

import controller.util.FXMLResourcesManager;
import controller.util.ThreadSafeResources;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Peer;
import model.util.PeerInfo;
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

        PeerInfo peerInfo = new PeerInfo();
        try{

            showWelcomeScreen(peerInfo);
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/resources/mainView.fxml"));
            Parent mainRoot = mainLoader.load();
            MainViewController mainViewController = mainLoader.getController();
            mainViewController.setInfo(peerInfo);
            Scene mainScene = new Scene(mainRoot);

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Carrier Pigeon");
            primaryStage.setOnCloseRequest(e-> cleanUpResources());
            primaryStage.show();
        }catch (IOException exception){
            System.out.println("FATAL ERROR: Could not load visual resources");
            exception.printStackTrace();
            System.exit(0);
        }
    }
    private void cleanUpResources (){
        System.out.println("Cleaning up resources...");
        System.exit(0);
    }
    private void showWelcomeScreen(PeerInfo peerInfo) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("/resources/welcome.fxml"));
        Parent welcome = welcomeLoader.load();
        WelcomeController controller = welcomeLoader.getController();
        controller.setPeerInfo(peerInfo);

        Scene welcomeScene = new Scene(welcome);
        Stage welcomeStage = new Stage();

        welcomeStage.setTitle("Log in");
        welcomeStage.setScene(welcomeScene);
        welcomeStage.showAndWait();

        System.out.println(peerInfo);
    }
}