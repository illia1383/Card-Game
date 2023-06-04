/**
 * author @Illia lotfalian
 * 2023-02-27
 */
/**
 * intializing my variables
 * @param <T>
 */
public class Set<T> {
    private LinearNode<T> setStart;

    public Set() {
        setStart = new LinearNode<T>();

    }

    /**
     * public void add(T element)
     * @param element
     */
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        if (setStart == null) {
            setStart = newNode;
        } else {
            LinearNode<T> current = setStart;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    /**
     * public int getlength
     * this function just returns the lenght of the set
     * @return
     */
    public int getLength() {
        int count = 0;
        LinearNode<T> current = setStart;
        while (current.getNext() != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }


    /**
     * Returns the element at the specified index in the linked list.
     *
     * @param i the index of the element to retrieve
     * @return the element at the specified index
     */
    public T getElement(int i){
        // Create a new LinearNode object called current and initialize it to the value of setStart.
        LinearNode<T> current = setStart;

        // Increment i by 1.
        i += 1;

        // Iterate through the linked list i times.
        for (int j = 0; j < i; j++) {
            // Set current to the next node in the linked list.
            current = current.getNext();
        }

        // Return the element stored in the node that current points to.
        return current.getElement();
    }

    /**
     *
     * @param element
     * @return
     */
    /**
     * Checks whether the linked list contains the specified element.
     *
     * @param element the element to check for
     * @return true if the linked list contains the element, false otherwise
     */
    public boolean contains(T element) {
        // Create a new LinearNode object called current and initialize it to the value of setStart.
        LinearNode<T> current = setStart;

        // Loop through the linked list until we reach the end of the list.
        while (current != null) {
            // Get the element stored in the current node.
            T currentElement = current.getElement();

            // Check if the current element matches the element we're looking for.
            if (element == null && currentElement == null) {
                // If the element we're looking for is null and the current element is null, we've found a match.
                return true;
            } else if (currentElement != null && currentElement.equals(element)) {
                // If the current element is not null and equals the element we're looking for, we've found a match.
                return true;
            }

            // Move on to the next node in the linked list.
            current = current.getNext();
        }

        // If we've reached the end of the linked list without finding a match, return false.
        return false;
    }


    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    public String toString() {
        // Create a new LinearNode object called current and initialize it to the value of setStart.
        LinearNode<T> current = setStart;

        // Create an empty string to hold the string representation of the linked list.
        String s = "";

        // Loop through the linked list until we reach the end of the list.
        while (current.getNext() != null){
            // Move on to the next node in the linked list.
            current = current.getNext();

            // Get the element stored in the current node and append it to the string with a space.
            s += current.getElement();
            s += " ";
        }

        // Return the string representation of the linked list.
        return s;
    }



}
