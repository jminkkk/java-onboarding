package onboarding;

public class Problem4 {
    static final int WORD_MAX_LENGTH = 1000;
    static final int WORD_MIN_LENGTH = 1;
    static final String OVER_MAX_LENGTH = "길이는 1000자 이하여야 합니다.";
    static final String BELOW_MAX_LENGTH = "길이는 1자 이상여야 합니다.";
    public static String solution(String word) {
        String answer = "";
        validateLength(word);

        char[] charsOfWord = changeOf(word);

        for (char c : charsOfWord) {
            if (isAlphabet(c)) {
                answer += reverseChar(c);
            } else {
                answer += c;
            }
        }

        return answer;
    }

    private static void validateLength(String word) {
        if (word.length() > WORD_MAX_LENGTH) throw new RuntimeException(OVER_MAX_LENGTH);
        if (word.length() < WORD_MIN_LENGTH) throw new RuntimeException(BELOW_MAX_LENGTH);
    }

    private static boolean isAlphabet(Character c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) return true;
        return false;
    }

    // 문자열을 문자 배열로 쪼개는 메서드
    private static char[] changeOf(String word) {
        return word.toCharArray();
    }

    // 쪼갠 문자 배열에 대해 반대 문자를 구하는 메서드
    private static char reverseChar(char c) {
        if (c < 97)  { // 대문자라면
            return (char) Math.abs(c - 155);
        }
        return (char) Math.abs(c - 219);
    }
}
