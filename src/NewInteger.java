import java.util.Objects;

public class NewInteger implements Comparable{
    private int value;
    private int strength;

    public NewInteger(int value, int strength) {
        this.value = value;
        this.strength = strength;
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
        return Integer.compare(other.strength, this.strength);
    }

    @Override
    public String toString() {
        return value + "";
    }
}
