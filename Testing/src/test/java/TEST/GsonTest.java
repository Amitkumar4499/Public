package TEST;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

import Resources.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GsonTest extends BaseClass {
    @Test
    void GsonTests() {
	String baseURI = "http://localhost:3000/posts/";
	GsonTest gst = new GsonTest();
	Gson gson = new Gson();

	// Setting and Parsing Data For Request
	DataClass data = new DataClass();
	data = gst.getData(data);
	System.out.println(gson.toJson(data));

	// Post Request
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
	Response response = request.contentType(ContentType.JSON).body(data).when().log().all().post(baseURI);
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 201);
	System.out.println(statusCode);
    }

    public DataClass getData(DataClass details) {
	Faker data = new Faker((new Locale("en-IND")));
	details.setId(data.random().nextInt(0, 100));
	details.setName(data.name().fullName());
	details.setEmail(data.internet().emailAddress());
	Map<String, String> Address = new HashMap<String, String>();
	Address.put("HNo.", data.address().buildingNumber());
	Address.put("City", data.address().cityName());
	Address.put("State", data.address().state());
	details.setAddress(Address);
	return details;
    }
}

// Creating And Setting Data
class DataClass {
    int id;
    String Name;
    String Email;
    Map<String, String> Address;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return Name;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public String getEmail() {
	return Email;
    }

    public void setEmail(String email) {
	this.Email = email;
    }

    public Map<String, String> getAddress() {
	return Address;
    }

    public void setAddress(Map<String, String> address) {
	this.Address = address;
    }
}
