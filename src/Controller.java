import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage stage;
    private FileChooser loadFileChooser;
    private FileChooser saveFileChooser;
    @FXML
    private ImageView imageView;
    private Image image;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private AnchorPane imageContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFileChooser = new FileChooser();
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        loadFileChooser.getExtensionFilters().add(textFilter);

        saveFileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files (*.png), (*.jpg)", "*.png", "*.jpg");
        saveFileChooser.getExtensionFilters().add(imageFilter);

        imageView.fitWidthProperty().bind(imageContainer.widthProperty());
        imageView.fitHeightProperty().bind(imageContainer.heightProperty());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void load() {
        File file = loadFileChooser.showOpenDialog(stage);
//        try {
            if (file != null) {
                image = ImageManager.computeImage(file);
                imageView.setImage(image);
                saveMenuItem.setDisable(false);
            }
//        } catch(IOException e){
//
//        }
    }

    @FXML
    public void save() {
        File file = saveFileChooser.showSaveDialog(stage);
        if (file != null && file.getName().indexOf('.') != -1) {
            String extension = file.getName().substring(file.getName().indexOf('.'));
            if(extension.equals(".jpg") || extension.equals(".png")) {
                try {
                    BufferedImage awtImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, awtImage);
                    ImageIO.write(bufferedImage, extension.substring(1), file);
                } catch (IOException e) {
                    createAlert("IOException", "There was a problem saving the image");
                }
            } else {
                createAlert("User Error", "The file type must be either .jpg or .png");
            }
        }
    }

    /**
     * creates an error message
     * @param header the title of the error message
     * @param content a message explaining what caused the error
     */
    public static void createAlert(String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
