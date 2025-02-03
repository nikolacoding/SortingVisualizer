package general;

public final class Utility {
    public static void Sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Neuspjesan Thread.sleep() poziv: " + e.getMessage());
        }
    }
}
