package RecursiveProduct;
	/*
	 * Program:  RecursiveProduct.java
	 * Course:   CSC372 - Module 5 Critical Thinking
	 * Author:   Ayden
	 * Purpose:  Lets the user enter five numbers, then uses recursive methods
	 *           to collect the numbers and to calculate the product of all five.
	 */
	import java.util.Scanner;

	public class RecursiveProduct {

	    private static final int COUNT = 5;

	    public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);
	        double[] numbers = new double[COUNT];

	        System.out.println("=== Recursive Product Calculator ===");
	        System.out.println("Enter " + COUNT + " numbers.");

	        readNumbers(input, numbers, 0);

	        double product = product(numbers, numbers.length - 1);

	        System.out.println();
	        System.out.println("Numbers entered: " + formatList(numbers, 0));
	        System.out.println("Product of all " + COUNT + " numbers: " + format(product));

	        input.close();
	    }

	    /**
	     * Recursively reads one number into numbers[index], then calls itself
	     * for the next index. Base case: index reaches the end of the array.
	     */
	    public static void readNumbers(Scanner input, double[] numbers, int index) {
	        if (index == numbers.length) {
	            return;   // base case: all numbers have been entered
	        }

	        System.out.print("Enter number " + (index + 1) + ": ");
	        if (input.hasNextDouble()) {
	            numbers[index] = input.nextDouble();
	            readNumbers(input, numbers, index + 1);   // move to the next number
	        } else {
	            System.out.println("  Invalid entry. Please enter a number.");
	            input.next();                              // discard the bad input
	            readNumbers(input, numbers, index);        // ask again for the same number
	        }
	    }

	    /**
	     * Recursively returns the product of numbers[0] through numbers[index].
	     * Base case: index 0 returns the first number.
	     * Recursive case: numbers[index] times the product of the numbers before it.
	     */
	    public static double product(double[] numbers, int index) {
	        if (index == 0) {
	            return numbers[0];
	        }
	        return numbers[index] * product(numbers, index - 1);
	    }

	    /** Recursively builds a comma-separated list of the numbers. */
	    private static String formatList(double[] numbers, int index) {
	        if (index == numbers.length - 1) {
	            return format(numbers[index]);
	        }
	        return format(numbers[index]) + ", " + formatList(numbers, index + 1);
	    }

	    /** Shows whole numbers without a decimal point, otherwise up to 4 decimals. */
	    private static String format(double value) {
	        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
	            return String.format("%,d", (long) value);
	        }
	        return String.format("%,.4f", value).replaceAll("0+$", "");
	    }
	}

