package chapter05;

/**
 * Get the diameter of a tree
 */
public class Exercise15 {

    /**
     * O(n) algorithm
     */
    public static Response calculateDiameter(Integer[][] tree, Integer root) {
        if(getLeftChild(tree[root]) == null && getRightChild(tree[root]) == null){
            return Response.empty();
        }


        int distanceFromLeft = 0;
        int diameterFromLeft = 0;
        if (getLeftChild(tree[root]) != null) {
            Response child = calculateDiameter(tree, getLeftChild(tree[root]));
            distanceFromLeft = child.distanceFromRoot + 1;
            diameterFromLeft = child.diameter;
        }

        int distanceFromRight = 0;
        int diameterFromRight = 0;
        if (getRightChild(tree[root]) != null) {
            Response child = calculateDiameter(tree, getRightChild(tree[root]));
            distanceFromRight = child.distanceFromRoot + 1;
            diameterFromRight = child.diameter;
        }


        int distanceFromRoot = Math.max(distanceFromLeft, distanceFromRight);

        int maxDiameterWithRoot = distanceFromLeft + distanceFromRight;
        int maxInnerDiameter = Math.max(diameterFromLeft, diameterFromRight);
        int diameter = Math.max(maxDiameterWithRoot, maxInnerDiameter);

        Response response = new Response(diameter, distanceFromRoot);
        System.out.println("root: " + root + " " + response);
        return response;
    }

    private static Integer getRightChild(Integer[] child) {
        return child[0];
    }

    private static Integer getLeftChild(Integer[] child) {
        return child[1];
    }

    public static void main(String[] args) {

        //binaryTree adjacent list
        Integer[][] binaryTree = {
                {1, 2,}, {3, 4}, {5, 6}, {null, null}, {null, null}, {7, null}, {null, null},
                {8, 9}, {null, null}, {null, null}
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

        Response response = calculateDiameter(binaryTree, 0);
        System.out.println(response);
    }

    private static class Response {
        public int diameter;

        public int distanceFromRoot;

        public Response(int diameter, int distanceFromRoot) {
            this.diameter = diameter;
            this.distanceFromRoot = distanceFromRoot;
        }

        public static Response empty() {
            return new Response(0, 0);
        }

        @Override
        public String toString() {
            return "Response{" + "diameter=" + diameter + ", distanceFromRoot=" +
                   distanceFromRoot + '}';
        }
    }
}
