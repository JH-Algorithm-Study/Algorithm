import java.io.*;
import java.util.*;

public class JiHwan {
    private static class Node {
        char data; 
        Node left, right;

        public Node(char data) {
            this.data = data;
        }
    }

    private Node root; // root 노드 찾기
    private Map<Character, Node> nodes = new HashMap<>();

    public void addNode(char data, char left, char right) {
        if (!nodes.containsKey(data)) {
            nodes.put(data, new Node(data));
            if (root == null) {
                root = nodes.get(data);
            }
        }
        Node parent = nodes.get(data);

        if (left != '.') {
            nodes.putIfAbsent(left, new Node(left));
            parent.left = nodes.get(left);
        }
        if (right != '.') {
            nodes.putIfAbsent(right, new Node(right));
            parent.right = nodes.get(right);
        }
    }

    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data);
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data);
            inorder(node.right);
        }
    }

    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Main tree = new Main();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.addNode(parent, left, right);
        }

        tree.preorder(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);
    }
}