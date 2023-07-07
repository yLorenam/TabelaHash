public class TabelaHash {

    private Tree[] buckets;
    private int size;

    public TabelaHash(int size) {
        this.buckets = new Tree[size];
        for (int i = 0; i < size; i++) {
            this.buckets[i] = new Tree();
        }
        this.size = size;
    }

    private int hashFunction(int key) {
        return key % this.size;
    }

    public void put(int key, String value) {
        int index = hashFunction(key);
        buckets[index].insert(key, value);
    }

    public int getSize() {
        return size;
    }

    public String get(int key) {
        int index = hashFunction(key);
        return (String) buckets[index].get(key);
    }

    public Tree getTree(int key) {
        int index = hashFunction(key);
        return buckets[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (int i = 0; i < size; i++) {
            sb.append("\t").append(buckets[i].toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        TabelaHash ht = new TabelaHash(4);
        ht.put(1, "valor a");
        ht.put(2, "valor b");
        ht.put(3, "valor c");
        ht.put(4, "valor d");
        ht.put(5, "valor e");

        System.out.println(ht.getSize());
        System.out.println(ht.toString());

        System.out.println(ht.get(3));
        System.out.println(ht.toString());
    }
}
