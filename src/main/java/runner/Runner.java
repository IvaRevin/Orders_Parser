package runner;

import model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import service.SpringReadFileService;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Runner implements CommandLineRunner {

    private static final int AWAIT_TERMINATION_TIMEOUT = 10;

    @Override
    public void run(String... args) {
        File file;
        File[] ArrayOffiles = new File[args.length];
        if (args.length > 0) {
            int i = 0;
            for (String arg : args) {
                file = new File(arg);
                ArrayOffiles[i] = file;
                i++;
            }
        }

        processDirectory(ArrayOffiles);


    }

    private static void processDirectory(File[] files) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        AtomicLong id = new AtomicLong(1);
        for (final File f : files) {
            if (!f.isFile()) {
                System.out.println("File " + f.getName() + " not found");
                continue;
            }

            service.execute(() -> {
                SpringReadFileService springReadFileService = new SpringReadFileService();
                List<Order> orders = springReadFileService.readDataFromUploadFile(f);
                if (orders != null) {
                    for (Order order : orders) {
                        order.setId(id.getAndIncrement());
                        System.out.println(order.toString());
                    }
                }
            });


        }

        service.shutdown();
        try {
            service.awaitTermination(AWAIT_TERMINATION_TIMEOUT, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
