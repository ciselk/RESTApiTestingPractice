import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DummyRestApiTest extends BaseTest{

    @Test
    public void getAllEmployeeData(){
        Response response = RestAssured.given().when().header("Content-Type", "application/json")
            .get("/employees")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().toString());

    }

    @DataProvider(name = "postCreateEmployeeDataProvider")
    public Object[] postCreateEmployeeDataProvider() throws JSONException {
        String name = "test";
        String salary = "123";
        String age = "23";
        return new Object[][]{{name, salary, age}};
    }
    @Test(dataProvider = "postCreateEmployeeDataProvider")
    public void postCreateEmployee(String name,String salary, String age) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("salary",salary);
        jsonObject.put("age",age);

        Response response = RestAssured.given().body(jsonObject.toString())
                .when()
                .header("Content-Type", "application/json")
                .post("/create")
                .then()
                .extract().response();

        System.out.println(response.getBody().toString());
        //Assert.assertEquals(response.getStatusCode(),200);
        JSONObject myObject = new JSONObject(response);
        System.out.println(myObject.toString());
        Assert.assertEquals(myObject.get("status"),"success");

    }
}
