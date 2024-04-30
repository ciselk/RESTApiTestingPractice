import org.testng.annotations.Test;

public class TestClass1 extends AnnotationTest{
    @Test
    public void firstTestClass()
    {
        System.out.println("firstTestClass");
    }
    @Test
    public void firstTestClassSecondTest()
    {
        System.out.println("firstTestClassSecondTest");
    }
}
