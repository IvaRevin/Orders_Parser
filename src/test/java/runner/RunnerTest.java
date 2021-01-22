package runner;

import junit.framework.TestCase;

public class RunnerTest extends TestCase {

    public void testTestRun() {

        String[] fileNames = {"order1.csv","order2.json"};
        new Runner().run(fileNames);

    }
}