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
    
    /**
     * Gets the average of the last 10 values it's been told to modify.
     */
    public static NumberModifier AVERAGE = new NumberModifier() {
        double[] last = new double[10];
        public double modify(double number) {
            double total = 0;
            
            // Push the numbers forward by one, and add them to the total.
            for (int i = 1; i < last.length; i++) {
                double val = last[i - 1];
                
                total += val;
                last[i] = val;
            }
            
            // Add the lastest value.
            total += number;
            last[0] = number;
            
            // Return the average.
            return total / last.length;
        }
    };
}
