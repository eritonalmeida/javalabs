package app.test;

import java.util.concurrent.TimeUnit;
import org.gearman.client.*;
import org.gearman.client.GearmanJob.JobPriority;
import org.gearman.util.ByteUtils;
import app.config.GearmanConfig;
import app.worker.TestFunction;

public class TestClient {

    private final GearmanClientImpl client;
    private final long timeout = 5;

    public TestClient() {
        client = new GearmanClientImpl();
        client.addJobServer(GearmanConfig.HOST, GearmanConfig.PORT);
    }

    public String reverse(String input) {

        byte[] data = ByteUtils.toUTF8Bytes(input);

        String uniqueId = null;

        GearmanJob job = GearmanJobImpl.createJob(
                TestFunction.QUEUE,
                data,
                JobPriority.NORMAL,
                uniqueId
        );

        client.submit(job);

        String response = "";

        try {
            GearmanJobResult result = job.get(timeout, TimeUnit.SECONDS);
            response = ByteUtils.fromUTF8Bytes(result.getResults());
        } catch (Exception e) {
        }

        return response;
    }

    public void shutdown() {
        if (client == null) {
            return;
        }

        client.shutdown();
    }

    public static void main(String[] args) {

        TestClient client = new TestClient();

        String payload = "1.2.3.4";

        System.out.println(client.reverse(payload));

        client.shutdown();
    }

}
