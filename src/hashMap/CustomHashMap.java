package hashMap;
/*
Key Points To Remember:

HashMap insertion and retrieval takes O(1) time complexity.
HashMap stores values in key-value pairs.
Each key-value pair is stored in Entry<K,V> object.
Every Entry object is stored in array named table[] as per calculated index.
Index is calculated on the basis of Hash code of the key and length of table[] array.
For null key, hash value is reserved to be 0.
Default Capacity of HashMap is 16.
Default Load Factor is 0.75
 */

public class CustomHashMap<K,V>{

    // A node to store key-value pairs
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;

        Node(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node<K, V>[] buckets; // Array of buckets
    private static final int DEFAULT_CAPACITY = 16; // Default initial size
    private static final float LOAD_FACTOR = 0.75f; // Load factor threshold
    private int size = 0; // Number of key-value pairs

    // We initialize the DEAFAULT_CAPACITY
    public CustomHashMap(){
        buckets = new Node[DEFAULT_CAPACITY];
    }

    // Compute the hash index for a given key
    private int getBucketIndex(K key){
        return (key == null) ? 0 : key.hashCode() % DEFAULT_CAPACITY;// to bring within range of bucket size
    }


    //Add or update a key-value pair
    public void put(K key, V value){
        // for getting the value, 1st we have to find index where key exist in bucket list.
        System.out.println("get is called for key "+key);
        // 1)get the index where key exist
        int index = getBucketIndex(key);
        // 2) Adding the value in newNode
        Node<K,V> newNode = new Node<>(key,value);
        // 3) a)If key already exist then update the value else
        // b) Add this key at the first in this linklist
        if(buckets[index] == null){
            buckets[index] = newNode;
        }else{
            // Traverse the linked list to find the key or add a new node
            Node<K,V> current = buckets[index];
            while(current != null){
                // can't compare custom object with '==' so used 'equals'.
                // '==' compares the address space not the actual value.
                if(current.key.equals(key)){
                    current.value = value;// Update value if key exists
                    return;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = newNode;// Add new node to the chain
        }
        size++;// Increase total no of elements

        // check if re-hashing is required
        if((1.0)*size/buckets.length > LOAD_FACTOR){
            rehash();
        }
    }

    // Retrieve a value by key
    public V get(K key){
        // 1) First find the index where this key will go
        int index = getBucketIndex(key);
        // 2) Get the current of the linklist at this index
        Node<K,V> current = buckets[index];
        // 3) Now find in this link list 'key' exist or not.
        while (current != null){

            if (current.key.equals(key)){
                return current.value;

            }
            current = current.next;
        }

        return null;// Key not found
    }

    // Remove a key-value pair by key
    public void remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next; // Remove the head node
                } else {
                    prev.next = current.next; // Remove the current node
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // Rehashing: Resize and redistribute the nodes
    private void rehash() {
        System.out.println("------------------------------------Rehashing------------------------------------ ");
        Node<K, V>[] oldBuckets = buckets;
        buckets = new Node[oldBuckets.length * 2]; // Double the size
        size = 0; // Reset size

        for (Node<K, V> node : oldBuckets) {
            while (node != null) {
                put(node.key, node.value); // Reinsert each node into the new buckets
                node = node.next;
            }
        }
    }


    // Get the current size of the map
    public int size() {
        return size;
    }

    // Check the size is empty or not
    public boolean isEmpty() {
        return size == 0;
    }


}
