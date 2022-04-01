package TEST;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {

    @Test
    public void getMethod() {
	Response resp = RestAssured.get("http://localhost:3000/posts/4");
	int statusCode = resp.getStatusCode();
	String statusBody = resp.getBody().asString();
	String statusLine = resp.getStatusLine();
	Headers header = resp.getHeaders();
	JsonPath js = new JsonPath(statusBody);
	String data = js.getString("first_name");
	System.out.println(data);
	Assert.assertEquals(statusCode, 200);
	System.out.println(statusCode);
	System.out.println(statusBody);
	System.out.println(statusLine);
	System.out.println(header);
    }

    @Test
    public void postMethod() {
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
	JSONObject requestParam = new JSONObject();
	requestParam.put("id", "8");
	requestParam.put("email", "amit@gmail.co");
	requestParam.put("first_name", "Amit");

	request.body(requestParam.toJSONString());
	Response response = request.post("http://localhost:3000/posts");
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 201);
	System.out.println(statusCode);
    }

    @Test
    public void putMethod() {
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
	JSONObject requestParam = new JSONObject();
	requestParam.put("id", "7");
	requestParam.put("email", "amit@gmail.com");
	requestParam.put("first_name", "Kumar");

	request.body(requestParam.toJSONString());
	Response response = request.put("http://localhost:3000/posts/7");
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 200);
	System.out.println(statusCode);
	
    }

    @Test
    public void deleteMethod() {
	RequestSpecification request = RestAssured.given();
	Response response = request.delete("http://localhost:3000/posts/1");
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 200);
	System.out.println(statusCode);
    }

}
