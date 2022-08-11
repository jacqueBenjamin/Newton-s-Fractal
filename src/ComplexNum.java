public class ComplexNum {
    private double real;
    private double imaginary;

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

    @Override
    public String toString(){
        return real + " + " + imaginary;
    }
}
