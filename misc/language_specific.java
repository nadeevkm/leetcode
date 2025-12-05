import java.util.*;

class Scratch {
    public static void main(String[] args) {
        int i = 1;


        // Arrays
        int[] nums = new int[]{1, 2, 3}; // = new int[3];
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

        int left = 2, right = 4;
        var arrCopy = Arrays.copyOfRange(nums, left, right + 1);
        int[] newNums = new int[nums.length];
        System.arraycopy(nums, 0, newNums, 0, nums.length);

        Arrays.sort(nums2, (n1, n2) -> n2[0] - n1[0]); // decreasingly, n1[0] - n2[0] is a standart increasing order !!! if negative values presents, n2[0] - n1[0] can overflow in case of big numbers (1500000000 - (-1500000000)
        /*better*/
        Arrays.sort(nums2, (n1, n2) -> Integer.compare(n2[0], n1[0]));
        // !!! primitive arrays cannot be sorted by custom comparator, need to cast it to objects before that

        List<Integer> res = new ArrayList<>();
        int[] ans = new int[res.size()];
        Arrays.setAll(ans, res::get);

        Arrays.fill(nums, -1); // use only with primitives filling
        Arrays.setAll(nums2, (index) -> new int[]{-1, -1});  // use instead fill!!, cause fill inserts same reference to all cells!!

        // Strings
        String.format("%03d,", 5);  // = "005,"

        // String Builders
        var sb = new StringBuilder();
        sb.append('a');
        sb.append("b3f");
        sb.setCharAt(0, 'C');
        sb.deleteCharAt(sb.length() - 1);

        // Chars
        Character ch = 'A';
        Character.isAlphabetic(';'); // false
        Character.isDigit('a'); // false
        Character.toLowerCase(ch); // => a

        // Math
        int ceil = (int) Math.ceil((double) (i - nums[0]) / nums[1]);
        int mod = Math.floorMod(-2, 4); // = 2, in comparison -2 % 4 = -2

        //Lists
        var list123 = Arrays.asList(1,2,3);
        Collections.sort(list123);
        Collections.sort(list123, (a, b) -> b.compareTo(a));

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
                "ghi");


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
        Integer int1 = Integer.valueOf("0123");
        int length = (int)(Math.log10(n)+1); // длина числа (кол-во цифр)
        int firstDigit = (int)(n/(Math.pow(10,length - 1))); // первая цифра

        // record - special objects, like data classes in Kotlin, with auto generated equals + hashcode
        record Point (int x, int y){};
        var point = new Point(1,5);
        record Pair(int first, int second){};
        var pair = new Pair(1, 10);

        // Random
        Random rand = new Random();
        var value1 = rand.nextInt(100); // from 0 to 100 exclusive
        var value2 = rand.nextInt(21, 50); // lower bound specification starting from java17
        var value3 = ThreadLocalRandom.current().nextInt(min, max + 1); //from min to max inclusive

    }
}
