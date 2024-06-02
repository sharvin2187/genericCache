package evictionpolicy;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Integer;
import java.util.TreeSet;

public class LFUEvictionPolicy implements EvictionPolicy {

    private static class Pair implements Comparable<Pair> {
        String key;
        Integer count;

        Pair(String key, Integer count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (count.compareTo(o.count) == 0) {
                return key.compareTo(o.key);
            }
            return count.compareTo(o.count);
        }
    }

    Map<String, Integer> keyToCount = new TreeMap<>();
    TreeSet<Pair> keyAndCount = new TreeSet<>();

    @Override
    public void recordKeyAcccess(String key) {
        Integer count = 0;
        if (keyToCount.containsKey(key)) {
            count = keyToCount.get(key);

            keyToCount.remove(key);
            keyAndCount.remove(new Pair(key, count));
        }

        keyToCount.put(key, count + 1);
        keyAndCount.add(new Pair(key, count + 1));
    }

    @Override
    public String pollKeyToEvict() {
        Pair toEvict = keyAndCount.first();
        keyToCount.remove(toEvict.key);
        keyAndCount.remove(toEvict);
        return toEvict.key;
    }
}
