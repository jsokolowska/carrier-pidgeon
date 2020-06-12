package controller.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Message;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Joanna Sokołowska
 */

public class FXMLResourcesManager {
    private static Scene welcomeScene;
    private static Scene mainScene;
    private static Scene newConnectionScene;
    private static SceneType currentScene;


    public void loadAllResources () throws IOException{
        Parent welcome = FXMLLoader.load(getClass().getResource("/resources/welcome.fxml"));
        Parent main =  FXMLLoader.load(getClass().getResource("/resources/mainView.fxml"));
        Parent newConnection = FXMLLoader.load(getClass().getResource("/resources/newConnection.fxml"));

        welcomeScene = new Scene(welcome);
        mainScene = new Scene(main);
        newConnectionScene = new Scene(newConnection);
    }

    public static Scene getWelcomeScene() {
        currentScene = SceneType.WELCOME;
        return welcomeScene;
    }

    public static Scene getMainScene() {
        currentScene = SceneType.MAIN;
        return mainScene;
    }

    public static Scene getNewConnectionScene() {
        return newConnectionScene;
    }

    public static SceneType getCurrentScene() {
        return currentScene;
    }
}
