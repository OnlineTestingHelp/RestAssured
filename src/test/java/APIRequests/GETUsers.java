package APIRequests;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETUsers {

	//@Test
	public void getUsers() {
		//Set the base URI
		RestAssured.baseURI = "https://reqres.in";
		
		//Create request
		RequestSpecification httpRequest = RestAssured.given();
		
		//hit the request and save the response
		Response response = httpRequest.request(Method.GET,"/api/users?page=2");
		
		//Validate the status
		System.out.println("Status is "+ response.getStatusCode());
		System.out.println("Time is "+ response.getTime());
		
		//Validate Header
		System.out.println("Header is "+ response.getHeader("Content-Type"));
		
		//Validate response body
		JsonPath resjson = response.jsonPath();
		
		System.out.println("Page value is "+ resjson.get("page"));
		Assert.assertEquals(resjson.get("page"), 2);
		
		System.out.println("Per Page value is "+ resjson.get("per_page"));
		System.out.println("Total value is "+ resjson.get("total"));
		System.out.println("Support URL value is "+ resjson.get("support.url"));
		System.out.println("Support Text value is "+ resjson.get("support.text"));
		
		//Getting the list of item and get the value
		System.out.println("First emaiil is "+ resjson.get("data[0].email"));
	}
	
	//@Test
	public void readListResponse() {
		//Set the base URI
				RestAssured.baseURI = "https://reqres.in";
				
				//Create request
				RequestSpecification httpRequest = RestAssured.given();
				
				//hit the request and save the response
				Response response = httpRequest.request(Method.GET,"/api/users?page=2");
				
				//Validate the status
				System.out.println("Status is "+ response.getStatusCode());
				System.out.println("Time is "+ response.getTime());
				
				//Validate Header
				System.out.println("Header is "+ response.getHeader("Content-Type"));
				
				//Validate response body
				JsonPath resjson = response.jsonPath();
				
				//Get the list of items
				List<Map<String,String>> data = resjson.getList("data");
				for(int i=0;i<data.size();i++) {
					Map<String,String> data1 = data.get(i);
					System.out.println("--------------Record----------------");
					System.out.println("Email is "+ data1.get("email"));
					System.out.println("first_name is "+ data1.get("first_name"));
					System.out.println("last_name is "+ data1.get("last_name"));
					System.out.println("avatar is "+ data1.get("avatar"));
					
					System.out.println("Entire map is "+ data1);
				}
	}
	
	@Test
	public void POSTCreateuser() {
		//Set the base URI
		RestAssured.baseURI = "https://reqres.in";
		
		//Create request
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request Body
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("name", "QATeam");
		jsonobj.put("job", "QA Manager");
		
		httpRequest.body(jsonobj);
				
		//hit the request and save the response
		Response response = httpRequest.request(Method.POST,"api/users");
		
		//Validate the status
		System.out.println("Status is "+ response.getStatusCode());
		System.out.println("Time is "+ response.getTime());
		System.out.println("----------------Response----------------");
		System.out.println("Response body is "+ response.getBody().asString());
	}
}
