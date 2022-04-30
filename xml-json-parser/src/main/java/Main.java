import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static WriteJson writeJson = new WriteJson();
    public static void main(String[] args) {
        String url = "xml-json-parser/data.xml";
        writeJson.setFileJson("xml-json-parser/data.json");
        List<Employee> list = parseXML(url);
        String json = writeJson.listToJson(list);
        writeJson.writeString(json);
    }

    private static List<Employee> parseXML(String url) {
        List<Employee> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(url);
            Document document = builder.parse(file);

            Node node = document.getDocumentElement();
            NodeList nodeList = node.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    list.add(parseEmployee(element));
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    private static Employee parseEmployee(Element employee) {
        long id = Long.parseLong(valueEmployee(employee, "id"));
        String firstName = valueEmployee(employee, "firstName");
        String lastName = valueEmployee(employee, "lastName");
        String country = valueEmployee(employee, "country");
        int age = Integer.parseInt(valueEmployee(employee, "age"));

        return new Employee(id, firstName, lastName, country, age);
    }

    private static String valueEmployee(Element employee, String name) {
       return employee.getElementsByTagName(name).item(0).getTextContent();
    }

}
