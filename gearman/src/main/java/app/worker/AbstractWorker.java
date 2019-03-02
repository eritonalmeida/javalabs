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
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        String[] command = System.getProperty("sun.java.command").split(" ");
        
        Class workerClass = Class.forName(command[0]);
        
        int threads = 1;
        
        if (args.length == 1) {
            threads = new Integer(args[0]);
        }
                   
        for (int i = 0; i < threads; i++) {
            new Thread((AbstractWorker) workerClass.newInstance()).start();
        }
    }
   
}
