import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author: orc-dev
 * @update: Jan.15 2024
 * 
 * @leetcode: 380. Insert Delete GetRandom O(1)
 * @tag: hash table, design
 * 
 * Log:
 *   - Combine list (index - value) and hashmap (value - index)
 *   - Swap last element with the target element, then remove
 */
class RandomizedSet {

    private ArrayList<Integer> data;
    private HashMap<Integer, Integer> valueIndexMap;
    private Random rand;

    /** Constructor */
    public RandomizedSet() {
        this.data = new ArrayList<>();
        this.valueIndexMap = new HashMap<>();
        this.rand = new Random();
    }
    
    /**
     * Append the value to the list, update the hashtable with entry
     * <val, index>.
     * 
     * @param val Value to insert.
     * @return True if the set does not contain that value, otherwise false.
     */
    public boolean insert(int val) {
        if (this.valueIndexMap.containsKey(val)) {
            return false;
        }
        this.valueIndexMap.put(val, this.data.size());
        this.data.add(val);
        return true;
    }
    
    /**
     * Use hashtable to search the index of given value. Swap the last 
     * element 'B' with that element 'R'. Then remove 'R' from the 
     * end of the list. Meanwhile, remove key 'R' from the hash table 
     * and update the entry <B, index>.
     * 
     * @param val value to be removed
     * @return True, if the set contains that value, otherwise, false.
     */
    public boolean remove(int val) {
        if (!this.valueIndexMap.containsKey(val)) {
            return false;
        }
        final int insIdx = this.valueIndexMap.get(val);
        final int lastIdx = this.data.size() - 1;
        final int lastVal = this.data.get(lastIdx);

        this.valueIndexMap.put(lastVal, insIdx);
        this.valueIndexMap.remove(val);
        this.data.set(insIdx, lastVal);
        this.data.remove(lastIdx);
        return true;
    }
    
    /**
     * Use Random generator to select an index from the list.
     * 
     * @return a randome element in the list.
     */
    public int getRandom() {
        final int index = this.rand.nextInt(this.data.size());
        return this.data.get(index);
    }
}
