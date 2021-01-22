package reader;

import com.opencsv.CSVReader;
import model.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpringReadDataFromCsv implements SpringReadData {

    @Override
    public List<Order> readData(File file) {
        List<Order> result = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(file));
            List<String[]> list = csvReader.readAll();
            int line = 0;
            for (String[] array : list) {
                line++;
                result.add(new Order(Long.parseLong(array[0]), Double.parseDouble(array[1]), array[2], array[3], file.getName(), line, "OK"));
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input data format in file: " + file.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("The file " + file.getName() +" contains a non-existent currency");
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
