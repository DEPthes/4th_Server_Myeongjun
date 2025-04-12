package java.thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        System.out.println("거래 시작: " + getClass().getSimpleName());

        if (!lock.tryLock()) {
            System.out.println("[진입 실패] 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            if (balance < amount) { // 잔액이 출금액보다 부족하면 false 반환
                System.out.println("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }

            System.out.println("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance = balance - amount;
            System.out.println("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }
        System.out.println("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // ReentrantLock 이용하여 lock을 걸기
        try {
            return balance;
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }
    }
}
