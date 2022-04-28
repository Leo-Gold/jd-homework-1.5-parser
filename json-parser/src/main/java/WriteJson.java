import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class WriteJson {
    private String fileJson;

    public void setFileJson(String fileJson) {
        this.fileJson = fileJson;
    }

    protected  <T> String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(list, listType);
    }

    protected void writeString(String json) {
        try (FileWriter file = new FileWriter(fileJson)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

