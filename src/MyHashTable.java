import java.util.HashSet;
import java.util.Set;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        chainArray = new HashNode[11];
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        int index = key.hashCode() % chainArray.length;
        return index < 0 ? index + chainArray.length : index;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (chainArray[index] != null) {
            newNode.next = chainArray[index];
        }

        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> curr = chainArray[index];

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                sb.append(node.key).append(" = ").append(node.value).append(", ");
                node = node.next;
            }
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }

        sb.append("}");
        return sb.toString();
    }
}
