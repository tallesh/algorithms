package chapter05;

import java.util.Arrays;
import java.util.List;

/**
 * Evaluate a polynomial function using divide and conquer algorithm
 */
public class Exercise01 {

    public static int evaluatePolynomial(List<Integer> coefficients, int x) {
        return new Polynomial(coefficients).evaluatePolynomial(x);
    }

    private static class Polynomial {

        private List<Integer> coefficients;

        private int[] degrees;

        private int currentX = 0;

        public Polynomial(List<Integer> coefficients) {
            this.coefficients = coefficients;
            degrees = new int[coefficients.size()];
        }

        public int evaluatePolynomial(int xValue) {
            currentX = xValue;
            degrees = new int[coefficients.size() / 2 + 1];
            return evaluatePolynomial(this.coefficients);
        }

        private int evaluatePolynomial(List<Integer> coefficients) {
            if (coefficients.size() == 1) {
                return coefficients.get(0);
            }

            int degreeSplit = (int) Math.ceil(coefficients.size() / 2.0);
            return getExponential(degreeSplit) *
                   evaluatePolynomial(coefficients.subList(degreeSplit, coefficients.size())) +
                   evaluatePolynomial(coefficients.subList(0, degreeSplit));
        }

        private int getExponential(int degree) {
            if (degree == 0) {
                return 1;
            }

            if (degrees[degree] == 0) {
                degrees[degree] = currentX * getExponential(degree - 1);
            }

            return degrees[degree];
        }
    }

    public static void main(String[] args) {
        System.out.print(evaluatePolynomial(Arrays.asList(10, 2, 1, 2), 2));
        System.out.println(" Expected: 34");
        System.out.print(evaluatePolynomial(Arrays.asList(4, 1, 1, 2), 3));
        System.out.println(" Expected: 70");
    }
}