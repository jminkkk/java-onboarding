package onboarding;

import java.util.Arrays;
import java.util.List;

class Problem1 {
    static int answer;
    static final int POBI_IS_WINNER = 1;
    static final int CRONG_IS_WINNER = 2;
    static final int POBI_AND_CRONG_ARE_DRAW = 0;
    static final int UNKNOWN_EXCEPTION = -1;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int leftOfPobi = pobi.get(0);
        int rightOfPobi = pobi.get(1);
        int leftOfCrong = crong.get(0);
        int rightOfCrong = crong.get(1);

        int maxOfPobi = getMax(leftOfPobi, rightOfPobi);
        int maxOfCrong = getMax(leftOfCrong, rightOfCrong);

        answer = getWinner(maxOfPobi, maxOfCrong);
        if (!isValidateNums(leftOfPobi, rightOfPobi, leftOfCrong, rightOfCrong)) answer = UNKNOWN_EXCEPTION;

        return answer;
    }

    private static int getWinner(int maxOfPobi, int maxOfCrong) {
        if (maxOfPobi > maxOfCrong) return POBI_IS_WINNER;
        else if (maxOfPobi < maxOfCrong) return CRONG_IS_WINNER;
        else return POBI_AND_CRONG_ARE_DRAW;
    }

    private static boolean isValidateNums(int leftOfPobi, int rightOfPobi, int leftOfCrong, int rightOfCrong) {
        if ((leftOfPobi != rightOfPobi - 1)
                || (leftOfCrong != rightOfCrong - 1)
                || leftOfPobi == 0
                || rightOfPobi == 400
                || leftOfCrong == 0
                || rightOfCrong == 400) return false;
        return true;
    }

    private static int getHap (String[] nums) {
        int hap = 0;
        for (String i : nums) {
            hap += Integer.parseInt(i);
        }

        return hap;
    }

    private static int getGop (String[] nums) {
        int gop = 1;
        for (String i : nums) {
            gop *= Integer.parseInt(i);
        }

        return gop;
    }

    public static int getMax(int leftNum, int rightNum) {
        // 1. 각 자리수 구하기
        String[] leftNums = String.valueOf(leftNum).split("");
        String[] rightNums = String.valueOf(rightNum).split("");

        // 2. 합, 곱 구하기
        int leftHap = getHap(leftNums);
        int leftGop = getGop(leftNums);

        int rightHap = getHap(rightNums);
        int rightGop = getGop(rightNums);

        // 3. 최대값 구하기
        return Arrays.asList(leftHap, leftGop, rightHap, rightGop)
                .stream()
                .mapToInt(x -> x)
                .max()
                .getAsInt();
    }
}