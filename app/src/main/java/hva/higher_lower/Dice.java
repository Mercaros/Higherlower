package hva.higher_lower;

/**
 * Created by khaled on 18-09-18.
 */

public class Dice {
    private int diceFaceNumber;

    public Dice(int diceFaceNumber) {
        this.diceFaceNumber = diceFaceNumber;
    }

    public int getDiceFaceNumber() {
        return diceFaceNumber;
    }

    public void setDiceFaceNumber(int diceFaceNumber) {
        this.diceFaceNumber = diceFaceNumber;
    }

    @Override
    public String toString() {
        return "Throw is " + (diceFaceNumber + 1);
    }
}
