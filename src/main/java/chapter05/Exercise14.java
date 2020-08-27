package chapter05;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Get a matrix of the distance between all vertices in a binary tree
 */
public class Exercise14 {

    /**
     * O(n²) algorithm
     */
    public static Integer[][] calculateDistances(Integer[][] binaryTree) {
        Distances distances = new Distances(binaryTree.length);

        Iterator iterator = new Iterator(binaryTree);
        while (iterator.hasNext()) {
            Vertex vertex = iterator.next();

            Integer[] distancesFromCurrentNode = distances.from(vertex.currentNode);
            distances.registerAsOneFarWay(vertex.linkedToNode, distancesFromCurrentNode);
            distances.register(vertex.currentNode, vertex.linkedToNode, 1);
        }

        return distances.get();
    }

    public static void main(String[] args) {

        //binaryTree adjacent list
        Integer[][] binaryTree = {
                {1, 2,}, {3, 4}, {5, 6}, {}, {}, {7}, {}, {8, 9}, {}, {}
        };

        /*
         *Graphical representation of the tree
         *                  0
         *                /  \
         *              /     \
         *             1       2
         *           /  \     / \
         *         3     4   5   6
         *                  /
         *                 7
         *                / \
         *               8  9
         *
         */

        Integer[][] distances = calculateDistances(binaryTree);
        print(distances);
    }

    private static void print(Integer[][] integers) {
        Arrays.stream(integers).forEach(Exercise14::printLine);
    }

    private static void printLine(Integer[] integers) {
        String numbers = Arrays.stream(integers)
                               .map(String::valueOf)
                               .collect(Collectors.joining(", "));

        System.out.println("[" + numbers + "]");
    }

    private static class Iterator {
        int currentNode = 0;

        int currentVertex = 0;

        private final Integer[][] binaryTree;

        public Iterator(Integer[][] binaryTree) {
            this.binaryTree = binaryTree;
        }

        public boolean hasNext() {
            return (currentNode < binaryTree.length) &&
                   (currentVertex < binaryTree[currentNode].length);
        }

        public Vertex next() {
            if (!hasNext())
                return null;

            Vertex value = new Vertex(currentNode, binaryTree[currentNode][currentVertex]);
            moveToNextVertex();

            return value;
        }

        private void moveToNextVertex() {
            currentVertex++;
            while (!hasNext() && currentNode < binaryTree.length) {
                currentVertex = 0;
                currentNode++;
            }
        }
    }

    private static class Vertex {
        public int currentNode;

        public int linkedToNode;

        public Vertex(int currentNode, int linkedToNode) {
            this.currentNode = currentNode;
            this.linkedToNode = linkedToNode;
        }
    }

    private static class Distances {
        private final Integer[][] distances;

        public Distances(int size) {
            distances = new Integer[size][size];
        }

        public void register(int itemA, int itemB, int distance) {
            distances[itemA][itemB] = distance;
            distances[itemB][itemA] = distance;
        }

        public Integer[] from(int item) {
            return distances[item];
        }

        public Integer[][] get() {
            return distances;
        }

        public void registerAsOneFarWay(int currentItem, Integer[] distancesFrom) {
            for (int item = 0; item < distancesFrom.length; item++) {
                if (distancesFrom[item] != null) {
                    int distanceOneFarWary = distancesFrom[item] + 1;
                    register(currentItem, item, distanceOneFarWary);
                }
            }
        }
    }
}
