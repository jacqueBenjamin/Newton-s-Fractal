import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;

public class ImageManager {
    private static List<ComplexNum> roots;
    private static List<Color> colors;

    public static Image computeImage(File file) {
        readFile(file);
        WritableImage image = new WritableImage(1200, 750);
        PixelWriter pw = image.getPixelWriter();
        for(int x = 0; x < 1200; x++){
            for(int y = 0; y < 750; y++){
                pw.setColor(x, y, Color.BLUE);
            }
        }
        return image;
    }

    private static void readFile(File file){

    }
}
