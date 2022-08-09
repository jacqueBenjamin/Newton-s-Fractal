import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader();
        Parent root = mainLoader.load(Objects.requireNonNull(getClass()
                .getResource("NewtonFractal.fxml")).openStream());
        stage.setScene(new Scene(root));
        stage.show();
        Controller controller = mainLoader.getController();
        controller.setStage(stage);
    }
}
