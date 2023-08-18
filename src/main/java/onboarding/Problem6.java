package onboarding;

import java.util.*;
import java.util.regex.Pattern;

public class Problem6 {
    static final int EMAIL_MIN_LENGTH = 11;
    static final int EMAIL_MAX_LENGTH = 20;
    static final int NICKNAME_MIN_LENGTH = 1;
    static final int NICKNAME_MAX_LENGTH = 20;
    static final String EMAIL_LENGTH_WARNING = "이메일의 길이는 11자 이상 20자 이하여야 합니다.";
    static final String EMAIL_FORM_WARNING = "이메일의 형식을 확인해주세요.";
    static final String NICKNAME_IS_ONLY_KOREAN = "닉네임은 한글만 가능합니다.";
    static final String NICKNAME_LENGTH_WARNING = "닉네임의 길이는 1자 이상 20자 이하여야 합니다.";

    public static List<String> solution(List<List<String>> forms) {
        Map<String, String> nicknameAndEmail = generateMap(forms);
        Set<String> duplicatedNickname = checkNicknameDuplication(nicknameAndEmail.keySet());
        List<String> emails = findEmailsOf(duplicatedNickname, nicknameAndEmail);
        List<String> answer = sortEmail(emails);
        return answer;
    }

    // 1. 닉네임, 이메일을 짝지어 저장하는 메서드
    private static Map<String, String> generateMap(List<List<String>> forms) {
        Map<String, String> nicknameAndEmail = new HashMap<>();
        for (List<String> list : forms) {
            String email = list.get(0);
            validateEmail(email);
            String nickname = list.get(1);
            validateNickname(nickname);
            nicknameAndEmail.put(nickname, email);
        }
        return nicknameAndEmail;
    }

    private static void validateEmail(String email) {
        // 이메일의 형식과 도메인을 검증하는 메서드
        if (!email.substring(email.length() - 10).equals("@email.com")) {
            throw new RuntimeException(EMAIL_FORM_WARNING);
        }

        // 이메일의 길이를 검증하는 메서드
        if (email.length() < EMAIL_MIN_LENGTH || email.length() > EMAIL_MAX_LENGTH) {
            throw new RuntimeException(EMAIL_LENGTH_WARNING);
        }
    }

    private static void validateNickname(String nickname) {
        // 닉네임이 한글인지 검증하는 메서드
        if (!Pattern.matches("^[ㄱ-ㅎ 가-힣]*$", nickname)) ;
        {
            new RuntimeException(NICKNAME_IS_ONLY_KOREAN);
        }

        // 닉네임의 길이를 검증하는 메서드
        if (nickname.length() < NICKNAME_MIN_LENGTH || nickname.length() > NICKNAME_MAX_LENGTH) {
            throw new RuntimeException(NICKNAME_LENGTH_WARNING);
        }
    }

    // 2. 닉네임 간의 같은 글자가 존재하는지 판별하는 메서드
    private static Set<String> checkNicknameDuplication(Set<String> nicknameList) {
        Set<String> duplicatedNicknames = new HashSet<>();

        for (String nickname : nicknameList) {
            List<String> word2OfNickname = generate2CharOfword(nickname);
            duplicatedNicknames.addAll(compareNicknameAndWord2(nicknameList, word2OfNickname, nickname));
        }

        return duplicatedNicknames;
    }

    // 3. 문자열의 모든 2문자를 구하는 메서드
    private static List<String> generate2CharOfword(String word) {
        List<String> word2List = new ArrayList<>();

        // 한 문자열에 대해 2개로 이루어진 문자
        for (int i = 0; i < word.length() - 1; i++) {
            word2List.add(word.substring(i, i + 2));
        }

        return word2List;
    }

    // 4. 닉네임과 2문자를 비교하는 메서드
    public static Set<String> compareNicknameAndWord2(Set<String> nicknameList, List<String> word2List, String nickname) {
        Set<String> duplicatedNicknames = new HashSet<>();

        for (String name : nicknameList) {
            for (String word2 : word2List) {
                if (name.contains(word2) && !name.equals(nickname)) {
                    duplicatedNicknames.add(name);
                }
            }
        }
        return duplicatedNicknames;
    }

    // 5. 중복된 닉네임일 시 그에 맞는 이메일들을 저장하는 메서드
    private static List<String> findEmailsOf(Set<String> duplicatedNickname, Map<String, String> nicknameAndEmail) {
        List<String> duplicatedEmails = new ArrayList<>();
        for (String nickname : duplicatedNickname) {
            String email = nicknameAndEmail.get(nickname);
            System.out.println(email);
            duplicatedEmails.add(email);
        }

        return duplicatedEmails;
    }

    // 6. 저장한 메일을 정렬하는 메서드
    private static List<String> sortEmail(List<String> emails) {
        Collections.sort(emails);
        return emails;
    }
}
