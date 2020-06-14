package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Cezar;
import model.Polibius;
import model.Solitaire;
import model.util.CipherBuilder;

/**
 * @author Joanna Sokołowska
 */
public class CipherController {
    @FXML
    private ChoiceBox<String> cipherChoice;
    @FXML
    private Button cancelButton;
    @FXML
    private Button cipherButton;
    @FXML
    private Text errorMessage;
    @FXML
    private TextField key;

    private CipherBuilder cBuilder;


    @FXML
    private void initialize(){
        cipherChoice.getItems().addAll("Polibius", "Cezar", "Solitaire");
    }

    public void setCipherBuilder(CipherBuilder cBuilder){
        this.cBuilder = cBuilder;
    }
    @FXML
    private void close(){
        Stage curr = (Stage) cipherButton.getScene().getWindow();
        curr.close();
    }

    @FXML
    private void cipherAndSend(){
        String cipher = cipherChoice.getValue();
        String key = this.key.getText();
        if (cipher ==null){
            errorMessage.setText("Choose cipher or cancel");
            return;
        }
        switch (cipher){
            case "Polibius":
                if (key.trim().length()>0){
                    cBuilder.setCipher(new Polibius(key));
                }else {
                    errorMessage.setText("Wrong key");
                    return;
                }
            case "Solitaire":
                cBuilder.setCipher(new Solitaire());
                break;
            case "Cezar":
                try{
                    int res = Integer.parseInt(key);
                    cBuilder.setCipher(new Cezar(res));
                }catch (NumberFormatException ex){
                    errorMessage.setText("Wrong key");
                    return;
                }
                break;
        }
        close();
    }
}
