package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseTwo {

    public ExerciseTwo(String path) {
        File file = new File(path);
        checkIfFileExists(file);
        String users = readNameAndAge(file);
        List<User> usersList = createUsersList(users);
        if (!usersList.isEmpty()) {
            File jsonFile = createJsonFile();
            writeIntoJsonFile(usersList, jsonFile);
            System.out.println("Check the user.json file");
        }
    }

    private File createJsonFile() {
        String path = "resources/ExTwo/user.json";
        File json = new File(path);
        if (!json.exists()) {
            json.getParentFile().mkdirs();
            try {
                json.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }


    private void checkIfFileExists(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readNameAndAge(File file) {
        StringBuilder users = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                users.append(line).append("\n");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.toString();
    }

    private List<User> createUsersList(String users) {
        List<User> userList = new ArrayList<>();
        String[] userData = users.split("\n");
        for (int i = 2; i < userData.length; i++) {
            String[] nameAndAge = userData[i].split(" ");
            userList.add(new User(nameAndAge[0], nameAndAge[1]));
        }
        return userList;
    }

    private void writeIntoJsonFile(List<User> users, File json) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(json))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(users);
            bufferedWriter.write(s);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
