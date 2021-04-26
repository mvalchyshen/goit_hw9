package main.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExerciseThree {
    public ExerciseThree(String path) {
        File file = new File(path);
        checkIfFileExists(file);
        String words = readTextFromFile(file);
        String result = countWords(words);
        System.out.println(result);
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

    private String readTextFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                sb.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

    private String countWords(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");
        HashMap<String, Integer> wordMap = new HashMap<>();
        int count;
        for (int i = 0; i < words.length; i++) {
            count = 0;
            for (int j = 0; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    count++;
                }
            }
            wordMap.put(words[i], count);
        }
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(wordMap.entrySet());
        entryList.sort(((o1, o2) -> o2.getValue() - o1.getValue()));
        for (Map.Entry<String, Integer> entry : entryList) {
            result.append(entry.getKey())
                    .append(" ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return result.toString().trim();
    }
}
