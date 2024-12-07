import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class NewInteger implements Comparable{
    private int value;
    private int strength;
    private HashSet<Integer> beforeThis;

    public NewInteger(int value, int strength ) {
        this.value = value;
        this.strength = strength;
        this.beforeThis = Part2.beforeThis.get(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewInteger that = (NewInteger) o;
        return value == that.value && strength == that.strength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, strength);
    }


    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 0;
        NewInteger other = (NewInteger) o;
//        return Integer.compare(other.strength, this.strength);
        if(beforeThis.contains(other.value)) {
            return 1;
        } else if (other.value == this.value) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
