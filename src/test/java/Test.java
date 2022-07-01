import dao.Dao;

import static utils.SQLUtils.longArrayToSqlArray;

public class Test {
    public static void main(String[] args) {
        /*Long[] originalList = new Long[]{0L, 1L, 4L};
        ArrayList<Long> longs = new ArrayList<>(List.of(originalList));
        StringBuilder result = new StringBuilder("{");
        int i = 0;
        while (i < longs.size() - 1) {
            result.append(longs.get(i)).append(", ");
            i++;
        }
        result.append(longs.get(i));
        result.append("}");
        System.out.println(result.toString());*/
        Long[] ruleId = new Long[]{5L, 6L, 7L};
        System.out.println(longArrayToSqlArray(ruleId));
        Dao.removeRule(ruleId);
    }
}
