package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem5 {
    static final int MONEY_MAX_LENGTH = 1000000;
    static final int MONEY_MIN_LENGTH = 1;

    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();
        validateMoneyRange(money);

        int[] bills = makeJIPYE();
        int balance = money;

        for (int jipye : bills) {
            if (!validateBalance(balance, money)) {
                answer.add(0);
            } else {
                int jipyeCount = balance / jipye;
                balance %= jipye;
                answer.add(jipyeCount);
            }
        }

        return answer;
    }

    private static void validateMoneyRange(int money) {
        if (money > MONEY_MAX_LENGTH || money < MONEY_MIN_LENGTH)
            throw new RuntimeException("금액은 1원 이상 1,000,000원 이하여야 합니다.");
    }

    private static boolean validateBalance(int balance, int jipye) {
        if (balance > 0 || balance > jipye) return true;
        return false;
    }

    public static int[] makeJIPYE() {
        int[] bills = new int[9];

        bills[0] = 50000;
        bills[1] = 10000;
        bills[2] = 5000;
        bills[3] = 1000;
        bills[4] = 500;
        bills[5] = 100;
        bills[6] = 50;
        bills[7] = 10;
        bills[8] = 1;

        return bills;
    }
}
