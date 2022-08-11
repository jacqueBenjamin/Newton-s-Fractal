import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplexNumTests {
    @Test
    public void add(){
        ComplexNum c1 = new ComplexNum(1, 2);
        ComplexNum c2 = new ComplexNum(3, -4);
        Assertions.assertEquals("4.0 + -2.0i", ComplexNum.add(c1, c2).toString());
    }

    @Test
    public void subtract(){
        ComplexNum c1 = new ComplexNum(1, 2);
        ComplexNum c2 = new ComplexNum(3, -4);
        Assertions.assertEquals("-2.0 + 6.0i", ComplexNum.subtract(c1, c2).toString());
    }

    @Test
    public void multiply(){
        ComplexNum c1 = new ComplexNum(1, 2);
        ComplexNum c2 = new ComplexNum(3, -4);
        Assertions.assertEquals("11.0 + 2.0i", ComplexNum.multiply(c1, c2).toString());
        Assertions.assertEquals("3.0 + 4.0i", ComplexNum.multiply(
                new ComplexNum(3, 4),
                new ComplexNum(1, 0)).toString());
    }

    @Test
    public void divide(){
        ComplexNum c1 = new ComplexNum(1, 2);
        ComplexNum c2 = new ComplexNum(3, -4);
        Assertions.assertEquals("-0.2 + 0.4i", ComplexNum.divide(c1, c2).toString());
    }
}
