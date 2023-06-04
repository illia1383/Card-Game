import com.sun.source.tree.BreakTree;
/**
 * @author Illia lotfalian
 * 2023-02-28
 */
public class Counter {
    private PowerSet<Card> cardps;
    private Card starter;

    public Counter(Card[] hand, Card starter) {
        this.starter = starter;
        this.cardps = new PowerSet<Card>(hand);
    }

    public int countPoints() {
        int points = 0;
        points += flush();
        //System.out.println(points);
        points += countPairs();
        //ystem.out.println(points);
        points += countRun();
        //System.out.println(points);
        points += fifteen();
        //System.out.println(points);
        points += hisKnobs();
        return points;
    }

    /**
     * CountPairs()
     * a helper function that counts pairs
     *
     * @return
     */
    private int countPairs() {
        int pair = 0;
        Set<Card> tSet = new Set<Card>(); // making a temp set

        for (int i = 0; i < cardps.getLength(); i++) { // looping through each powerset
            tSet = cardps.getSet(i); // setting temp set to each set
            if (tSet.getLength() == 2) { // first checking to see if the length is 2
                if (tSet.getElement(0).getLabel().equals(tSet.getElement(1).getLabel())) { // checks to see if its a pair
                    pair += 2;
                }
            }
        }
        return pair;


    }

    private boolean isRun(Set<Card> set) {
        // In this method, we are going through the given set to check if it constitutes a run of 3 or more
        // consecutive cards. To do this, we are going to create an array of 13 cells to represent the
        // range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
        // each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
        // An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
        // contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
        // Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.


        int n = set.getLength();

        if (n <= 2) return false; // Run must be at least 3 in length.

        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank() - 1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }

    }

    /**
     * CountRun
     *
     * @return
     */
    private int countRun() {
        int amountSeen = 0;
        int longest = 0;
        Set<Card> tset = new Set<Card>();  //making a temp set
        for (int i = 0; i < cardps.getLength(); i++) {  // going throuigh
            tset = cardps.getSet(i);
            if (isRun(tset)) { // first checking to see if its true
                if (tset.getLength() > longest) { // setting longest to the longest run
                    longest = tset.getLength();
                    amountSeen = 1;
                } else if (tset.getLength() == longest) { // and if its the same as longost amount seen is increased by one
                    amountSeen += 1;

                }

            }
        }
        return (longest * amountSeen);
    }

    /**
     * fifteen() private method that counts if the total points add to 15
     * @return
     */

    private int fifteen() {
        int points = 0;
        Set<Card> tSet = new Set<Card>();
        for (int i = 0; i < cardps.getLength(); i++) { //looping through powerset
            tSet = cardps.getSet(i);
            int total = 0;
            for (int j = 0; j < tSet.getLength(); j++) { //going through the values of of each set
                total += tSet.getElement(j).getFifteenRank();

            }
            if (total == 15) {
                points += 2;
            }
        }

        return points;
    }

    /**
     * Hisknobs()
     * @return
     */
    private int hisKnobs() {
        Set<Card> tSet = new Set<Card>(); //making a temp set
        for (int i = 0; i < cardps.getLength(); i++) {
            tSet = cardps.getSet(i);
            if (tSet.getLength() == 1) { // only lookin for sets with lenght of 1
                if (hasStarter(tSet, tSet.getLength()) == false) { // checking if it has a starter
                    if (tSet.getElement(0).getLabel().equals("J")) {
                        if (tSet.getElement(0).getSuit().equals(starter.getSuit())) {
                            return 1;
                        }
                    }
                }

            }
        }
        return 0; // because there is no points for his knocbs
    }

    /**
     *
     * @param deck
     * @param checkingCard
     * @return
     */
    private boolean iFlush(Set<Card> deck, int checkingCard) {
        if (deck.getLength() == checkingCard) { //this only looks for hands of 4 and it assumes that starter has been removed
            String suits = deck.getElement(checkingCard - 1).getSuit(); //sets deck to the last element
            for (int i = 0; i < checkingCard - 1; i++) { //loops through all expect the last vaue
                if (deck.getElement(i).getSuit().equals(suits) == false) { //returns false if the suits dont match
                    return false; //Return false
                }
            }
        } else {
            //return false if hand is wrong legnth
            return false;
        }
        return true; //If it passes everything returning true
    }

    /**
     * flush()
     * @return
     */
    private int flush() {
        int point = 0;
        Set<Card> tSet = new Set<Card>(); //making a temp set

        for (int i = 0; i < cardps.getLength(); i++) {
            tSet = cardps.getSet(i); //Looping through the temp set

            if (tSet.getLength() == 4) { //checking to see if the temp set have a length of 4
                if (hasStarter(tSet, 4) == false) { //this checks to see if it has a starter
                    if (iFlush(tSet, 4)) {
                        point = 4;
                        if (starter.getSuit().equals(tSet.getElement(0).getSuit())) {
                            point += 1;
                            return point;
                        }
                        return point;
                    }
                }
            }
        }
        return point; //There was no flush so 0 points
    }

    /**
     * //Helper method for flush and his knobs
     *
     * @param deck
     * @param checkingCard
     * @return
     */
    private boolean hasStarter(Set<Card> deck, int checkingCard) {
        if (deck.getLength() == checkingCard) {
            for (int i = 0; i < deck.getLength(); i++) {
                //checks to see if the starter is the same
                if (deck.getElement(i).getLabel().equals(starter.getLabel()) && deck.getElement(i).getSuit().equals(starter.getSuit())) {
                    //and then it returns false if any of the cards are the starter
                    return true;
                }
            }
            //also returns false if there is anything else as the starter
        } else {
            return true;
        }
        return false; //finnaly if none of the cards are the starter it returns true
    }
}
