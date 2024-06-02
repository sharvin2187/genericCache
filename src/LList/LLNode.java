package LList;

public class LLNode {
    public String val;
    public LLNode prev, next;

    public LLNode(String val, LLNode prev, LLNode next) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
