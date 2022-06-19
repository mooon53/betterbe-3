package utils;

import java.util.ArrayList;
import java.util.List;

public class SQLUtils {
    public static String intArrayToSqlArray(Long[] originalList) {
        ArrayList<Long> longs = new ArrayList<>(List.of(originalList));
        StringBuilder result = new StringBuilder("{");
        int i =0;
        while (i < longs.size() - 1) {
            result.append(longs.get(i)).append(", ");
            i++;
        }
        result.append(longs.get(i));
        result.append("}");
        return result.toString();
    }
}
