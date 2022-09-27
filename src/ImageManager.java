import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ImageManager {
    private static int width;
    private static int height;
    private static ComplexNum BottomLeft;
    private static ComplexNum TopRight;
    private static int steps;
    private static List<ComplexNum> roots;
    private static List<Color> colors;

    public static Image computeImage(File file) throws IOException{
        readFile(file);
        WritableImage image = new WritableImage(width, height);
        PixelWriter pw = image.getPixelWriter();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                pw.setColor(x, y, computePixel(x, y));
            }
            System.out.println((double)x/width);
        }
        return image;
    }

    private static Color computePixel(int x, int y){
        ComplexNum location = pixelToComplexNum(x, y);
        ComplexNum finalLocation = computeFinalLocation(location);
        return colors.get(findClosestRoot(finalLocation));
    }

    private static int findClosestRoot(ComplexNum location){
        int minRootIndex = 0;
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i < roots.size(); i++){
            double distance = ComplexNum.distance(location, roots.get(i));
            if(distance < minDistance){
                minDistance = distance;
                minRootIndex = i;
            }
        }
        return minRootIndex;
    }

    private static ComplexNum computeFinalLocation(ComplexNum initialLocation){
        ComplexNum location = initialLocation;
        for(int i = 0; i < steps; i++){
            try {
                location = ComplexNum.subtract(location, ComplexNum.divide(
                        computePolynomialAt(location), derivativeAt(location, 0)));
            } catch(ArithmeticException ignored){
            }
        }
        return location;
    }

    private static ComplexNum pixelToComplexNum(int x, int y){
        double realVal = BottomLeft.getReal() +
                ((double)x/width) * (TopRight.getReal() - BottomLeft.getReal());
        double imaginaryVal = TopRight.getImaginary() -
                ((double)y/height) * (TopRight.getImaginary() - BottomLeft.getImaginary());
        return new ComplexNum(realVal, imaginaryVal);
    }

    private static ComplexNum computePolynomialAt(ComplexNum location){
        ComplexNum counter = new ComplexNum(1, 0);
        for(int i = 0; i < roots.size(); i++){
            counter = ComplexNum.multiply(counter, ComplexNum.subtract(location, roots.get(i)));
        }
        return counter;
    }

    private static ComplexNum derivativeAt(ComplexNum location, int rootsUsed){
        if(rootsUsed == roots.size() - 1){
            return new ComplexNum(1, 0);
        } else {
            ComplexNum counter = new ComplexNum(1, 0);
            for(int i = rootsUsed + 1; i < roots.size(); i++){
                counter = ComplexNum.multiply(counter, ComplexNum.subtract(location, roots.get(i)));
            }
            return ComplexNum.add(counter,
                    ComplexNum.multiply(
                            ComplexNum.subtract(location, roots.get(rootsUsed)),
                            derivativeAt(location, rootsUsed + 1)));
        }
    }

    private static void readFile(File file) throws IOException {

//        roots.add(new ComplexNum(-1, 0));
//        roots.add(new ComplexNum(1, 0));
//        roots.add(new ComplexNum(0, 1));
//        roots.add(new ComplexNum(0, -1));
//        colors.add(Color.valueOf("D00000"));
//        colors.add(Color.valueOf("00D000"));
//        colors.add(Color.valueOf("0000D0"));
//        colors.add(Color.valueOf("D0D000"));
        Scanner scanner = new Scanner(file);
        String[] widthAndHeight = scanner.nextLine().split("x");
        if(widthAndHeight.length != 2){
            throw new InputMismatchException("The width and height isn't in the correct format");
        }
        width = Integer.parseInt(widthAndHeight[0].strip());
        height = Integer.parseInt(widthAndHeight[1].strip());
        String[] arrBLTR = scanner.nextLine().split(",");
        if(arrBLTR.length != 2){
            throw new InputMismatchException("The bottom-left and top-right complex numbers aren't in the correct format");
        }
        BottomLeft = new ComplexNum(arrBLTR[0]);
        TopRight = new ComplexNum(arrBLTR[1]);
        steps = Integer.parseInt(scanner.nextLine());
        String[] strRoots = scanner.nextLine().split(",");
        roots = new ArrayList<>();
        for(String strRoot : strRoots){
            roots.add(new ComplexNum(strRoot));
        }
        String[] strColors = scanner.nextLine().split(",");
        if(strColors.length != strRoots.length){
            throw new InputMismatchException("The number of colors is invalid");
        }
        colors = new ArrayList<>();
        for(String color : strColors){
            colors.add(Color.valueOf(color.strip()));
        }
    }
}
