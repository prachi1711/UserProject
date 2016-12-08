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

	    
}