package hw2;

/**
 * 并查集
 *
 * @author Bunny
 * @create 2022-01-14 17:22
 */
public class MyUnion {

    /**
     * i的parent
     */
    private int[] parent;

    /**
     * 集合的数量
     */
    private int[] rank;

    public MyUnion(int N) {
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 将两个不相交的集合合并
     *
     * @param a
     * @param b
     */
    public void union(int a, int b) {
        int i = find(a);
        int j = find(b);
        if (i == j) {
            return;
        }
        if (rank[i] < rank[j]) {
            parent[i] = j;
            rank[j] += rank[i];

        } else {
            parent[j] = i;
            rank[i] += rank[j];
        }
    }

    /**
     * 查询a的父节点
     *
     * @param a
     * @return
     */
    public int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    public boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}
