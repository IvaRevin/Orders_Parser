package reader;

import model.Order;

import java.io.File;
import java.util.List;


public interface SpringReadData {
    List<Order> readData(File file);
}
