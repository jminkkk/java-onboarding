package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem3 {
    static final String CAN_NOT_START = "1 이상부터 게임 시작이 가능합니다.";
    public static int solution(int number) {
        int answer = 0;
        int index = 1;

        validated(index);

        while (index <= number) {
            List<Integer> alpabetOfNum = splitNum(index);
            answer += checkCount(3, alpabetOfNum);
            answer += checkCount(6, alpabetOfNum);
            answer += checkCount(9, alpabetOfNum);

            index++;
        }

        return answer;
    }

    static void validated(int number) {
        if (number < 0) throw new RuntimeException(CAN_NOT_START);
    }

    // 숫자 분리
    public static List<Integer> splitNum(int number) {
        String[] arr =  String.valueOf(number).split("");

        List<Integer> alpabetList = new ArrayList<>();
        for (String s : arr) {
            alpabetList.add(Integer.parseInt(s));
        }

        return alpabetList;
    }

    // 369 몇번 확인
    public static int checkCount(int checkPoint, List<Integer> alpabetList) {
        int count = 0;
        for (int i : alpabetList) {
            if (i == checkPoint) {
                count++;
            }
        }
        return count;
    }
}
