class RandomizedSet1 {

    List<Integer> array;
    Map<Integer, Integer> map;
    Random random;

    public RandomizedSet1() {
        array = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        } else {
            array.add(val);
            map.put(val, array.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        } else {
            var toRemovePos = map.get(val);
            var toChange = array.get(array.size()-1);
            array.set(toRemovePos, toChange);
            map.put(toChange, toRemovePos);
            array.remove(array.size()-1);
            map.remove(val);
            return true;
        }
    }

    public int getRandom() {
        return array.get(random.nextInt(array.size()));
    }
}


class RandomizedSet { // optimized with array

    int[] array;
    int size;
    Map<Integer, Integer> map;
    Random random;

    public RandomizedSet() {
        array = new int[10];
        size = 0;
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        } else {
            if (size == array.length){
                int[] tmp = new int[array.length * 2];
                System.arraycopy(array, 0, tmp, 0, array.length);
                array = tmp;
            }
            array[size] = val;
            map.put(val, size);
            size++;
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        } else {
            var toRemovePos = map.get(val);
            var toChange = array[size - 1];
            array[toRemovePos] = toChange;
            map.put(toChange, toRemovePos);
            size--;
            map.remove(val);
            return true;
        }
    }

    public int getRandom() {
        return array[random.nextInt(size)];
    }
}