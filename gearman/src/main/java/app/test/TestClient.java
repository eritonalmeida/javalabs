package app.test;

import app.client.JobClient;
import app.worker.TestFunction;

public class TestClient {

    public static void main(String[] args) {

        JobClient client = new JobClient();

        String payload = "1.2.3.4";

        System.out.println(client.executeJob(TestFunction.QUEUE, payload));

        client.shutdown();
    }

}
