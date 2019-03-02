package app.worker;

import org.gearman.worker.GearmanWorkerImpl;
import org.gearman.worker.AbstractGearmanFunction;
import app.config.GearmanConfig;

public class AbstractWorker implements Runnable {

    protected GearmanWorkerImpl worker;

    public AbstractWorker(Class<? extends AbstractGearmanFunction> function) {

        worker = new GearmanWorkerImpl();

        worker.addServer(GearmanConfig.HOST, GearmanConfig.PORT);

        worker.registerFunction(function);
    }

    @Override
    public void run() {
        worker.work();
    }

    public static void init(Class<? extends AbstractWorker> workerClass, String[] args) {
        int threads = 1;

        try {

            if (args.length > 0) {
                threads = new Integer(args[0]);
            }

            for (int i = 1; i <= threads; i++) {
                System.out.println("starting thread " + i);
                new Thread(workerClass.newInstance()).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
