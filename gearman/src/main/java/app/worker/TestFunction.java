package app.worker;

import org.gearman.worker.AbstractGearmanFunction;
import org.gearman.client.GearmanJobResult;
import org.gearman.util.ByteUtils;

public class TestFunction extends AbstractGearmanFunction {

    public final static String QUEUE = "TestFunction";

    public TestFunction() {
        super(QUEUE);
    }

    @Override
    public GearmanJobResult executeFunction() {

        String buffer = ByteUtils.fromUTF8Bytes((byte[]) this.data);
        
        System.out.println(buffer);

        String response = new StringBuffer(buffer).reverse().toString();

        return JobResult.create(this.jobHandle, response.getBytes());
    }

}
