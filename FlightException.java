public class FlightException extends Exception { 
    public FlightException(String message) {
        super(message);
    }

    // Functional Interface to allow throwing exceptions in lambdas
    @FunctionalInterface
    interface ExceptionThrowingTask {
        void run() throws FlightException;
    }

    public static void handleException(ExceptionThrowingTask task) {
        try {
            task.run();
        } catch (FlightException e) {
            System.out.println("Flight Exception: " + e.getMessage());
        }
    }
}
