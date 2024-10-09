package functions;

import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Map;

public class Functions {

    @Getter
    @Setter
    public static StringWriter xmlData;

    @Getter
    @Setter
    public static Map<String, Object> snakeData;

    public static void loadXMLFile(String pathXML) {
        try {
            setXmlData(null);
            Source xmlSource = new StreamSource(pathXML);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
            StringWriter stringWriter = new StringWriter();
            transformer.transform(xmlSource, new StreamResult(stringWriter));
            setXmlData(stringWriter);
        } catch (TransformerException e) {
            System.out.println("Erro ao transformar o arquivo: " + e.getMessage());
        }
    }

    public static void loadYAMLData(String pathYAML) {
        try {
            setSnakeData(null);
            Yaml yaml = new Yaml();
            Object data = yaml.load(new FileInputStream(pathYAML));
            setSnakeData((Map<String, Object>) data);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public static String getValue(String field) {
        String[] fields = field.split(">");
        Map<String, Object> currentMap = (Map<String, Object>) getSnakeData().get(fields[0]);
        for (int i = 1; i < fields.length - 1; i++)
            currentMap = (Map<String, Object>) currentMap.get(fields[i]);
        return currentMap.get(fields[fields.length - 1]).toString();
    }

}
