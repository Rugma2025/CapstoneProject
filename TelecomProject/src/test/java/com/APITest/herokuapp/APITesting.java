package com.APITest.herokuapp;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class APITesting {
	
	String token1, token2;
	String email,id;
	
  @Test(priority=1)
  public void testAddNewUser() {
	  
	  //email
	  String userEmail="rugma"+System.currentTimeMillis()+"@gmail.com";
	  
	  //payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("firstName","Rugma");
	  data.put("lastName","S");
	  data.put("email",userEmail);
	  data.put("password","rugma123");
	  
	  Response res=given()
			  .header("Content-Type","application/json")
			       .body(data)
			       .when().post("https://thinking-tester-contact-list.herokuapp.com/users");
      //log the response
	  res.then().log().all();
	  
	  //get the token
	  token1=res.jsonPath().getString("token");
	  System.out.println("Token generated is: "+token1);
	  
	  //validate statuscode
	  Assert.assertEquals(res.getStatusCode(),201);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("201 Created"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
	  
  }
  @Test(priority=2)
  public void testGetUserProfile()
  {
	  Response res=given()
	    .header("Authorization","Bearer "+token1)
	      .when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
	  
	  //log the response
	  res.then().log().body();
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
  }
  @Test(priority=3)
  public void testUpdateUser()
  {
	  String userEmail="rugmas"+System.currentTimeMillis()+"@gmail.com";
	  //payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("firstName","Rugma");
	  data.put("lastName","S");
	  data.put("email",userEmail);
	  data.put("password","test123");
	  
	  
	  Response res=given()
	   .header("Content-Type","application/json")
	    .header("Authorization","Bearer "+token1)
	    .body(data)
	     .when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
	  
	  //get the updated email from response
	 email = res.jsonPath().getString("email");
	 System.out.println("Updated email is: "+email);
	  
	  //log the response
	  res.then().log().body();
	  
	
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
  }
  
  @Test(priority=4)
  public void testLogInUser()
  {
	//payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  
	  data.put("email",email);
	  data.put("password","test123");
	  
	  Response res=given()
	  .header("Content-Type","application/json")
	   .body(data)
	   .when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");
  
    //log the response
	  res.then().log().body();
	  
	 //get the token
	  token2=res.jsonPath().getString("token");
	  System.out.println("The token generated is: "+token2);
	
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
	  
  }
  @Test(priority=5)
  
  public void testAddContact()
  {
	 //payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("firstName","Rugma");
	  data.put("lastName","S");
	  data.put("birthdate","2000-01-01");
	  data.put("email",email);
	  data.put("phone","8005555555");
	  data.put("street1","1 Main St.");
	  data.put("street2","Apartment A");
	  data.put("city","Anytown");
	  data.put("stateProvince","KL");
	  data.put("postalCode","12345");
	  data.put("country","INDIA");
	  
	  Response res=given()
	  .header("Content-Type","application/json")
	  .header("Authorization","Bearer "+token2)
	  .body(data)
	  .when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
  
	//log the response
	  res.then().log().body();
	  
	 //validate statuscode
	  Assert.assertEquals(res.getStatusCode(),201);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("201 Created"));
	  System.out.println("Status message matched! "+res.getStatusLine());
  
  }
  
  @Test(priority=6)
  
  public void testGetContactList()
  {
	  Response res=given()
	  .header("Authorization","Bearer "+token2)
	  .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
	  
	  
	//log the response
	  res.then().log().body();
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
  }
  
  @Test(priority=7)
  public void testGetContact()
  {
	  Response res=given()
			       .header("Authorization","Bearer "+token2)
			       .when().get(" https://thinking-tester-contact-list.herokuapp.com/contacts/");
	  
	  //log the response
	  res.then().log().body();
	  
	  //get the id from response
	  id = res.jsonPath().getString("[0]._id");
	  System.out.println("The id generated is: "+id);
	  
	//validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
			       		
  }
  
  @Test(priority=8)
  public void testFullUpdateContact()
  {
	  //get payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("firstName","Padma");
	  data.put("lastName","M");
	  data.put("birthdate","1999-02-02");
	  data.put("email","padma@gmail.com");
	  data.put("phone","9005554242");
	  data.put("street1","13 School St.");
	  data.put("street2","Apt. 5");
	  data.put("city","Washington");
	  data.put("stateProvince","QC");
	  data.put("postalCode","A1A1A1");
	  data.put("country","Canada");
	  
	  Response res=given()
			       .header("Content-Type","application/json")
			       .header("Authorization","Bearer "+token2)
			       .body(data)
			       .when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/"+id);
	  //log the response
	  res.then().log().body();
	  
	  
	  //validate for email
	  String updatedEmail=res.jsonPath().getString("email");
	  Assert.assertEquals(updatedEmail, "padma@gmail.com");
	  System.out.println("Email updated.."+updatedEmail);
	  
	//validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
  }
  
  @Test(priority=9)
  public void testPartialUpdateContact()
  {
	  //payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("firstName","Anna");
	  
	  Response res=given()
			       .header("Content-Type","application/json")
			        .header("Authorization","Bearer "+token2)
			        .body(data)
			        .when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/"+id);
	  
	  //log the response
	  res.then().log().body();
	  
	  //validate first name
	  String firstname=res.jsonPath().getString("firstName");
	  Assert.assertEquals(firstname, "Anna");
	  System.out.println("First Name updated.."+firstname);
	  
	  
	//validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
	  
  }
  
  @Test(priority=10)
  public void testLogoutUser()
  {
	  Response res=given()
			       .header("Authorization","Bearer "+token2)
			       .when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
	  
	//validate status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched! "+res.getStatusCode());
	  
	  //validate status message
	  Assert.assertTrue(res.getStatusLine().contains("200 OK"));
	  System.out.println("Status message matched! "+res.getStatusLine());
			       
  }
  
}
