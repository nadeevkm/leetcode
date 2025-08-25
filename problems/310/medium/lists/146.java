import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class LRUCache {

    int capacity;
    int size = 0;
    Node head = null;
    Node tail = null;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            var target = map.get(key);
            if (target != head) {
                makeHead(target);
            }
            return target.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var target = map.get(key);
            if (target != head) {
                makeHead(target);
            }
            target.val = value;
        } else {
            var newNode = new Node(key, value);
            map.put(key, newNode);
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
            if (size > capacity) {
                map.remove(tail.key);
                tail = tail.prev;
                tail.next = null;
                size--;
            }
        }
    }

    private void makeHead(Node target) {
        if (target.key == tail.key) {
            tail = tail.prev;
        }
        var prev = target.prev;
        var next = target.next;
        prev.next = next;
        if (next != null) {
            next.prev = prev;
        }
        target.prev = null;
        target.next = head;
        head.prev = target;
        head = target;
    }
}

class LRUCacheNoMap {

    int capacity;
    int size = 0;
    Node dummy = new Node();

    public LRUCacheNoMap(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        var prev = dummy;
        var curr = dummy.next;
        while (curr != null) { // true
            if (curr.key == key) {
                prev.next = curr.next;
                curr.next = dummy.next;
                dummy.next = curr;
                return curr.val;
            }
            if (curr.next == null) {
                break;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        var prev = dummy;
        var curr = dummy.next;
        while (true) {
            if (curr.key == key) {
                curr.val = value;
                prev.next = curr.next;
                curr.next = dummy.next;
                dummy.next = curr;
                return;
            }
            if (curr.next == null) {
                break;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        if (size < capacity) {
            size++;
        } else {
            prev.next = null;
        }
        var newNode = new Node(key, value);
        newNode.next = dummy.next;
        dummy.next = newNode;
    }
}

class Node {
    int key;
    int val;

    Node prev = null; // not used in LRUCacheNoMap
    Node next = null;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public Node() {
    }
}


class Check{
    public static void main(String[] args) {
        var cache = new LRUCache(2);

        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],        [1,1],[2,2],[1],[3,3], [2],  [4,4],  [1],[3],[4]]
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}


class LRUCacheLatest {

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int size;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        int size = 0;
        map = new HashMap<>();
        head = null;
        tail = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        } else {
            var node = map.get(key);
            moveToTheHead(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        Node node;
        if (map.containsKey(key)){ // update and move to the head
            node = map.get(key);
            node.value = value;
            moveToTheHead(node);
        } else { // create new, make head, remove tail if capacity is breached
            node = new Node(key, value);
            map.put(key, node);
            size++;
            if (size == 1){
                head = tail = node;
                return;
            }
            node.next = head;
            head.prev = node;
            head = node;
            if (size > capacity){
                cutTail();
            }
        }
    }

    private void moveToTheHead(Node node){
        if (node == head){
            return;
        }
        if (node == tail){
            tail = node.prev;
            tail.next = null;
        } else {
            var prev = node.prev;
            var next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    private void cutTail(){
        map.remove(tail.key);
        var prev = tail.prev;
        prev.next = null;
        tail = prev;
        size = capacity;
    }

    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}


/// !!! - in order to deal with empty list or cases of node is head, node is tail
// ==> it is better to initially create dummy nodes for tail and head and implement only two methods (addNode and removeNode)
// code would be much more concise and easy to implement