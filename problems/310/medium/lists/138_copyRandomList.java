import java.util.ArrayList;
import java.util.HashMap;

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}


class Solution {
    public Node copyRandomList(Node head){  // with no map, 3 iterations, at first link new list to original one
        Node copyHead = null;
        Node p1 = head;

        while ( p1 != null){
            Node copy = new Node(p1.val);
            if (copyHead == null){
                copyHead = copy;
            }
            copy.next = p1.next;
            p1.next = copy;
            p1 = copy.next;
        }

        p1 = head;
        while ( p1 != null){
            Node ran = p1.random;
            Node copy = p1.next;
            copy.random = (p1.random == null) ? null : p1.random.next;

            p1 = p1.next.next;
        }

        p1 = head;
        Node p2 = copyHead;
        while ( p1 != null){
            p2.next = (p2.next == null) ? null : p2.next.next;
            p2 = p2.next;
            p1.next = (p1.next == null) ? null : p1.next.next;
            p1 = p1.next;
        }

        return copyHead;
    }

    public Node copyRandomList2(Node head){  // with one map, 2 iterations
        var nodeToNewNode = new HashMap<Node, Node>();

        Node copyHead = null;
        Node p1 = head;
        while ( p1 != null){
            Node newNode = new Node(p1.val);
            if (copyHead == null){
                copyHead = newNode;
            }
            nodeToNewNode.put(p1, newNode);

            p1 = p1.next;
        }

        p1 = head;
        while ( p1 != null){
            Node copy = nodeToNewNode.get(p1);
            copy.next = (p1.next == null) ? null : nodeToNewNode.get(p1.next);
            copy.random = (p1.random == null) ? null : nodeToNewNode.get(p1.random);
            p1 = p1.next;
        }

        return copyHead;
    }

    public Node copyRandomList1(Node head){  // with map and array, based on index, 2 iterations

        var nodeToIndex = new HashMap<Node, Integer>();
        var indexToNode = new ArrayList<Node>();

        Node copyHead = null;
        Node p1 = head;
        Node p2 = null;
        int index = 0;
        while ( p1 != null){
            nodeToIndex.put(p1, index);
            Node newNode = new Node(p1.val);
            if (p2 == null){
                p2 = newNode;
                copyHead = newNode;
            } else {
                p2.next = newNode;
                p2 = p2.next;
            }
            indexToNode.add(newNode);

            index++;
            p1 = p1.next;
        }

        p1 = head;
        p2 = copyHead;
        while ( p1 != null){

            Node pRanCopy = (p1.random == null) ? null : indexToNode.get(nodeToIndex.get(p1.random));
            p2.random = pRanCopy;

            p1 = p1.next;
            p2 = p2.next;
        }
        return copyHead;
    }
}

class SolutionLatest {
    public Node copyRandomList(Node head) {
        // 1o -> 2o -> 2o
        // 1st iteration
        // 1o -> 1c -> 2o -> 2c -> 2o ->2c
        // 2nd iteration - link random pointers
        // 1c.random = 1o.random.next (check null)
        // 3rd iteration - restore original links in origin list and link copy to its own next lists
        // return cope;

        Node origin = head;
        while (origin != null){
            var originNext = origin.next;
            origin.next = new Node(origin.val);
            origin.next.next = originNext;
            origin = originNext;
        }

        origin = head;
        Node copyHead = head.next;
        while (origin != null){
            Node copy = origin.next;
            Node originNext = copy.next;
            copy.random = (origin.random == null) ? null : origin.random.next;
            origin = originNext;
        }

        origin = head;
        while (origin != null){
            Node copy = origin.next;
            Node originNext = origin.next.next;
            copy.next = (copy.next == null) ? null : copy.next.next;
            origin.next = originNext;
            origin = origin.next;
        }

        return copyHead;
    }
}