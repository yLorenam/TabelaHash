public class Tree {
    private Node root;

    public void insert(int key, Object value){
        this.root = this.insert(this.root, key, value);
    }
    private Node insert(Node root, int key, Object value){
        if(root == null){
            Node node = new Node();
            node.setKey(key);
            node.setValue(value);
            return node;
        } else {
            if(key < root.getKey()){
                root.setLeft(this.insert(root.getLeft(), key, value));
            } else if (key > root.getKey()){
                root.setRight(this.insert(root.getRight(), key, value));
            }
            return root;
        }
    }

    private Object get(Node root, int key) {
        if (root != null) {
            if (key < root.getKey()) {
                return this.get(root.getLeft(), key);
            } else if (key > root.getKey()) {
                return this.get(root.getRight(), key);
            } else {
                return root.getValue();
            }
        }
        return null;
    }

    public Object get(int key){
        return this.get(this.root, key);
    }

    private Node getNode(Node root, int key){
        if (root != null){
            if (key < root.getKey()){
                return this.getNode(root.getLeft(), key);
            } else if (key > root.getKey()) {
                return this.getNode(root.getRight(), key);
            } else {
                return root;
            }
        }
        return null;
    }

    private Node getParentNode(Node root, int key) {
        if (root != null) {
            if (key < root.getKey()) {
                if (root.getLeft() != null && root.getLeft().getKey() == key) {
                    return root;
                } else {
                    return getParentNode(root.getLeft(), key);
                }
            } else if (key > root.getKey()) {
                if (root.getRight() != null && root.getRight().getKey() == key) {
                    return root;
                } else {
                    return getParentNode(root.getRight(), key);
                }
            }
        }
        return null;
    }

    private Node findSuccessor(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private Object remove(int key) {
        Node node = getNode(this.root, key);
        if (node != null) {
            Node parentNode = getParentNode(this.root, key);
            if (parentNode == null) {
                this.root = removeNode(node);
            } else if (parentNode.getLeft() != null && parentNode.getLeft().getKey() == key) {
                parentNode.setLeft(removeNode(node));
            } else {
                parentNode.setRight(removeNode(node));
            }
            return node.getValue(); // Return the value of the removed node
        }
        return null;
    }

    private Node removeNode(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        } else {
            Node successor = findSuccessor(node.getRight());
            Node successorParent = getParentNode(node, successor.getKey());
            if (successorParent != node) {
                successorParent.setLeft(successor.getRight());
                successor.setRight(node.getRight());
            }
            successor.setLeft(node.getLeft());
            return successor;
        }
    }

    private void preOrdemVisit(Node node) {
        if (node != null) {
            System.out.println(node.getKey() + " ");
            preOrdemVisit(node.getLeft());
            preOrdemVisit(node.getRight());
        }
    }

    public void preOrdem() {
        preOrdemVisit(this.root);
    }

    private void emOrdemVisit(Node node) {
        if (node != null) {
            emOrdemVisit(node.getLeft());
            System.out.println(node.getKey() + " ");
            emOrdemVisit(node.getRight());
        }
    }

    public void emOrdem() {
        emOrdemVisit(this.root);
    }

    private void posOrdemVisit(Node node) {
        if (node != null) {
            posOrdemVisit(node.getLeft());
            posOrdemVisit(node.getRight());
            System.out.println(node.getKey() + " ");
        }
    }

    public void posOrdem() {
        posOrdemVisit(this.root);
    }

    private String print(Node root, int lvl){
        String out = "";
        for(int i = 0; i < lvl; i++){
            out += "\t";
        }
        out += root.getKey() + ": " + (root.getValue() != null ? root.getValue() : "null");
        out += "\n";
        out += (root.getLeft() != null ? print(root.getLeft(), lvl + 1) : "");
        out += (root.getRight() != null ? print(root.getRight(), lvl + 1) : "");
        return out;
    }

    public String toString(){
        return this.print(this.root, 0);
    }

    public static void main(String[] args) {
        Tree t = new Tree();

        t.insert(20, 20);
        t.insert(5, 5);
        t.insert(40, 40);
        t.insert(0, 0);
        t.insert(10, 10);
        t.insert(30, 30);
        t.insert(50, 50);


        System.out.println("PreOrdem");
        t.preOrdem();
        System.out.println();

        System.out.println("EmOrdem");
        t.emOrdem();
        System.out.println();

        System.out.println("PosOrdem");
        t.posOrdem();
        System.out.println();

      /*  t.remove(40);
        t.remove(20);

       */


        System.out.println("PreOrdem");
        t.preOrdem();
        System.out.println();

        System.out.println("EmOrdem");
        t.emOrdem();
        System.out.println();

        System.out.println("PosOrdem");
        t.posOrdem();
        System.out.println();

       /* t.remove(0);
        t.remove(5);
        t.remove(10);
        t.remove(30);
        t.remove(50);

        */
    }
}