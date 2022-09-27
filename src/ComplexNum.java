import java.util.InputMismatchException;

public class ComplexNum {
    private final double real;
    private final double imaginary;

    public ComplexNum(String complexNum){
        String[] arrNum = complexNum.split("\\+");
        if(arrNum.length != 2){
            throw new InputMismatchException("The complex number isn't in the correct format");
        }
        real = Double.parseDouble(arrNum[0].strip());
        String imaginaryStr = arrNum[1].strip();
        imaginaryStr = imaginaryStr.substring(0, imaginaryStr.length() - 1);
        imaginary = Double.parseDouble(imaginaryStr);
    }

    public ComplexNum(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public static ComplexNum add(ComplexNum num1, ComplexNum num2){
        return new ComplexNum(num1.real + num2.real, num1.imaginary + num2.imaginary);
    }

    public static ComplexNum subtract(ComplexNum num1, ComplexNum num2){
        return new ComplexNum(num1.real - num2.real, num1.imaginary - num2.imaginary);
    }

    public static ComplexNum multiply(ComplexNum num1, ComplexNum num2){
        return new ComplexNum((num1.real * num2.real) - (num1.imaginary * num2.imaginary),
                (num1.real * num2.imaginary) + (num2.real * num1.imaginary));
    }

    public static ComplexNum divide(ComplexNum num1, ComplexNum num2){
        ComplexNum temp = multiply(num1, new ComplexNum(num2.real, -1*num2.imaginary));
        double divisionFactor = (num2.real * num2.real) + (num2.imaginary * num2.imaginary);
        return new ComplexNum(temp.real/divisionFactor, temp.imaginary/divisionFactor);
    }

    public static double distance(ComplexNum num1, ComplexNum num2){
        double realDiff = num1.real - num2.real;
        double imaginaryDiff = num1.imaginary - num2.imaginary;
        return Math.sqrt(realDiff*realDiff + imaginaryDiff*imaginaryDiff);
    }

    @Override
    public String toString(){
        return real + " + " + imaginary + "i";
    }
}
