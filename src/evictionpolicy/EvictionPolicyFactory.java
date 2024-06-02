package evictionpolicy;

public class EvictionPolicyFactory {
    public static EvictionPolicy getLRU() {
        return new LRUEvictionPolicy();
    }

    public static EvictionPolicy getLFU() {
        return new LFUEvictionPolicy();
    }
}
