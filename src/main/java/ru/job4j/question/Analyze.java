package ru.job4j.question;

import java.util.*;

public class Analyze {

    private static Map<Integer, String> createMapFromSet(Set<User> set) {
        Map<Integer, String> map = new HashMap<>();
        for (User user : set) {
            map.put(user.getId(), user.getName());
        }
        return map;
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        int addCounter = 0;
        int changCounter = 0;
        int deleteCounter;

        Map<Integer, String> mapPrev = createMapFromSet(previous);
        Map<Integer, String> mapCur = createMapFromSet(current);

        for (User currUser : current) {
            if (!mapPrev.containsKey(currUser.getId())) {
                addCounter++;
            } else if (!Objects.equals(currUser.getName(), mapPrev.get(currUser.getId()))) {
                changCounter++;
            }
        }

        Set<Integer> prevKeyset = mapPrev.keySet();
        prevKeyset.removeAll(mapCur.keySet());
        deleteCounter = prevKeyset.size();

        return new Info(addCounter, changCounter, deleteCounter);
    }
}

