import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		System.out.println(RestAssured.baseURI);

	RequestSpecification httprequest=RestAssured.given();
	Response response=httprequest.get("/Hyderabad");
	System.out.println(response.getBody());
	System.out.println(response.getHeaders());
	System.out.println(response.getStatusCode());
	System.out.println(response.getStatusLine());
	System.out.println(response.header("Content-Length"));
	System.out.println(response.header("Content-Type"));
	System.out.println(response.header("Date"));
	System.out.println(response.header("Da"));
	Assert.assertEquals(response.getStatusCode(), 200);
	
	JsonPath jp=response.jsonPath();
	System.out.println(jp.get("City"));
	System.out.println(jp.get("city"));



	}

}
