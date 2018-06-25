import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class googlemaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String lat="40.714224,-73.961452";
String name="Brooklyn";
String key="AIzaSyDD9tYKJn0-HxQHqBpvdkMdjTVEZilPPNo";
	System.out.println("a");
	RestAssured.baseURI="http://localhost:8765/maps/api/geocode/json";
	RequestSpecification req=RestAssured.given().param("latlng", "40.714224,-73.961452").param("key", "AIzaSyDD9tYKJn0-HxQHqBpvdkMdjTVEZilPPNo");
	JSONObject param=new JSONObject();
	//System.out.println("b");

/*	param.put("latlng", "40.714224,-73.961452");
	param.put("key", "AIzaSyDD9tYKJn0-HxQHqBpvdkMdjTVEZilPPNo");*/
	
	//System.out.println("c");
System.out.println(req);
	Response res=req.get();
	//System.out.println("d");

	
	//System.out.println(res.getBody().asString());
JsonPath jp=res.jsonPath();

System.out.println(jp.param("name", name).get("results[0].address_components.findAll {address_components->address_components.short_name==name}.long_name"));
	}

}
