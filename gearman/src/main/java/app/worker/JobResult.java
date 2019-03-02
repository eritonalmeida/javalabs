package app.worker;

import org.gearman.client.GearmanJobResult;
import org.gearman.client.GearmanJobResultImpl;

public class JobResult {

    public static GearmanJobResult create(byte[] jobHandle, byte[] response) {
        GearmanJobResult result = new GearmanJobResultImpl(
                jobHandle,
                //success
                true,
                response,
                //warnings
                new byte[0],
                //exceptions
                new byte[0],
                //numerator
                0,
                //denominator
                0
        );

        return result;
    }
}
