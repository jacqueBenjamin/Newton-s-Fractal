import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage stage;
    private FileChooser fileChooser;
    @FXML
    private ImageView imageView;
    @FXML
    private MenuItem saveMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser = new FileChooser();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void load() {
        File file = fileChooser.showOpenDialog(stage);
        try {
            if (file != null) {
                ImageManager.computeImage(file);
                saveMenuItem.setDisable(false);
            }
        } catch(IOException e){

        }
    }

    @FXML
    public void save() {
    }
}
