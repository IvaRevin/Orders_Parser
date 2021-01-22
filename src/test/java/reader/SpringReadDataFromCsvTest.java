package reader;

import model.Order;
import org.junit.Assert;
import org.junit.Test;
import service.SpringReadFileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpringReadDataFromCsvTest {

    @Test
    public void readData() {

        List<File> arrayOfFiles = new ArrayList<>();
        File csvFile = new File("order1.csv");
        File notFoundFile = new File("order2.json");
        arrayOfFiles.add(csvFile);
        arrayOfFiles.add(notFoundFile);

        List<List<Order>> result = new ArrayList<>();
        for (File file : arrayOfFiles) {
            result.add(new SpringReadDataFromCsv().readData(file));
        }
        List<Order> expected = new ArrayList<>();
        for (List<Order> orders : result) {
            if (orders != null) {
                expected.addAll(orders);
            }
        }

        List<Order> actual = new ArrayList<>();
        actual.add(new Order(1L, 100.0, "RUB", "оплата заказа", "order1.csv", 1, "OK"));
        actual.add(new Order(2L, 500.0, "EUR", "оплата заказа", "order1.csv", 2, "OK"));
        actual.add(new Order(3L, 1000.0, "USD", "оплата заказа", "order1.csv", 3, "OK"));

        Assert.assertEquals(expected.toString(), actual.toString());
    }
}