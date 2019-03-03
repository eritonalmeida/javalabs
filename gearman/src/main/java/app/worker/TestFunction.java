package app.worker;

import app.model.TestJobRequest;
import app.model.TestJobResponse;
import com.google.gson.Gson;
import org.gearman.worker.AbstractGearmanFunction;
import org.gearman.client.GearmanJobResult;
import org.gearman.util.ByteUtils;

public class TestFunction extends AbstractGearmanFunction {

    public final static String QUEUE = "TestFunction";

    Gson gson = new Gson();

    public TestFunction() {
        super(QUEUE);
    }

    @Override
    public GearmanJobResult executeFunction() {

        String json = ByteUtils.fromUTF8Bytes((byte[]) this.data);

        TestJobRequest user = gson.fromJson(json, TestJobRequest.class);

        System.out.println(user.toString());

        TestJobResponse response = new TestJobResponse();
        response.setId(123456);
        response.setSuccess(true);

        return JobResult.create(this.jobHandle, response.toString().getBytes());
    }

}
