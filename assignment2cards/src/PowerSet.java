/**
 * @author Illia lotfalian
 * @param <T>
 */

public class PowerSet<T> {
    private Set<T>[] set;

    /**
     * making the binary power set
     * @param elements
     */
    public PowerSet(T[] elements) {
        int n = elements.length;
        int powerSetSize = (int) Math.pow(2, n);
        this.set = (Set<T>[]) new Set[powerSetSize];

        // Generate the power set
        for (int i = 0; i < powerSetSize; i++) {
            // Convert the binary representation of i to a string with n digits
            String binary = Integer.toBinaryString(i);
            while (binary.length() < n) {
                binary = "0" + binary;
            }

            // Create a new set for this subset
            this.set[i] = new Set<T>();

            // Add the elements from the original set corresponding to the bits that are 1
            for (int j = 0; j < n; j++) {
                if (binary.charAt(j) == '1') {
                    this.set[i].add(elements[j]);
                }
            }
        }
    }

    public int getLength() {
        return set.length;
    }

    public Set<T> getSet(int i) {
        return set[i];
    }


    public static void main(String[] args) {

        /* ---------- Test 1 [Set getLength and contains] ---------- */


        Set<String> sSet = new Set<String>();
        sSet.add("spring");
        sSet.add("summer");
        sSet.add("autumn");
        sSet.add("winter");
        System.out.println(sSet);
        System.out.println(sSet.getLength());
    }

}
