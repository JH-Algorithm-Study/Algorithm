package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    char data;
    Node left, right;

    public Node(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class JaeSung {
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // A부터 Z까지 가능한 모든 노드에 대해 배열 생성
        nodes = new Node[26];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            Node parent = findOrCreateNode(p);

            if(left != '.') {
                parent.left = findOrCreateNode(left);
            }
            if(right != '.') {
                parent.right = findOrCreateNode(right);
            }
        }

        preorder(nodes['A' - 'A']);
        System.out.println();
        inorder(nodes['A' - 'A']);
        System.out.println();
        postorder(nodes['A' - 'A']);
    }

    static Node findOrCreateNode(char data) {
        if (nodes[data - 'A'] == null) {
            nodes[data - 'A'] = new Node(data);
        }
        return nodes[data - 'A'];
    }

    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }
}
