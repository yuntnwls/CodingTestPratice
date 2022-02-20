package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1991
// 전위순회(preorder) : root->left->right
// 중위순회(inorder) : left->root->right
// 후위순회(postorder) : left->right->root
public class Tree {

    private static Node root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String data = st.nextToken();
            String leftData = st.nextToken();
            String rightData = st.nextToken();
            
            createNode(data, leftData, rightData);
        }

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    // 전위순회
    private static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data);
            if (node.left != null)
                preOrder(node.left);
            if (node.right != null)
                preOrder(node.right);
        }
    }

    // 중위순회
    private static void inOrder(Node node) {
        if (node != null) {
            if (node.left != null)
                inOrder(node.left);
            System.out.print(node.data);
            if (node.right != null)
                inOrder(node.right);
        }
    }

    // 후위순회
    private static void postOrder(Node node) {
        if (node != null) {
            if (node.left != null)
                postOrder(node.left);
            if (node.right != null)
                postOrder(node.right);
            System.out.print(node.data);
        }
    }

    private static void createNode(String data, String leftData, String rightData) {
        if (root == null) {
            root = new Node(data);

            // 좌우 값이 있는 경우, 좌/우 값이 .이 아닌 경우
            if (!leftData.equals("."))
                root.left = new Node(leftData);
            if (!rightData.equals("."))
                root.right = new Node(rightData);
        } else {
            searchNode(root, data, leftData, rightData);
        }
    }

    private static void searchNode(Node node, String data, String leftData, String rightData) {
        if (node == null) {
            return;
        } else if (node.data.equals(data)) {
            if (!leftData.equals("."))
                node.left = new Node(leftData);
            if (!rightData.equals("."))
                node.right = new Node(rightData);
        } else {
            // 아직 탐색할 노드가 남아있는 경우
            searchNode(node.left, data, leftData, rightData);   // 왼쪽 재귀
            searchNode(node.right, data, leftData, rightData);  // 오른쪽 재귀
        }
    }

    static class Node {
        String data;
        Node left;
        Node right;

        public Node(String data) {
            this.data = data;
        }
    }
}
