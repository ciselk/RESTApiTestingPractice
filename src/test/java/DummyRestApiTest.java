import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.ProtocolException;

public class DummyRestApiTest extends BaseTest{

    @Test
    public void getUsers() throws JSONException {
        Response response = RestAssured.given().
                headers("Content-Type", "application/json").
                when().
                get("/api/users?page=2").
                then().
                extract().
                response();

        Assert.assertEquals(response.getStatusCode(),200);

        String jsonString = response.getBody().asString();
        JSONObject object = new JSONObject(jsonString);
        System.out.println(object);


    }

    @DataProvider(name = "postCreateUserDataProvider")
    public Object[] postCreateUserDataProvider() throws JSONException {
        String name = "çisel";
        String job = "engineer";
        String name2 = "çisel2";
        String job2 = "engineer2";

        return new Object[][]{{name,job},{name2,job2}};
    }
    @Test(dataProvider = "postCreateUserDataProvider")
    public void postCreateUser(String name,String job) throws JSONException, ProtocolException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("job",job);

        System.out.println(jsonObject);
        Response response = RestAssured.given().
                headers("Content-Type", "application/json").
                body(jsonObject.toString()).
                when().
                post("/api/users/").
                then().
                extract().
                response();

        String jsonString = response.getBody().asString();
        JSONObject jsonObj = new JSONObject(jsonString);
        String nameResult = jsonObj.getString("name");
        String jobResult = jsonObj.getString("job");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(nameResult,name);
        Assert.assertEquals(jobResult,job);
    }
    @DataProvider(name = "putUpdateUserDataProvider")
    public Object[] putUpdateUserDataProvider() throws JSONException {
        String name = "morpheus";
        String job = "engineer-aselsan";

        return new Object[][]{{name,job}};
    }
    @Test(dataProvider = "putUpdateUserDataProvider")
    public void putUpdateUser(String name,String job) throws JSONException, ProtocolException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("job",job);

        System.out.println(jsonObject);
        Response response = RestAssured.given().
                headers("Content-Type", "application/json").
                body(jsonObject.toString()).
                when().
                put("/api/users/2").
                then().
                extract().
                response();

        String jsonString = response.getBody().asString();
        JSONObject jsonObj = new JSONObject(jsonString);
        String nameResult = jsonObj.getString("name");
        String jobResult = jsonObj.getString("job");
        System.out.println(jobResult);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(nameResult,name);
        Assert.assertEquals(jobResult,job);
    }
    @DataProvider(name = "patchUpdateUserDataProvider")
    public Object[] patchUpdateUserDataProvider() throws JSONException {
        String name = "morpheus";
        String job = "engineer-aselsan4";

        return new Object[][]{{name,job}};
    }
    @Test(dataProvider = "patchUpdateUserDataProvider")
    public void patchUpdateUser(String name,String job) throws JSONException, ProtocolException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("job",job);

        System.out.println(jsonObject);
        Response response = RestAssured.given().
                headers("Content-Type", "application/json").
                body(jsonObject.toString()).
                when().
                patch("/api/users/2").
                then().
                extract().
                response();

        String jsonString = response.getBody().asString();
        JSONObject jsonObj = new JSONObject(jsonString);
        String nameResult = jsonObj.getString("name");
        String jobResult = jsonObj.getString("job");
        System.out.println(jobResult);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(nameResult,name);
        Assert.assertEquals(jobResult,job);
    }
    @Test()
    public void deleteUser() throws JSONException, ProtocolException {

        Response response = RestAssured.given().
                headers("Content-Type", "application/json").
                when().
                delete("/api/users/2").
                then().
                extract().
                response();

        Assert.assertEquals(response.getStatusCode(),204);

    }
}
