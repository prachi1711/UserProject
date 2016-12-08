package com.project.user;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class HomeControllerTest {
		
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults
	
	@BeforeClass
    public static void setup() {
        				
        RestAssured.port = Integer.valueOf(8089);        

        String basePath = "/user/";        
        RestAssured.basePath = basePath;
        
        String baseHost = "http://localhost";        
        RestAssured.baseURI = baseHost;

    }
	
	@Test
    public void saveTextDataTest() {		               
        stubFor(post(urlEqualTo("/user/text"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"result\":{\"userPost\":\"hello\"},\"status\":\"SUCCESS\"}")));

        given()
        .contentType("application/json")
        .body("{\"userPost\" : \"hello\"}")
        .when().post("/text").then()
        .statusCode(200)        
        .body(containsString("userPost"))  
        .body(containsString("hello"));   
    }	
	
	@Test
    public void invalidEndpointTest() {		        
        stubFor(post(urlEqualTo("/user/text1"))
                .willReturn(aResponse()
                		.withStatus(404)));

        given()
        .contentType("application/json")        
        .when().post("/text1").then()          // incorrect end-point
        .statusCode(404);                 
    }	
	
	@Test
    public void incorrectDataEndpointTest() {			                 
        stubFor(post(urlEqualTo("/user/text"))
        		.withRequestBody(matching(".*\"userText\" : \"hello\"}"))
                .willReturn(aResponse()
                		.withStatus(400)));

        given()
        .contentType("application/json")           
        .body("{\"userText\" : \"hello\"}")           // incorrect parameter name, should be userPost
        .when().post("/text").then()
        .statusCode(400);                 
    }	
	
	
	@Test
	public void invaliUserPostdEndpointTest() {		        
        stubFor(post(urlEqualTo("/user/userPosts"))
                .willReturn(aResponse()
                		.withStatus(404)));

        given()
        .contentType("application/json")        
        .when().post("/userPosts").then()          // incorrect end-point
        .statusCode(404);                 
    }	
	
	
	@Test
    public void saveUserTextDataTest() {		               
        stubFor(post(urlEqualTo("/user/userPost"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"result\":{\"userName\":\"test\" ,\"userPost\":[{\"userTxtId\":33,\"userPost\":\"test \"}]},\"status\":\"SUCCESS\"}")));

        given()
        .contentType("application/json")
        .body("{\"userName\": \"sanket\", \"userPost\": [\"dfdfdffdgf\"]}")
        .when().post("/userPost").then()
        .statusCode(200)        
        .body(containsString("userName"))  
        .body(containsString("userPost"));   
    }	
	
	@Test
    public void invalidGetEndpointTest() {		        
        stubFor(post(urlEqualTo("/user/userPost"))
                .willReturn(aResponse()
                		.withStatus(405)));

        given()
        .contentType("application/json")        
        .when().post("/userPost").then()          // username is missing at the end of the endpoing
        .statusCode(405);                              //method not supported      
    }	
	
	@Test
    public void getUserTextDataTest() {			                 
        stubFor(post(urlEqualTo("/user/userPost/prachi"))        		
                .willReturn(aResponse()
                		.withStatus(200)));

        given()
        .contentType("application/json")                  
        .when().post("/userPost/prachi").then()
        .statusCode(200);                 
    }	
	
	@Test
	public void invaliUserCommentdEndpointTest() {		        
        stubFor(post(urlEqualTo("/user/userComment"))
                .willReturn(aResponse()
                		.withStatus(404)));

        given()
        .contentType("application/json")        
        .when().post("/userComment").then()          // incorrect end-point
        .statusCode(404);                 
    }	
	
	
	@Test
    public void saveUserCommentDataTest() {		               
        stubFor(post(urlEqualTo("/user/comment"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\"result\":{\"userName\":\"test\" ,\"userPost\":[{\"comments\":null, \"userTxtId\":33,\"userPost\":\"test \"}]},\"status\":\"SUCCESS\"}")));

        given()
        .contentType("application/json")
        .body("{\"userName\": \"sanket\", \"userTxtId\": \"3\", \"comment\": \"how is test\"}")
        .when().post("/comment").then()
        .statusCode(200)        
        .body(containsString("userName"))  
        .body(containsString("userPost"));   
    }	
	
	@Test
    public void invalidGetDataEndpointTest() {		        
        stubFor(post(urlEqualTo("/user/comment"))
                .willReturn(aResponse()
                		.withStatus(405)));

        given()
        .contentType("application/json")        
        .when().post("/comment").then()          // username is missing at the end of the endpoint
        .statusCode(405);                              //method not supported      
    }	
	
	@Test
    public void getUserCommentDataTest() {			                 
        stubFor(post(urlEqualTo("/user/comment/prachi"))        		
                .willReturn(aResponse()
                		.withStatus(200)));

        given()
        .contentType("application/json")                  
        .when().post("/comment/prachi").then()
        .statusCode(200);                 
    }	

	    
}