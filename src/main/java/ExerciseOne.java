package main.java;

import java.io.*;

public class ExerciseOne {

    public ExerciseOne(String path) {
        File file = new File(path);
        checkIfFileExists(file);
        String numbers = readNumbers(file);
        String validNumbers = checkIfNumbersValid(numbers);
        print(validNumbers);

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

    private String readNumbers(File file) {
        StringBuilder numbers = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            numbers.append(line).append("\n");
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    numbers.append(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers.toString();
    }

    private String checkIfNumbersValid(String numbers) {
        StringBuilder result = new StringBuilder();
        String regex = "\\(\\d{3}\\)\s[0-9]{3}-[0-9]{4}";
        String regex1 = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
        String[] numberArray = numbers.split("\n");
        for (String number : numberArray) {
            if (number == null) {
                continue;
            }
            if (number.matches(regex) || number.matches(regex1)) {
                result.append(number).append("\n");
            }
        }
        return result.toString();
    }

    private void print(String validNumber) {
        System.out.println(validNumber);
    }
}
