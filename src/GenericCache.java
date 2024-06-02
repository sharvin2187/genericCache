import evictionpolicy.EvictionPolicy;
import evictionpolicy.EvictionPolicyFactory;

import java.util.HashMap;
import java.util.Map;

public class GenericCache<T> {
    int capacity;
    Map<String, T> store = new HashMap<>();
    EvictionPolicy policy;

    GenericCache(int capacity, EvictionPolicy policy) {
        this.capacity = capacity;
        this.policy = policy;
    }

    public T get(String key) throws KeyNotFoundException {
        if (!store.containsKey(key)) {
            throw new KeyNotFoundException();
        }
        policy.recordKeyAcccess(key);
        return store.get(key);
    }

    public void put(String key, T value) {
        if (!store.containsKey(key) && store.size() == capacity) {
            String toEvict = policy.pollKeyToEvict();
            store.remove(toEvict);
        }
        policy.recordKeyAcccess(key);
        store.put(key, value);
    }
}
