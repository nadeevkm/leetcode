import java.util.*;

class Scratch {
    public static void main(String[] args) {
        int i = 1;


        // Arrays
        int[] nums = new int[]{};
        int[][] nums2 = new int[][]{};
        int left = 0, right = 1;

        var arrCopy = Arrays.copyOfRange(nums, left, right + 1);

        Arrays.sort(nums2, (n1, n2) -> n2[0] - n1[0]); // decreasingly, n1[0] - n2[0] is a standart increasing order !!! if negative values presents, n2[0] - n1[0] can overflow in case of big numbers (1500000000 - (-1500000000)
        /*better*/ Arrays.sort(nums2, (n1, n2) -> Integer.compare(n2[0], n1[0]));
        // !!! primitive arrays cannot be sorted by custom comparator, need to cast it to objects before that

        List<Integer> res = new ArrayList<>();
        int[] ans = new int[res.size()];
        Arrays.setAll(ans, res::get);

        Arrays.fill(nums, -1);
        Arrays.setAll(nums2, (index) -> new int[]{-1,-1});  // use instead fill!!, cause fill inserts same reference to all cells!!

        // Strings
        String.format("%03d,", 5);  // = "005,"

        // Chars
        Character.isAlphabetic(';'); // false
        Character.isDigit('a'); // false
        Character.toLowerCase('A'); // => a

        // Math
        int ceil = (int) Math.ceil((double) (i - nums[0]) / nums[1]);

        //Lists
        var list123 = Arrays.asList(1,2,3);

        // Maps
        int key = 1;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, List<Integer>> map2 = new HashMap<>();

        int value = map.getOrDefault(i, 0);
        var values = map2.computeIfAbsent(key, k -> new ArrayList<Integer>());
        map2.computeIfAbsent(key, k -> new ArrayList<Integer>()).add(5); // can modify this list right away

        Map<Character, String> letters = Map.of( // creation of Map
                '2',
                "abc",
                '3',
                "def",
                '4',
                "ghi",
                '5',
                "jkl",
                '6',
                "mno",
                '7',
                "pqrs",
                '8',
                "tuv",
                '9',
                "wxyz");



        // Queue, Deque, Stack
        Deque<Integer> q = new LinkedList<>();
        while(!q.isEmpty() && q.peekLast() < nums[i]){
            q.removeLast();
        }
        q.add(nums[i]);
        q.peek();



        // Priority Queue
        Queue<Integer> pqMax = new PriorityQueue<>((n1, n2) -> n2 - n1); // max heap
        Queue<Integer> pqMin = new PriorityQueue<>((n1, n2) -> n1 - n2); // min heap
        var pq = new PriorityQueue<int[]>((n1, n2) -> n1[2] - n2[2]);


        // Numbers
        int length = (int)(Math.log10(n)+1); // длина числа (кол-во цифр)
        int firstDigit = (int)(n/(Math.pow(10,length - 1))); // первая цифра
        
    }
}