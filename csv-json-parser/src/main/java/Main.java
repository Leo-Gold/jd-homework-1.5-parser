import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    static WriteJson writeJson = new WriteJson();

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "csv-json-parser/data.csv";
        writeJson.setFileJson("csv-json-parser/data.json");
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = writeJson.listToJson(list);
        writeJson.writeString(json);
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee> list = null;
        try (
                FileReader fileReader = new FileReader(fileName);
                CSVReader reader = new CSVReader(fileReader)
        ) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();

            list = csv.parse();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }


}
