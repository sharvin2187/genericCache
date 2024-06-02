package LList;

public class LList {
    LLNode root = new LLNode("root", null, null);
    LLNode curr = root;

    // Adds a val to the end;
    public LLNode add(String val) {
        curr.next = new LLNode(val, curr, null);
        curr = curr.next;
        return curr;
    }

    public void remove(LLNode todel) {
        assert todel != root;
        LLNode before = todel.prev;
        LLNode after = todel.next;
        before.next = after;
        if (after != null) {
            after.prev = before;
        }
        if (curr == todel) {
            curr = before;
        }
        todel.prev = null;
        todel.next = null;
    }

    public LLNode pollFirst() {
        assert curr != root;
        LLNode temp = root.next;
        remove(temp);
        return temp;
    }

    public void debugLL() {
        LLNode itr = root;
        while (itr != null) {
            System.out.print(itr.val + " => ");
            itr = itr.next;
        }
        System.out.println("");
    }
}
