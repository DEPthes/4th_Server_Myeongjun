package java.thread.volatile1;

import static java.util.ThreadUtils.*;

public class VolatileCount {

    public static void main(String[] args) {
        Task task = new Task();
        Thread t = new Thread(task, "work");
        System.out.println("flag = " + task.flag);
        t.start();

        sleep(1000);
        System.out.println("flag를 false로 변경 시도");
        task.flag = false;
        System.out.println("flag = " + task.flag);
        System.out.println("spring.main 종료");
    }

    static class Task implements Runnable {
        //boolean flag = true;
        volatile boolean flag = true;

        @Override
        public void run() {
            System.out.println("task 시작");
            while (flag) {
                // flag가 false로 변하면 탈출
            }
            System.out.println("task 종료");
        }
    }
}
