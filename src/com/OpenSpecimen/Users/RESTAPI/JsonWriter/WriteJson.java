package com.OpenSpecimen.Users.RESTAPI.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {

	public static void writeJsonInFile(String userList) throws IOException {
		try (FileWriter fileWriter = new FileWriter("User.json")) {	 
			fileWriter.write(userList.toString());
			fileWriter.flush();
			System.out.println("Data Successfully gets saved in: User.json file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}