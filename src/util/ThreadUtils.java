package util;

public class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("인터럽트 발생 " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
