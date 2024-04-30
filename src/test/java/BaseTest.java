import io.restassured.RestAssured;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeClass
    public void setBaseUri(){
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
    }
}
