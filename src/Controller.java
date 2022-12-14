import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    private Stage stage;
    private FileChooser loadFileChooser;
    private FileChooser saveFileChooser;
    @FXML
    private ImageView imageView;
    private Image image;
    @FXML
    private AnchorPane imageContainer;
    private List<String> extensions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        extensions = Arrays.stream(ImageIO.getWriterFormatNames()).map(s -> "." + s).collect(Collectors.toList());
        loadFileChooser = new FileChooser();
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        loadFileChooser.getExtensionFilters().add(textFilter);

        saveFileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files",
                extensions.stream().map(s -> "*" + s).collect(Collectors.toList()));
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
        try {
            if (file != null) {
                image = ImageManager.computeImage(file);
                imageView.setImage(image);
            }
        } catch(IOException e){
            createAlert("IOException", "There was a problem reading the file");
        } catch(IllegalStateException e){
            createAlert("IllegalStateException", "There wasn't enough lines in the file");
        } catch(NumberFormatException e){
            createAlert("NumberFormatException", "A number was formatted incorrectly");
        } catch(InputMismatchException e){
            createAlert("InputMismatchException", e.getMessage());
        }
    }

    @FXML
    public void save() {
        if(image != null) {
            File file = saveFileChooser.showSaveDialog(stage);
            if (file != null && file.getName().indexOf('.') != -1) {
                String extension = file.getName().substring(file.getName().indexOf('.'));
                if (extensions.contains(extension)) {
                    try {
                        BufferedImage awtImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);
                        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, awtImage);
                        ImageIO.write(bufferedImage, extension.substring(1), file);
                    } catch (IOException e) {
                        createAlert("IOException", "There was a problem saving the image");
                    }
                } else {
                    createAlert("User Error", "The file type must an image file type\n" + extensions);
                }
            }
        }
    }

    /**
     * creates an error message
     *
     * @param header  the title of the error message
     * @param content a message explaining what caused the error
     */
    public static void createAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * creates a window that describes the program's functions
     */
    @FXML
    public void about() {
        Alert aboutBox = new Alert(Alert.AlertType.INFORMATION);
        aboutBox.setTitle("About");
        aboutBox.setHeaderText(null);

        TextArea area = new TextArea(
                """
                        Newton's Fractal
                                        
                        Link to an explanation of the fractal:
                        https://www.youtube.com/watch?v=-RdOwhmqP5s
                                                        
                        the user input is entered into the program through a text file.
                        the image can be saved as a .png or a .jpg
                                                                                                 
                        Explanation of text file format:
                        
                        
                        Line 1: pixel width x pixel height (no spaces)
                         
                        Example: 1280x720
                        
                        
                        Line 2: "The complex number in bottom left corner of the picture",
                        "The complex number in top right corner of the picture"
                        
                        * (Input the number in the form: real + imaginary)
                        * (if the imaginary part is negative input +-)
                        * (you can't input just i you must instead input 1i)
                                      
                        Example: -4 + -2.25i, 4 + 2.25i
                                      
                                      
                        Line 3: The number of steps
                                      
                        Example: 100
                                       
                                       
                        Line 4: The roots of the inputted polynomial.
                        * (separate each root by commas)
                        * (input each root in the form: real + imaginary)
                        * (if the imaginary part is negative input +-)
                        * (you can't input just i you must instead input 1i)
                                       
                        Example: -2 + 1i, -2 + -1i, 2 + 0i
                                       
                        This is equivalent to the polynomial:
                        y = (x - (-2 + 1i)) * (x - (-2 - 1i)) * (x - (2 + 0i))
                                       
                                       
                        Line 5: the colors associated with each root.
                        * (separate each color by commas)
                        * (must have the same number of colors as roots)
                        * (color must be in the form: RRGGBB
                                       
                        Example: FF0000, 00FF00, 0000FF
                                       
                        Full Example:
                        1280x720
                        -4 + -2.25i, 4 + 2.25i
                        100
                        -2 + 1i, -2 + -1i, 2 + 0i
                        FF0000, 00FF00, 0000FF
                        """
        );
        area.setWrapText(true);
        area.setEditable(false);

        aboutBox.getDialogPane().setContent(area);
        aboutBox.setResizable(true);

        aboutBox.showAndWait();
    }
}
