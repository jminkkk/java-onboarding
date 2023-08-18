package onboarding;

import java.util.HashSet;
import java.util.Set;

public class Problem2 {
    static final int CRYPTOGRAM_MIN_LENGTH = 1;
    static final int CRYPTOGRAM_MAX_LENGTH = 100;
    static final String CRYPTOGRAM_LENGTH_EXCEPTION = "cryptogram은 길이가 1 이상 1000 이하인 문자열이여야 합니다.";
    static final String CRYPTOGRAM_LOWER_CASE_EXCEPTION = "cryptogram은 알파벳 소문자로만 이루어져 있어야 합니다.";

    public static String solution(String cryptogram) {
        isValidated(cryptogram);
        String answer = repeatToRemove(cryptogram);
        return answer;
    }

    private static void isValidated(String cryptogram) {
        isEnableLength(cryptogram.length());
        isAllLowerCase(cryptogram);
    }

    private static void isEnableLength(int length) {
        if (length < CRYPTOGRAM_MIN_LENGTH && length > CRYPTOGRAM_MAX_LENGTH)
            throw new RuntimeException(CRYPTOGRAM_LENGTH_EXCEPTION);
    }

    private static void isAllLowerCase(String cryptogram) {
        for (Character c: cryptogram.toCharArray())
            if (!Character.isLowerCase(c)) throw new RuntimeException(CRYPTOGRAM_LOWER_CASE_EXCEPTION);
    }

    public static String repeatToRemove(String duplicatedWord) {
        String newWord = removeDuplication(duplicatedWord);

        if (existDuplication(duplicatedWord, newWord)) {
            return repeatToRemove(newWord);
        }
        return newWord;
    }

    public static boolean existDuplication(String original, String newWord) {
        if (original.equals(newWord)) return false;
        return true;
    }

    public static String removeDuplication(String word) {
        Set<Integer> indexList = duplicatedIndexList(word);

        char[] changedArr = changeIndexValue(indexList, word);
        String removedWord = mergeCharArr(changedArr, word);

        return removedWord;
    }


    public static Set<Integer> duplicatedIndexList(String word) {
        Set<Integer> indexList = new HashSet<>();
        for (int i = 1 ; i < word.length(); i++) {
            if (word.charAt(i - 1) == word.charAt(i)) {
                indexList.add(i - 1);
                indexList.add(i);
            }
        }
        return indexList;
    }

    public static char[] changeIndexValue(Set<Integer> indexList, String word) {
        char[] charArrFromWord = word.toCharArray();

        for (Integer i : indexList) {
            charArrFromWord[i] = 0;
        }

        return charArrFromWord;
    }

    public static String mergeCharArr(char[] charArrFromWord, String word) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (charArrFromWord[i] != 0) sb.append(charArrFromWord[i]);
        }
        return sb.toString();
    }
}
