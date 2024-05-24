import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    private Node insert(Node root, K key, V val) {
        if (root == null)
            return new Node(key, val);

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = insert(root.left, key, val);
        } else if (cmp > 0) {
            root.right = insert(root.right, key, val);
        } else {
            root.val = val;
        }
        return root;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key + " : " + node.val);
            inOrder(node.right);
        }
    }

    public K getKey(V val) {
        Node node = findForKey(root, val);
        return node != null ? node.key : null;
    }

    public V getVal(K key) {
        Node node = findForVal(root, key);
        return node != null ? node.val : null;
    }

    private Node findForKey(Node node, V val) {
        if (node == null)
            return null;

        if (val.equals(node.val))
            return node;

        Node leftNode = findForKey(node.left, val);
        if (leftNode != null)
            return leftNode;

        return findForKey(node.right, val);
    }

    private Node findForVal(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findForVal(node.left, key);
        } else if (cmp > 0) {
            return findForVal(node.right, key);
        } else {
            return node;
        }
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node smallestNode = findSmallestVal(node.right);
            node.key = smallestNode.key;
            node.val = smallestNode.val;
            node.right = remove(node.right, smallestNode.key);
        }
        return node;
    }

    private Node findSmallestVal(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return size(node.left) + size(node.right) + 1;
        }
    }

    public Iterable<Node> iterator() {
        return new Iterable<>() {
            @Override
            public Iterator<Node> iterator() {
                return new BSTIterator(root);
            }
        };
    }

    private class BSTIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            pushLeft(node.right);
            return node;
        }
    }
}

