package app.test;

import app.client.JobClient;
import app.model.TestJobRequest;
import app.worker.TestFunction;

public class TestClient {

    public static void main(String[] args) {

        JobClient client = new JobClient();

        TestJobRequest user = new TestJobRequest();
        user.setName("Eriton Almeida");
        user.setEmail("erytonalmieda@gmail.com");

        System.out.println(client.executeJob(TestFunction.QUEUE, user.toString()));

        client.shutdown();
    }

}
