package TEST;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestChaining {

    @Test
    public void VerifyId() {
	int ele;
	String jsonData = "{\"id\" : 1,\"Name\" : \"Amit Kumar\", \"Email\" : \"amit@gmail.com\" }";
	String baseURI = "http://localhost:3000/posts/";
	Gson gson = new Gson();
	gson.fromJson(jsonData, RequestChaining.class);
	RequestSpecification request = RestAssured.given();
	Response resp = request.contentType(ContentType.JSON).get(baseURI);
	JsonPath js = resp.jsonPath();
	List<Integer> json = js.getList("id");
	System.out.println(json);
//	Iterator<Integer> itr = json.listIterator();
//	while (itr.hasNext()) {
//	    int status = request.delete(baseURI + itr.next()).getStatusCode();
//	    System.out.println(status);
//	}
	ele = json.get(1);
	if (json.contains(ele)) {
	    int statusCode = request.contentType(ContentType.JSON).body(jsonData).when().log().all()
		    .delete(baseURI + ele).getStatusCode();
	    Assert.assertEquals(statusCode, 200);
	    System.out.println(statusCode);
	} else {
	    System.out.println("No Json object Found");
	}
    }
}
