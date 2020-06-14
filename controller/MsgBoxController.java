package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author Joanna Sokołowska
 */
public class MsgBoxController {
    @FXML
    private VBox innerMsgBox;

    public void addMsg(Node node){
        innerMsgBox.getChildren().add(node);
    }
}
