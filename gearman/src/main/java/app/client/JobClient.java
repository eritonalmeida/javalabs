package app.client;

import java.util.concurrent.TimeUnit;
import org.gearman.client.*;
import org.gearman.client.GearmanJob.JobPriority;
import org.gearman.util.ByteUtils;
import app.config.GearmanConfig;

public class JobClient {

    private GearmanClientImpl client;
    private long timeout;

    public JobClient(long timeout) {
        this.timeout = timeout;
        client = new GearmanClientImpl();
        client.addJobServer(GearmanConfig.HOST, GearmanConfig.PORT);
    }

    public JobClient() {
        this(5);
    }

    public String executeJob(String queue, String data) {
        return this.executeJob(queue, data, JobPriority.NORMAL, "");
    }

    public String executeJob(String queue, String data, JobPriority priority, String uniqueId) {

        byte[] jobData = ByteUtils.toUTF8Bytes(data);

        GearmanJob job = GearmanJobImpl.createJob(
                queue,
                jobData,
                priority,
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
}
