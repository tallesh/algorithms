package chapter05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Find a subset that the product of the numbers in it is maximum over all consecutive
 * subsequences.
 */
public class Exercise12 {

    public static Subset calculate(List<Double> sequence) {

        Subset maxSubset = new Subset();
        Subset suffixMax = new Subset();
        Subset suffixMin = new Subset();

        for (int i = 0, sequenceSize = sequence.size(); i < sequenceSize; i++) {
            Double item = sequence.get(i);

            suffixMax.insert(item, i);
            suffixMin.insert(item, i);

            OrderedSubSet suffixes = new OrderedSubSet(new Subset(),
                                                       new Subset(item, i),
                                                       suffixMax,
                                                       suffixMin);

            suffixMax = suffixes.getMax();
            suffixMin = suffixes.getMin();

            if (suffixMax.multiply() >= maxSubset.multiply()) {
                maxSubset = suffixMax.duplicate();
            }
        }

        return maxSubset;
    }

    public static void main(String[] args) {
        System.out.println(calculate(Arrays.asList(-0.1,
                                                   0.5,
                                                   1.0,
                                                   -5.0,
                                                   2.0,
                                                   0.5,
                                                   -0.5,
                                                   8.0,
                                                   -3.0,
                                                   -2.0)));
        System.out.println("Solution " + (1 * -5 * 2 * 0.5 * -0.5 * 8 * -3 * -2));

        System.out.println(calculate(Arrays.asList(-0.1,
                                                   0.5,
                                                   1.0,
                                                   -5.0,
                                                   2.0,
                                                   0.5,
                                                   -5.0e-10,
                                                   8.0,
                                                   -3.0,
                                                   -2.0)));
        System.out.println("Solution " + (8 * -3 * -2));
    }

    private static class Subset {

        private static final double EmptySubsetProduct = 1.0;

        private int initialIndex = -1;

        private int finalIndex = -1;

        private Double multiplication = EmptySubsetProduct;

        public Subset() {
        }

        public Subset(Double item, int originalIndex) {
            insert(item, originalIndex);
        }

        public void insert(Double item, int originalIndex) {
            if (initialIndex < 0) {
                initialIndex = originalIndex;
            }
            finalIndex = originalIndex;

            multiplication *= item;
        }

        public Double multiply() {
            return multiplication;
        }

        @Override
        public String toString() {
            return "Subset{" + "initialIndex=" + initialIndex + ", finalIndex=" + finalIndex +
                   ", multiplication=" + multiplication + '}';
        }

        public Subset duplicate() {
            Subset clone = new Subset();
            clone.initialIndex = this.initialIndex;
            clone.finalIndex = this.finalIndex;
            clone.multiplication = this.multiplication;

            return clone;
        }
    }

    private static class OrderedSubSet {

        private final List<Subset> items;

        public OrderedSubSet(Subset... items) {
            this.items = Arrays.asList(items);
            this.items.sort(Comparator.comparingDouble(Subset::multiply));
        }

        public Subset getMin() {
            return this.items.get(0);
        }

        public Subset getMax() {
            return this.items.get(this.items.size() - 1);
        }
    }
}
