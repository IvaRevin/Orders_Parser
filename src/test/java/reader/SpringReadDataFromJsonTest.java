package reader;

import model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpringReadDataFromJsonTest {

    @Test
    public void readData() {
        List<File> arrayOfFiles = new ArrayList<>();
        File notFoundFile = new File("order2.json");
        File jsonFile = new File("order3.json");
        arrayOfFiles.add(notFoundFile);
        arrayOfFiles.add(jsonFile);

        List<List<Order>> result = new ArrayList<>();
        for (File file : arrayOfFiles) {
            result.add(new SpringReadDataFromJson().readData(file));
        }
        List<Order> expected = new ArrayList<>();
        for (List<Order> orders : result) {
            if (orders != null) {
                expected.addAll(orders);
            }
        }

        List<Order> actual = new ArrayList<>();
        actual.add(new Order(1L, 1.23, "USD", "оплата заказа", "order3.json", 1, "OK"));
        actual.add(new Order(2L, 1.24, "EUR", "оплата заказа", "order3.json", 2, "OK"));

        Assert.assertEquals(expected.toString(), actual.toString());
    }
}