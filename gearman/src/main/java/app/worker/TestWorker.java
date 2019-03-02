package app.worker;

public class TestWorker extends AbstractWorker {

    public TestWorker() {
        super(TestFunction.class);
    }

    public static void main(String[] args) {
        init(TestWorker.class, args);
    }
}
