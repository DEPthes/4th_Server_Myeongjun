package thread.sync;

import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) {
        System.out.println("거래 시작: " + getClass().getSimpleName());

        if (balance < amount) { // 잔액이 출금액보다 부족하면 false 반환
            System.out.println("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
            return false;
        }

        System.out.println("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); // 출금에 걸리는 시간으로 가정
        balance = balance - amount;
        System.out.println("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);

        System.out.println("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
