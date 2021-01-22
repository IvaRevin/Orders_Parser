package reader;

import model.Order;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpringReadDataFromJson implements SpringReadData {

    @Override
    public List<Order> readData(File file) {
        List<Order> result = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONParser parser = new JSONParser();
            int line = 0;
            String rowData = reader.readLine();
            while (rowData != null) {
                line++;
                JSONObject employee = (JSONObject) parser.parse(rowData);
                Long id = (Long) employee.get("orderId");
                Double amount = Double.parseDouble(String.valueOf(employee.get("amount")));
                String currency = (String) employee.get("currency");
                String comment = (String) employee.get("comment");
                result.add(new Order(id, amount, currency, comment, file.getName(), line, "OK"));

                rowData = reader.readLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input data format in file " + file.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("The file " + file.getName() +" contains a non-existent currency.");
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
