package evictionpolicy;

import LList.LLNode;
import LList.LList;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    Map<String, LLNode> keyToSequence = new HashMap<>();
    LList sequence = new LList();

    @Override
    public void recordKeyAcccess(String key) {
        if (keyToSequence.containsKey(key)) {
            LLNode toRemove = keyToSequence.get(key);
            keyToSequence.remove(key);
            sequence.remove(toRemove);
        }
        keyToSequence.put(key, sequence.add(key));
    }

    @Override
    public String pollKeyToEvict() {
        return sequence.pollFirst().val;
    }
}
