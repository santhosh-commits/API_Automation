
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiMethodsTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiMethodsTests.class);
	int statusCode;

	@Test
	void getUserDetailsTest() {
		RestAssured.baseURI = "https://reqres.in/api/users/";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "2");

		String responseBody = response.getBody().asString();

		statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void createUserTest() {
		RestAssured.baseURI = "https://reqres.in/api/users/";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Aadya");
		requestParams.put("job", "student");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());

		Response response = httpRequest.request(Method.POST);

		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	void updateUserDetailsTest() {

		RestAssured.baseURI = "https://reqres.in/api/users/";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject updateData = new JSONObject();
		updateData.put("name", "Aarna");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(updateData.toJSONString());
		Response response = httpRequest.request(Method.PUT, "4");
		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		JsonPath newData = response.jsonPath();
		String name = newData.get("name");
		Assert.assertEquals(name, "Aarna");

	}

	@Test
	void deleteUserTest() {
		RestAssured.baseURI = "https://reqres.in/api/users/";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.DELETE, "4");
		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
	}

}
