import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
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
        fileChooser.showOpenDialog(stage);
        saveMenuItem.setDisable(false);
    }

    @FXML
    public void save() {
    }
}
