package com.OpenSpecimen.Users.RESTAPI.APICaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.OpenSpecimen.Users.RESTAPI.RequestResource;
import com.OpenSpecimen.Users.RESTAPI.JsonWriter.WriteJson;

public class REST_API_Caller extends RequestResource {

	public void readUserData() throws IOException {
		System.out.println("GET");
		urlConnection.setRequestMethod("GET");
		responseCode = urlConnection.getResponseCode();
		System.out.println("\nRequest Status Code is:" + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while((readLine = httpResponseReader.readLine()) != null) {
				WriteJson.writeJsonInFile(readLine);
				System.out.println("\nJSON DATA :" + readLine);
			}httpResponseReader.close();	
		} else {
			System.err.println("\n Unable to read data from the Server");
		}
	}

	public void createNewUser(String POST_PARAMS) throws IOException {
		System.out.println("POST");
		System.out.println("\n" + POST_PARAMS);
		urlConnection = (HttpURLConnection) serverUrl.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.addRequestProperty("Authorization", basicUrlAuthentication);

		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setDoOutput(true);
		OutputStream outputStream = urlConnection.getOutputStream();
		outputStream.write(POST_PARAMS.getBytes());
		int responseCode = urlConnection.getResponseCode();
		System.out.println("\nPOST Response Code :  " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { 
			httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = httpResponseReader .readLine()) != null) {
				response.append(readLine);
			} httpResponseReader .close();
			System.out.println("\nNew Created User is:" +response.toString());
		} else {
			System.err.println("\n Unable to create USER from the Server");
		}
	}

	public void updateUserData(String POST_PARAMS) throws IOException {
		System.out.println("UPDATE");
		System.out.println("\n" + POST_PARAMS);

		serverUrl = new URL("http://localhost:8080/openspecimen/rest/ng/users/18");

		urlConnection = (HttpURLConnection) serverUrl.openConnection();
		urlConnection.setRequestMethod("PUT");
		urlConnection.addRequestProperty("Authorization", basicUrlAuthentication);
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setDoOutput(true);

		OutputStream outputStream = urlConnection.getOutputStream();
		outputStream.write(POST_PARAMS.getBytes());
		int responseCode = urlConnection.getResponseCode();
		System.out.println("\nPUT Response Code :  " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { 
			httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = httpResponseReader .readLine()) != null) {
				response.append(readLine);
			} httpResponseReader .close();
			System.out.println("\nUpdated User Information:" +response.toString());
		} else {
			System.err.println("\n USER_NOT_Found");
		}
	}

	public void deleteUser() throws IOException {
		System.out.println("DELETE");
		serverUrl = new URL("http://localhost:8080/openspecimen/rest/ng/users/18");

		urlConnection = (HttpURLConnection) serverUrl.openConnection();
		urlConnection.setRequestMethod("DELETE");
		urlConnection.addRequestProperty("Authorization", basicUrlAuthentication);
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setDoOutput(true);

		int responseCode = urlConnection.getResponseCode();
		System.out.println("\nDelete Response Code :  " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { 
			httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = httpResponseReader .readLine()) != null) {
				response.append(readLine);
			} httpResponseReader .close();
			System.out.println("\nUser Deleted Successfully:" +response.toString());
		} else {
			System.err.println("\n USER_NOT_Found");
		}
	}
}
