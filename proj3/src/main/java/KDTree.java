import java.io.File;

/**
 * @author Bunny
 * @create 2022-01-24 18:29
 */
public class KDTree {

    private TreeNode root;

    private int size;


    public KDTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Node node) {
        if (root == null) {
            root = new TreeNode(node, 1);
            size++;
            return;
        }

        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.value.getId() == node.getId()) {
                return;
            }
            pre = cur;
            cur = cur.isRightOrTop(node) ? cur.right : cur.left;
        }
        if (pre.isRightOrTop(node)) {
            pre.right = new TreeNode(node, pre.xOrY * (-1));
        } else {
            pre.left = new TreeNode(node, pre.xOrY * (-1));
        }
        size++;
    }

    public long nearest(double lon, double lat) {
        return nearestNode(new Node(-1, lon, lat), root, Double.MAX_VALUE).getId();
    }

    private Node nearestNode(Node value, TreeNode curNode, double min) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (curNode.value == null) {
            return null;
        }
        if (curNode.value.equals(value)) {
            return curNode.value;
        }
        Node minNode = null;
        double curMin = min;
        double nodeDistance = GraphDB.distance(curNode.value.getLongitude(), curNode.value.getLatitude(),
                value.getLongitude(), value.getLatitude());
        if (nodeDistance < curMin) {
            curMin = nodeDistance;
            minNode = curNode.value;
        }
        TreeNode first = curNode.left;
        TreeNode second = curNode.right;
        if (curNode.isRightOrTop(value)) {
            first = curNode.right;
            second = curNode.left;
        }
        if (first != null) {
            Node firstBest = nearestNode(value, first, curMin);
            if (firstBest != null) {
                minNode = firstBest;
                curMin = GraphDB.distance(value.getLongitude(), value.getLatitude(),
                        minNode.getLongitude(), minNode.getLatitude());
            }
        }

        if (second == null) {
            return minNode;
        }

        if (second == curNode.left) {
            if (curNode.xOrY == 1 && value.getLongitude() - curNode.value.getLongitude() >= curMin) {
                return minNode;
            }
            if (curNode.xOrY == -1 && value.getLatitude() - curNode.value.getLatitude() >= curMin) {
                return minNode;
            }
        } else if (second == curNode.right) {
            if (curNode.xOrY == 1 && curNode.value.getLongitude() - value.getLongitude() >= curMin) {
                return minNode;
            }
            if (curNode.xOrY == -1 && curNode.value.getLatitude() - value.getLatitude() >= curMin) {
                return minNode;
            }
        }
        Node secondBest = nearestNode(value, second, curMin);
        if (secondBest != null) {
            minNode = secondBest;
        }
        return minNode;
    }

    private static class TreeNode {

        private Node value;
        //1以x作为划分点，-1以y作为划分点
        private int xOrY;

        private TreeNode left;

        private TreeNode right;

        TreeNode(Node value, int xOrY) {
            this.value = value;
            this.xOrY = xOrY;
            left = null;
            right = null;
        }

        public boolean isRightOrTop(Node node) {
            if (xOrY == 1) {
                if (node.getLongitude() > value.getLongitude()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (node.getLatitude() > value.getLatitude()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

    }
}
