package TEST;

import org.testng.annotations.Test;

import Resources.BaseClass;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class GetMethod extends BaseClass {
    @Test
    public void getMethod() {
	RequestSpecification request = RestAssured.given();
	String response = request.get(baseURI).headers().toString();
	System.out.println(response);
    }
}
