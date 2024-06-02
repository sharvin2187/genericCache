import evictionpolicy.EvictionPolicyFactory;

public class Main {
    static GenericCache cache;

    public static void main(String[] args) {
        cache = new GenericCache<Long>(3, EvictionPolicyFactory.getLFU());

        attemptGet("k1");
        recordAndSet("k1", 32L);
        attemptGet("k1");
        recordAndSet("k2", 33L);
        recordAndSet("k2", 33L);
        recordAndSet("k3", 34L);
        recordAndSet("k1", 35L);
        recordAndSet("k4", 33L);
        recordAndSet("k5", 35L);
        attemptGet("k4");
        attemptGet("k1");
        attemptGet("k2");
    }

    // prints key and value if key is found, otherwise prints "not found"
    public static void attemptGet(String key) {
        try {
            Long val = (Long) cache.get(key);
            System.out.println(key + " : " + val);
        } catch (KeyNotFoundException k) {
            System.out.println("not found: " + key);
        }
    }

    public static void recordAndSet(String key, Long val) {
        System.out.println("setting: " + key + " : " + val);
        cache.put(key, val);
    }
}