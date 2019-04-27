package hundeklemmen.shared.api;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class variablesHandler {
    public static HashMap<String, Object> load(Drupi instance)  {
        HashMap<String, Object> variables = new HashMap<String, Object>();
        File variablesFile = new File(instance.DataFolder, "variables.csv");
        if(variablesFile.exists()){
            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(variablesFile), "UTF-8"))) {
                while ((line = br.readLine()) != null) {

                    String[] data = line.split(cvsSplitBy);

                    String originalName = data[0];
                    String originalType = data[1];
                    String originalContent = new String(DatatypeConverter.parseBase64Binary(data[2]));

                    variables.put(originalName, Class.forName(originalType).cast(originalContent));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            instance.log.warning("Couldn't find variables files!");
        }

        return variables;
    }

    public static void save(Drupi instance){
        try {
            FileWriter fileWriter = new FileWriter(new File(instance.DataFolder, "variables.csv"));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Map.Entry<String, Object> entry : instance.variables.entrySet()) {
                String variableName = entry.getKey();
                String variableType = entry.getValue().getClass().getName();
                String variableContent = DatatypeConverter.printBase64Binary(String.valueOf(entry.getValue()).getBytes());
                printWriter.print(variableName + "," + variableType + "," + variableContent + "\n");
            }
            printWriter.close();
            instance.log.info("Saved variables to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
