package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>();
        Map<String, Set<String>> friendList = generateFriendList(friends);
        Set<String> friendOfFriend = getFriendOfFriend(user, friendList);
        Map<String, Integer> visitorScore = countVisitorScore(visitors);
        Map<String, Integer> finalScore = countScore(friendOfFriend, visitorScore);
        answer = recommendList(finalScore, friendList.get(user));
        return answer;
    }

    // 1. 사용자별 친구인 목록을 저장하는 메서드
    private static Map<String, Set<String>> generateFriendList(List<List<String>> friends) {
        Map<String, Set<String>> friendList = new HashMap<>();

        for (List<String> list : friends) {
            String person1 = list.get(0);
            String person2 = list.get(1);

            connectFriends(friendList, person1, person2);
            connectFriends(friendList, person2, person1);
        }

        return friendList;
    }

    // 서로의 친구 목록에 서로를 저장
    private static Map<String, Set<String>> connectFriends(Map<String, Set<String>> friendList, String person1, String person2) {
        if (!friendList.containsKey(person1)) {
            Set<String> connectedFriends = new HashSet<>();
            connectedFriends.add(person2);
            friendList.put(person1, connectedFriends);
        } else {
            friendList.get(person1).add(person2);
        }

        return friendList;
    }

    // 2. user의 친구의 친구를 구하는 메서드
    private static Set<String> getFriendOfFriend(String user, Map<String, Set<String>> friendList) {
        Set<String> friendOfUser = friendList.get(user); // 유저의 친구들
        Set<String> friendOfFriendList = new HashSet<>();

        for (String s : friendOfUser) {
            Set<String> friendOfFriend = friendList.get(s); // 유저 친구의 친구들
            friendOfFriendList.addAll(friendOfFriend);
        }

        friendOfFriendList.remove(user);
        friendOfFriendList.removeAll(friendList.get(user));
        return friendOfFriendList;
    }

    // 3. 방문 친구별 횟수를 구하는 메서드
    private static Map<String, Integer> countVisitorScore(List<String> visitors) {
        Map<String, Integer> countOfVisitor = new HashMap<>();
        visitors.forEach(visitor -> countOfVisitor.merge(visitor, 1, Integer::sum));
        return countOfVisitor;
    }

    // 4. 규칙에 따라 점수를 매기는 메서드
    private static Map<String, Integer> countScore(Set<String> friendOfFriend, Map<String, Integer> countVisitor) {
        Map<String, Integer> scoreOfFriend = new HashMap<>();
        for (String s : friendOfFriend) {
            scoreOfFriend.merge(s, 10, (existValue, newValue) -> existValue + newValue);
        }

        for (String s : countVisitor.keySet()) {
            scoreOfFriend.merge(s, countVisitor.get(s), (existValue, newValue) -> existValue + newValue);
        }

        return scoreOfFriend;
    }

    // 5. 점수를 정렬하여 상위 5명을 구하는 메서드
    private static List<String> recommendList(Map<String, Integer> scoreOfFriend, Set<String> friendOfUser) {
        List<String> keySet = new ArrayList<>(scoreOfFriend.keySet());
        keySet.removeAll(friendOfUser);
        Collections.sort(keySet, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (scoreOfFriend.get(o1) == scoreOfFriend.get(o2)) return o1.compareTo(o2);
                else return scoreOfFriend.get(o2).compareTo(scoreOfFriend.get(o1));
            }
        });

        if (keySet.size() > 5) keySet.subList(0, 4);
        return keySet;
    }
}
