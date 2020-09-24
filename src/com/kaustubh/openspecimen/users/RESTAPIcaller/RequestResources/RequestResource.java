package com.kaustubh.openspecimen.users.RESTAPIcaller.RequestResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.kaustubh.openspecimen.users.RESTAPIcaller.RESTAPICaller.REST_API_Caller;

public class RequestResource {

	public static String usernamePassword = "admin:Login@123";
	public static String basicUrlAuthentication = "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes());
	public static URL serverUrl = null;
	public static HttpURLConnection urlConnection = null;
	public static BufferedReader httpResponseReader = null;
	public String readLine;
	public int responseCode;
	final static String POST_PARAMS = 
			"{\n" + 
					"    \"firstName\": \"Kaustubh12\",\r\n" +
					"    \"lastName\": \"Wadagavi12\",\r\n" +
					"    \"loginName\": \"kaustubh12\",\r\n" +
					"    \"domainName\": \"openspecimen\",\r\n" +
					"    \"emailAddress\": \"kaustubhwadagavi12@gmail.com\",\r\n" +
					"    \"instituteName\": \"KRISHAGNI_(INST)\",\r\n" +
					"    \"primarySite\": \"Krishagni Site (INST)\"" + 
					"\n}";
	
	final static String PUT_PARAMS = 
			"{\n" + 
					"    \"firstName\": \"kaustubh123\",\r\n" +
					"    \"lastName\": \"Wadagavi\",\r\n" +
					"    \"loginName\": \"kaustubh1\",\r\n" +
					"    \"domainName\": \"openspecimen\",\r\n" +
					"    \"emailAddress\": \"kaustubh12@gmail.com\",\r\n" +
					"    \"instituteName\": \"KRISHAGNI_(INST)\",\r\n" +
					"    \"primarySite\": \"Krishagni Site (INST)\"" + 
					"\n}";
	
	public static void main(String args[]) {
		System.out.println("\nUsers All REST API Caller");
		try {
			serverUrl = new URL("http://localhost:8080/openspecimen/rest/ng/users");
			urlConnection = (HttpURLConnection) serverUrl.openConnection();
			urlConnection.addRequestProperty("Authorization", basicUrlAuthentication);
		} catch (IOException e) {
			e.printStackTrace();
		}
		methodsCaller();
	}

	public static void methodsCaller() {
		REST_API_Caller restApiCaller = new REST_API_Caller();
		try {
			restApiCaller.readUserData();
			restApiCaller.createNewUser(POST_PARAMS);
			restApiCaller.updateUserData(PUT_PARAMS);
			restApiCaller.deleteUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
