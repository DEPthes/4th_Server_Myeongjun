package java.thread.sync;

import static java.util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        //BankAccount account = new BankAccountV1(1000); // 동시성 문제 발생
        //BankAccount account = new BankAccountV2(1000); // synchronized
        //BankAccount account = new BankAccountV3(1000); // 부분 synchronized
        //BankAccount account = new BankAccountV4(1000); // 락 사용
        BankAccount account = new BankAccountV5(1000); // tryLock 시도 후 락이 없을시 즉시 반환

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기

        t1.join();
        t2.join();

        System.out.println("최종 잔액: " + account.getBalance());
    }

    public static class WithdrawTask implements Runnable {

        private BankAccount account;
        private int amount;

        public WithdrawTask(BankAccount account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            account.withdraw(amount);
        }
    }
}

