package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class TC_RequestResposne
{
	 @Test
	 public void GetAllUsers()
	 { 
		given().
		 when().
		 	get("https://reqres.in/api/users").
		 then().
		 	statusCode(200);
	 }
 
	 @Test
	public void PostSingleUser()
	{
		HashMap data=new HashMap();
		data.put("name","john");
		data.put("job","analyst");
	
		Response res=
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201)
		.log().body()
		.extract().response();
	
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("john"),true);
	}

	 @Test
	 public void GetUserByID()
	 {
		 given()
		 .when()
		 	.get("https://reqres.in/api/users/2")
		 .then()
		 	.statusCode(200)
		 	.body("data.first_name",equalTo("Janet"));
	 }
	 @Test
	 public void PutUserByID()
	 {
		 HashMap data=new HashMap();
			data.put("name","john");
			data.put("job","Lead");
	
		
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.put("https://reqres.in/api/users/2")
			.then()
				.statusCode(200)
				.log().body()
				.body("name",equalTo("john"));	
	 }
	 @Test
	 public void DeleteUserByID()
	 {
		 given()
		 .when()
		 	.delete("https://reqres.in/api/users/2")
		 .then()
		 	.statusCode(204);
	 }
}