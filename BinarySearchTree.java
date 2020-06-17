
package project3simse;

import java.util.Comparator;

/*
The BinarySearchTree is a generic class which means it can accept variables of more than one type. 
For example if a method accepts a variable input, it has to specify type, like String, Integer, etc. 
The advantage of a generic class is this specification does not have to be made. The caveat is that 
when creating methods within the class, they must be capable of handling input of different variable types,
in this case, that is why the Fraction class has to have an overriding compareTo() method becuase the 
standard compareTo() method can be used on Integers but not custom made variable types.

This class performs several important tasks within the program. The first of which is defining what a Node
is to create the blueprints for and initiate the binary search tree. The compare method uses the appropriate
compareTo() method depending on which variable type is passed to compare the inputs for the purposes of sorting
them. There are also methods for walking through the completed tree forward and backwards then a third method used
in the action performed method that simplifies choosing between the two. 
*/

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;
    private Comparator<T> comparator;

    public BinarySearchTree() {
        this.root = null;
        this.comparator = null;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }
    
    class Node {
        private Node left;
        private Node right;
        private T data;

        public Node(T data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }

        public Node queryLeft() {
            return left;
        }

        public Node queryRight() {
            return right;
        }

        public T queryData() {
            return data;
        }

        public void setLeftData(Node left) {
            this.left = left;
        }

        public void setRightData(Node right) {
            this.right = right;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    }

    public int compare(T data1, T data2) {
        if (this.comparator != null)
            return this.comparator.compare(data1, data2);
        else
            return data1.compareTo(data2);
    }
    
    public void insert(Node currentNode, T data) {
        if (compare(data, currentNode.queryData()) < 0) {
            if (currentNode.queryLeft() != null)
                insert(currentNode.queryLeft(), data);
            else
                currentNode.setLeftData(new Node(data));
        } 
        else if (compare(data, currentNode.queryData()) >= 0) {
            if (currentNode.queryRight() != null)
                insert(currentNode.queryRight(), data);
            else
                currentNode.setRightData(new Node(data));
        } 
    }

    public void insert(T data) {
        if (root == null) {
            this.root = new Node(data);
        } else {
            insert(this.root, data);
        } 
    }

    public StringBuffer inOrderWalk(Node root, StringBuffer stringBuffer) {
        if (root != null) {
            inOrderWalk(root.queryLeft(), stringBuffer);
            stringBuffer.append(root.queryData() + " ");
            inOrderWalk(root.queryRight(), stringBuffer);
        }

        return stringBuffer;
    }
    
    
    public StringBuffer backwardsOrderWalk(Node root, StringBuffer stringBuffer) {
        if (root != null) {
            backwardsOrderWalk(root.queryRight(), stringBuffer);
            stringBuffer.append(root.queryData() + " ");
            backwardsOrderWalk(root.queryLeft(), stringBuffer);
        }
        return stringBuffer;
    }


    public String sort(int sortOrder) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer = (sortOrder == 1) ? inOrderWalk(this.root, stringBuffer) : backwardsOrderWalk(this.root, stringBuffer);
        return stringBuffer.toString();
    }
    
}