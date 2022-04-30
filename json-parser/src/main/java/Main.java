import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "json-parser/data.json";
        String json = readString(url);
        List<Employee> list = jsonToList(json);
        for (Employee item : list) {
            System.out.println(item);
        }

    }

    private static String readString(String url) {
        String json = null;
        try {
            File file = new File(url);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            json = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            for (Object item : jsonArray) {
                Employee employee = gson.fromJson(item.toString(), Employee.class);
                list.add(employee);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
