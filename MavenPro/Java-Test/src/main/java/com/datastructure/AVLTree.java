package com.datastructure;

class Node{
    Node left, right;
    int data;
    int height = 0;

    Node(){}

    Node(int data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", height=" + height +
                '}';
    }
}

public class AVLTree {
    Node root;
    public AVLTree(){
        this.root = null;
    }

    public Node getRoot(){
        return root;
    }

    public int treeHeight(){
        if(root == null)
            return 0;
        return root.height + 1;
    }

    public void insert(int data){       //insert always start from root
        this.root = insert(root, data);
    }

    public Node search(int data){
        Node findNode = root;
        while(findNode!=null && findNode.data != data){
            if(data < findNode.data){
                findNode = findNode.left;
            }else{
                findNode = findNode.right;
            }
        }
        return findNode;
    }

    private Node insert(Node n, int data){
        if(n == null){
            n = new Node(data);
            return n;
        }

        if(data < n.data){  //insert node to left
            n.left = insert(n.left, data);
            if(height(n.left) - height(n.right) == 2){
                if(data < n.left.data){
                    n = rightRotate(n);
                }else{
                    n = doubleLeftRotateRightRotate(n);
                }
            }
        }else if( data > n.data){
            n.right = insert(n.right, data);
            if(height(n.right) - height(n.left) == 2){
                if(data > n.right.data){
                    n = leftRotate(n);
                }else{
                    n = doubleRightRotateLeftRotate(n);
                }
            }
        }else{
            //insert a exist data, do nothing

        }

        resetNodeHeight(n);
        return n;
    }

    private Node doubleRightRotateLeftRotate(Node n) {
        Node right = rightRotate(n.right);
        n.right = right;
        return leftRotate(n);
    }

//    private Node shiftRigthChild(Node n) {
//        Node left = n.left;
//        n.left = left.right;
//        left.right = n;
//        resetNodeHeight(n);
//        resetNodeHeight(left);
//        return left;
//    }

    private Node leftRotate(Node n) {
        Node right = n.right;
        n.right = right.left;
        right.left = n;
        resetNodeHeight(n);
        resetNodeHeight(right);
        return right;
    }

    private Node doubleLeftRotateRightRotate(Node n) {
        Node left = leftRotate(n.left);
        n.left = left;
        return rightRotate(n);
    }

//    private Node shiftLeftChild(Node n) {
//        Node right = n.right;
//        n.right = right.left;
//        right.left = n;
//        resetNodeHeight(n);
//        resetNodeHeight(right);
//        return right;
//    }

    private Node rightRotate(Node n) {
        Node leftChild = n.left;
        n.left = leftChild.right;
        leftChild.right = n;
        resetNodeHeight(n);
        resetNodeHeight(leftChild);
        return leftChild;
    }

    private void resetNodeHeight(Node n){
        n.height = Math.max(height(n.left) , height(n.right)) + 1;
    }

    private int height(Node n) {
        return n == null ? -1: n.height;
    }

    public void preSort(){
        preSort(this.root);
    }

    private void preSort(Node node) {
        if(node != null){
            System.out.print(" "+node.data);
            preSort(node.left);
            preSort(node.right);
        }
    }

    public void delete(int data){
        if(root == null)
            return;
        this.root = _deleteNode(root, data);
    }

    private Node _deleteNode(Node n, int data){
        if(n == null)
        return null;
        if (data < n.data) {    //delete left
            n.left = _deleteNode(n.left, data);
            if(height(n.right) - height(n.left) == 2){
                Node rotateNode = n.right;
                if(height(rotateNode.left)>height(rotateNode.right)){
                    n = doubleRightRotateLeftRotate(n);
                }else{
                    n = leftRotate(n);
                }
            }
        }else if(data > n.data){
            n.right = _deleteNode(n.right, data);
            if(height(n.left) - height(n.right) == 2){
                Node rotateNode = n.left;
                if(height(rotateNode.right)>height(rotateNode.left)){
                    n = doubleLeftRotateRightRotate(n);
                }else{
                    n = rightRotate(n);
                }
            }
        }else {
            if(n.left!=null && n.right!=null){
                //find delete
                int minDataOfRightChild = findMinData(n.right);
                n.data = minDataOfRightChild;
                System.out.println("Find Min Data:"+minDataOfRightChild);

                n.right = _deleteNode(n.right, minDataOfRightChild);
            }else{
                n = n.left!=null?n.left:n.right;
            }
        }
        if(n!=null)
            resetNodeHeight(n);
        return n;
    }

    private int findMinData(Node n) {
        if(n.left!=null)
            return findMinData(n.left);
        else
            return n.data;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        tree.insert(14);
//        tree.insert(12);
//        tree.insert(16);
//        tree.insert(10);
//        tree.insert(8);
//        tree.insert(6);

        tree.insert(60);
        tree.insert(37);
        tree.insert(58);
        tree.insert(39);
        tree.insert(26);
        tree.insert(18);
        tree.insert(73);
        tree.insert(62);
//        System.out.println(tree.getRoot());

        System.out.println("tree height:" + tree.treeHeight());
        System.out.print("PreSort: ");
        tree.preSort();
        System.out.println();

        System.out.println(tree.findMinData(tree.getRoot()));
        System.out.println(tree.search(37));
        tree.delete(37);
//        System.out.println(tree.getRoot());
        System.out.print("PreSort: ");
        tree.preSort();

        tree.delete(58);
        System.out.print("PreSort: ");
        tree.preSort();
    }

}
