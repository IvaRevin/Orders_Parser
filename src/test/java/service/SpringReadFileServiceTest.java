package service;

import model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpringReadFileServiceTest {

    @Test
    public void readDataFromUploadFile() {

        List<File> arrayOfFiles = new ArrayList<>();
        File csvFile = new File("order1.csv");
        File notFoundFile = new File("order2.json");
        File jsonFile = new File("order3.json");
        File otherFileFormat = new File("order2.xlsx");
        arrayOfFiles.add(csvFile);
        arrayOfFiles.add(notFoundFile);
        arrayOfFiles.add(jsonFile);
        arrayOfFiles.add(otherFileFormat);

        List<List<Order>> result = new ArrayList<>();
        for (File file : arrayOfFiles) {
            result.add(new SpringReadFileService().readDataFromUploadFile(file));
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
        actual.add(new Order(1L, 1.23, "USD", "оплата заказа", "order3.json", 1, "OK"));
        actual.add(new Order(2L, 1.24, "EUR", "оплата заказа", "order3.json", 2, "OK"));

        Assert.assertEquals(expected.toString(), actual.toString());
    }
}