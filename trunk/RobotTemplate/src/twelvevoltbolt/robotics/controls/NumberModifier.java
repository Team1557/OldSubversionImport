package twelvevoltbolt.robotics.controls;

/**
 * An abstract class which, when implemented, allows a number to be passed through it's modify function.
 */
public abstract class NumberModifier {
    /**
     * This method return the number modified from the input number.
     * @param number
     * The number to modify.
     * @return 
     * The modified number.
     */
    public abstract double modify(double number);
    
    public static NumberModifier NONE = new NumberModifier() {
        public double modify(double number) {
            return number;
        }
    };
    
    public static NumberModifier SQUARED = new NumberModifier() {
        public double modify(double number) {
            return number * number * (number < 0 ? -1 : 1);
        }
    };
}
