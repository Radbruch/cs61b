public class UnionFind {
    /**
     * This union-find is based on Weighted quick-union with path compression.
     */
    
    private int[] parent;

    public UnionFind(int n) {
        /** Creates a UnionFind data structure holding n vertices.
         * Initially, all vertices are in disjoint sets.
         */
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }

    public void validate(int v1) {
        /** Throws an exception if v1 is not a valid index.*/
        if (0 <= v1 && v1 < parent.length) {
            return;
        }
        else {
            throw new IllegalArgumentException("v1 is out of index.");
        }
    }

    public int sizeOf(int v1) {
        /** Returns the size of the set v1 belongs to.*/
        validate(v1);
        int root = find(v1);
        return -parent(root);
    }


    public int parent(int v1) {
        /** Returns the parent of v1.
         * If v1 is the root of a tree, returns the negative size of the tree for which v1 is the root.*/
        validate(v1);
        return parent[v1];

    }

    public boolean connected(int v1, int v2) {
        /** Returns true if nodes v1 and v2 are connected.*/
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        /** Connects two elements v1 and v2 together.
         * v1 and v2 can be any valid elements, and a union-by-size heuristic is used.
         * If the sizes of the sets are equal, tie break by connecting v1’s root to v2’s root.*/
        if (connected(v1, v2)) return;
        else {
            int rootv1 = find(v1);
            int rootv2 = find(v2);
            int sizev1 = -parent[rootv1];
            int sizev2 = -parent[rootv2];
            if (sizev1 > sizev2) {
                parent[rootv2] = rootv1;
                parent[rootv1] = -(sizev1+sizev2);
            } else if (sizev1 < sizev2) {
                parent[rootv1] = rootv2;
                parent[rootv2] = -(sizev1+sizev2);
            } else {
                parent[rootv1] = rootv2;
                parent[rootv2] = -(sizev1+sizev2);
            }
        }
    }

    public int find(int v1) {
        /** Returns the root of the set v1 belongs to.
         * Path-compression is employed allowing for fast search-time.*/
        validate(v1);
        int index = v1;
        if (parent(v1) < 0) return v1;
        else {
            while(parent(index) >= 0) {
               index = parent(index);
            }
            parent[v1] = index;
            return index;
        }

    }


    public static void main(String[] args) {
        UnionFind system = new UnionFind(10);
        System.out.println(system.sizeOf(1)); //return 1
        System.out.println(system.connected(1, 2)); //return false
        system.union(0, 1);
        system.union(2, 3);
        system.union(3, 4);
        system.union(1, 2);
        System.out.println(system.sizeOf(2)); //return 5
        System.out.println(system.sizeOf(0)); //return 5
        System.out.println(system.connected(1, 4)); //return true
        System.out.println(system.parent(0)); //return 2
    }
}
