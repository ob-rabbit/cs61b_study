package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) == 0) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (root == null || key == null) {
            return null;
        }
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {

        if (key.compareTo(p.key) < 0) {
            if (p.left == null) {
                return p;
            } else {
                return putHelper(key, value, p.left);
            }
        } else if (key.compareTo(p.key) > 0) {
            if (p.right == null) {
                return p;
            } else {
                return putHelper(key, value, p.right);
            }
        } else {
            return p;
        }
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        //不存放null值
        if (key == null) {
            return;
        }
        if (root == null) {
            root = new Node(key, value);
            root.left = null;
            root.right = null;
            size++;
            return;
        }
        Node local = putHelper(key, value, root);
        if (key.compareTo(local.key) < 0) {
            local.left = new Node(key, value);
            size++;
        } else if (key.compareTo(local.key) > 0) {
            local.right = new Node(key, value);
            size++;
        } else {
            local.value = value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        Node pre = null;
        Node cur = root;
        while (cur != null && key.compareTo(cur.key) != 0) {
            if (key.compareTo(cur.key) < 0) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        //没找到key
        if (cur == null) {
            return null;
        }
        size--;
        //没有左右子树
        if (cur.left == null && cur.right == null) {
            //为根节点
            if (pre == null) {
                root = null;
            } else {
                //为叶子节点
                if (pre.left == cur) {
                    pre.left = null;
                } else {
                    pre.right = null;
                }
            }
            return cur.value;
        }
        //有左子树或者右子树
        if (cur.left != null && cur.right == null) {
            if (pre == null) {
                V value = root.value;
                root = root.left;
                return value;
            } else {
                if (pre.left == cur) {
                    pre.left = cur.left;
                } else {
                    pre.right = cur.left;
                }
                cur.left = null;
                return cur.value;
            }
        }
        if (cur.right != null && cur.left == null) {
            if (pre == null) {
                V value = root.value;
                root = root.right;
                return value;
            } else {
                if (pre.left == cur) {
                    pre.left = cur.right;
                } else {
                    pre.right = cur.right;
                }
                cur.right = null;
                return cur.value;
            }
        }

        //有左子树也有右子树
        //找到右子树的最小值
        Node minLeft = cur.right;
        Node preMinLeft = cur;
        while (minLeft.left != null) {
            preMinLeft = minLeft;
            minLeft = minLeft.left;
        }
        if (minLeft == cur.right) {
            V value = cur.value;
            cur.value = minLeft.value;
            cur.right = minLeft.right;
            return value;
        } else {
            V value = cur.value;
            cur.value = minLeft.value;
            preMinLeft.left = null;
            return value;
        }

    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
