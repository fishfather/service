package com.datastructure;

import java.util.LinkedList;

public class BinaryTree {
    static class Node {
        int data;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "data is :" + data + " | left is:" + (left != null ? left.data : null)
                    + " | right is:" + (right != null ? right.data : null);
        }
    }

    private Node root = null;

    public Node getRoot() {
        return this.root;
    }

    public void insert(int data) {
        Node insertNode = new Node(data);
        if (root == null) {
            root = insertNode;
            return;
        }

        Node currentNode = root;
        while (true) {
            if (insertNode.data > currentNode.data) {
                if (currentNode.right == null) {
                    currentNode.right = insertNode;
                    break;
                } else {
                    currentNode = currentNode.right;
                }
            } else {
                if (currentNode.left == null) {
                    currentNode.left = insertNode;
                    break;
                } else {
                    currentNode = currentNode.left;
                }
            }
        }
    }

    public Node find(int data) {
        Node currentNode = root;
        while (currentNode != null && currentNode.data != data) {
            if (currentNode.data > data) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return currentNode;
    }

    public Node find_approach2(int data) {
        return _find_approach2(root, data);
    }

    private Node _find_approach2(Node n, int data) {
        if(n == null)
            return null;
        if(n.data == data)
            return n;
        if(n.data > data){
            return _find_approach2(n.left, data);
        }else{
            return _find_approach2(n.right, data);
        }
    }

    public boolean delete(int data) {
        Node currentNode = root;
        Node parentNode = null;
        boolean isLeftChild = true;
        while (currentNode != null && currentNode.data != data) {
            parentNode = currentNode;
            if (currentNode.data > data) {
                currentNode = currentNode.left;
                isLeftChild = true;
            } else {
                currentNode = currentNode.right;
                isLeftChild = false;
            }
        }
        if (currentNode == null)
            return false;

        if (currentNode.left == null && currentNode.right == null) {
            if (parentNode == null) { //root node
                root = null;
                return true;
            }
            if (isLeftChild) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (currentNode.left == null) {
            if (parentNode == null) { //root node
                root = currentNode.right;
                return true;
            }

            if (isLeftChild) {
                parentNode.left = currentNode.right;
            } else {
                parentNode.right = currentNode.right;
            }

        } else if (currentNode.right == null) {
            if (parentNode == null) { //root node
                root = currentNode.left;
                return true;
            }
            if (isLeftChild) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }

        } else {
            //获得右子树的中序遍历第一个节点, 用它替换删除节点
            Node inOrderFirstNode = currentNode.right;
            Node parentOfFirstNode = currentNode;
            while (inOrderFirstNode != null && inOrderFirstNode.left != null) {
                parentOfFirstNode = inOrderFirstNode;
                inOrderFirstNode = inOrderFirstNode.left;
            }

            parentOfFirstNode.left = inOrderFirstNode.right;

            inOrderFirstNode.left = currentNode.left;
            inOrderFirstNode.right = currentNode.right;
            if (parentNode == null) {
                root = inOrderFirstNode;
            } else {
                if (isLeftChild) {
                    parentNode.left = inOrderFirstNode;
                } else {
                    parentNode.right = inOrderFirstNode;
                }
            }
        }

        return true;
    }

    public void preOrder(Node node) {        //前序遍历
        if (node == null) {
            return;
        }
        System.out.print(">> " + node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {     //中序遍历
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(">> " + node.data + " ");
        inOrder(node.right);
    }

    public void postOrder(Node node) {   //后序遍历
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(">> " + node.data + " ");
    }

    public void levelOrder(Node node) {   //层序遍历
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.print(">> " + front.data + " ");
            if (front.left != null) {
                queue.add(front.left);
            }
            if (front.right != null) {
                queue.add(front.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(6);
        tree.insert(3);
        tree.insert(14);
        tree.insert(10);
        tree.insert(16);
        tree.insert(9);
        tree.insert(13);
        tree.insert(11);
        tree.insert(12);

        System.out.println("\r\nPre Order:");
        tree.preOrder(tree.getRoot());
        System.out.println("\r\nIn Order:");
        tree.inOrder(tree.getRoot());
        System.out.println("\r\nPost Order:");
        tree.postOrder(tree.getRoot());
        System.out.println("\r\nLevel Order:");
        tree.levelOrder(tree.getRoot());
        System.out.println();

        System.out.println("Find Node: " + tree.find(15));

        tree.delete(10);
        System.out.println("\r\nIn Order:");
        tree.inOrder(tree.getRoot());
        System.out.println();

        System.out.println("Find Node: " + tree.find(14));
        System.out.println("Find Node: " + tree.find(11));
        System.out.println("Find Node: " + tree.find_approach2(11));

        tree.delete(6);
        tree.inOrder(tree.getRoot());
        System.out.println();
    }
}

