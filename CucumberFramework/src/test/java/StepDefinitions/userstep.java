package StepDefinitions;

import org.json.simple.JSONObject;
import org.testng.Assert;
import io.cucumber.java.en.And; 
import io.cucumber.java.en.Given; 
import io.cucumber.java.en.Then; 
import io.cucumber.java.en.When; 
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class userstep 
{
	private static ResponseOptions<Response> resp = null;
	private JSONObject requestParams;
	RequestSpecification httpRequest;

	@Given("^I have an end point$")
    public void i_have_an_end_point() throws Throwable 
	{
		RestAssured.baseURI="https://reqres.in/api/users/";
		httpRequest=RestAssured.given(); 
	}

	@When("^I add  user name as (.+) and job as (.+) in user details$")
	public void i_add_user_name_as_and_job_as_in_user_details(String username, String job) throws Throwable 
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstname",username); requestParams.put("job",job);

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());

		resp=httpRequest.request(Method.POST);
	}
	
	@When("^I send the user id$")
    public void i_send_the_user_id() throws Throwable 
    {		
    	resp=httpRequest.request(Method.GET,"2");
    }
	
	@When("^I change user name as (.+) and job as (.+) in user details$")
	public void i_change_user_name_as_and_job_as_in_user_details(String username, String job) throws Throwable
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstname",username); 
		requestParams.put("job",job);

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());

		resp=httpRequest.request(Method.PUT);  
	}

	@When("^I send the user id to delete$")
    public void i_send_the_user_id_to_delete() throws Throwable
	{
		resp=httpRequest.request(Method.DELETE,"2");
    }

	@And("^Checking the status code is (.+)")
	public void checking_the_status_code_is(int statuscode) throws Throwable
	{ 
		int statusCode1=resp.getStatusCode(); 
		System.out.println("Status Code is :" +statusCode1); 
		Assert.assertEquals(statusCode1,statuscode);
	}

	@Then("^Validating the user name as (.+) in the name field of the body$")
	public void validating_the_user_name_as_in_the_name_field_of_the_body(String username) throws Throwable
	{ 
		String responseBody=resp.getBody().asString();
		System.out.println("Reponse Body is :" +responseBody);
		Assert.assertEquals(responseBody.contains(username),true); 
	}
    	
}

