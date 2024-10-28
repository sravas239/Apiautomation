package com.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Apitesting {
	
	@Test
	void test_01() {
		
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getTime());
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		

	}
	
@Test
	void test_02() {
		
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(7));
	}

@Test
public void test_1_post() {

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("name", "sravani");
	map.put("job", "softwareenginee");

	System.out.println(map);

	JSONObject request = new JSONObject();

	request.put("name", "sravani");
	request.put("job", "softwareengineer");

	System.out.println(request);
	System.out.println(request.toJSONString());

	given().
	header("Content-Type","application/json").
	contentType(ContentType.JSON).
	accept(ContentType.JSON).
	body(request.toJSONString()).
	when().
	post("https://reqres.in/api/users").
	then().
	statusCode(201).
	log().all();

}

@Test
public void test_2_put() {
    JSONObject request = new JSONObject();
    request.put("name", "sravani");
    request.put("job", "engineer");

    System.out.println(request.toJSONString());

    given()
        .header("Content-Type", "application/json")
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
    .when()
        .put("https://reqres.in/api/users/2")  // Providing the correct user ID
    .then()
        .statusCode(200)  // Expecting successful update, not 404
        .log().all();
}

@Test
public void test_3_patch() {
    JSONObject request = new JSONObject();
    request.put("name", "sravani");
    request.put("job", "Guduru");

    System.out.println(request.toJSONString());

    given()
        .header("Content-Type", "application/json")
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
    .when()
        .patch("https://reqres.in/api/users/2")  // Providing the correct user ID
    .then()
        .statusCode(200)  // Expecting a successful partial update, not 404
        .log().all();
}

	
	@Test
	public void test_4_delete() {
		
		
		when().
		delete("https://reqres.in/api/users/2").
		then().
		statusCode(204).
		log().all();
	}


	 @Test
	    public void authenticateAndUseToken() {
	        // Step 1: Authenticate and get token
	        Response authResponse = RestAssured
	                .given()
	                .header("Content-Type", "application/json")
	                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")  // Example email/password for reqres.in
	                .post("https://reqres.in/api/login");  // Authentication endpoint

	        authResponse.prettyPrint();  // Print response for debugging

	        // Validate that authentication was successful and token is returned
	        int statusCode = authResponse.getStatusCode();
	        assertEquals(statusCode, 200, "Authentication failed!");

	        String token = authResponse.jsonPath().getString("token");  // Extract the token
	        System.out.println("Token received: " + token);

	        // Step 2: Use the token in a subsequent GET request
	        Response getResponse = given()
	                .header("Authorization", "Bearer " + token)  // Include the token in Authorization header
	                .get("https://reqres.in/api/users/2");  // Example endpoint using token for user data
	        
	        // Print and validate the GET response
	        getResponse.prettyPrint();
	        assertEquals(getResponse.getStatusCode(), 200, "Resource request failed!");
	    }

	private void assertEquals(int statusCode, int i, String string) {
		// TODO Auto-generated method stub
		
	}
	

}
