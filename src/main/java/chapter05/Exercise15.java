package chapter05;

/**
 * Get the diameter of a tree
 */
public class Exercise15 {

    /**
     * O(n) algorithm
     * @return
     */
    public static Response calculateDiameter(Integer[][] tree) {
        return calculateDiameter(tree, 0);
    }

    public static Response calculateDiameter(Integer[][] tree, Integer root) {
        if(tree[root][0] == null && tree[root][1] == null){
            return Response.vazia() ;
        }

        Response da = Response.vazia();
        if(tree[root][0] != null){
            da =calculateDiameter(tree, tree[root][0]);
            da.distance++;
        }

        Response db = Response.vazia();
        if(tree[root][1] != null){
            db =calculateDiameter(tree, tree[root][1]);
            db.distance++;
        }

        return new Response(Math.max(da.distance + db.distance, Math.max(da.diameter, db.diameter)), Math.max(da.distance, db.distance));
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

        Response diameter = calculateDiameter(binaryTree);
        System.out.println(diameter);
    }

    private static class Response{
        public int diameter;
        public int distance;


        public Response(int diameter, int distance) {
            this.diameter = diameter;
            this.distance = distance;
        }

        public static Response vazia(){
            return new Response(0,0);
        }

        @Override
        public String toString() {
            return "Response{" + "diameter=" + diameter + ", distance=" + distance + '}';
        }
    }
}
