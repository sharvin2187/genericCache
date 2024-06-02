package evictionpolicy;

public interface EvictionPolicy {
    public void recordKeyAcccess(String key);

    public String pollKeyToEvict();
}
