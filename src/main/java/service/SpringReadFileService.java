package service;

import org.apache.commons.io.FilenameUtils;
import model.Order;
import org.springframework.stereotype.Service;
import reader.SpringReadData;
import reader.SpringReadDataFromCsv;
import reader.SpringReadDataFromJson;

import java.io.*;
import java.util.List;

@Service
public class SpringReadFileService {
    public List<Order> readDataFromUploadFile(File file) {
        List<Order> answer = null;
        if(file.isFile()) {
            String extension = FilenameUtils.getExtension(file.getName());
            SpringReadData springReadData;
            if (extension.equalsIgnoreCase("json")) {
                springReadData = new SpringReadDataFromJson();
                answer = springReadData.readData(file);
            } else if (extension.equalsIgnoreCase("csv")) {
                springReadData = new SpringReadDataFromCsv();
                answer = springReadData.readData(file);
            } else {
                System.out.println("Wrong file format " + file.getName() + ", enter .json or .csv");
            }
            return answer;
        }else {
            System.out.println("File " + file.getName() + " not found");
            return answer;
        }

    }
}
