import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	RestAssured.baseURI="http://restapi.demoqa.com/customer";
	RequestSpecification req=RestAssured.given();
	
	JSONObject param=new JSONObject();
	param.put("FirstName","Vijender");
	param.put("LastName","Singh");
	param.put("UserName","vs123");
	param.put("Password","Password");
	param.put("Email","Vijender@gmail.com");

	req.header("Content-Type","application/json");
	req.body(param.toJSONString());
	
	Response res=req.post("\register");
	
	System.out.println(res.statusCode());
	}

}
