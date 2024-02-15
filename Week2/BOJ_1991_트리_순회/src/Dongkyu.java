import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dongkyu {

    static Node[] nodes;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        nodes = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            char data = (char) (64 + i);	// A부터 시작하도록 
            nodes[i] = new Node(data);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char parentData = st.nextToken().charAt(0);
            char leftData = st.nextToken().charAt(0);
            char rightData = st.nextToken().charAt(0);

            if (leftData != '.') {
                nodes[parentData - 'A' + 1].left = nodes[leftData - 'A' + 1];
            }

            if (rightData != '.') {
                nodes[parentData - 'A' + 1].right = nodes[rightData - 'A' + 1];
            }
        }

        preorder(nodes[1]);
        System.out.println(sb);
        sb.setLength(0);

        inorder(nodes[1]);
        System.out.println(sb);
        sb.setLength(0);

        postorder(nodes[1]);
        System.out.println(sb);
    }

    static void preorder(Node node) {
        if (node == null) {
            return;
        }

        sb.append(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        sb.append(node.data);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        sb.append(node.data);
    }
}

class Node {
    char data;
    Node left;
    Node right;

    Node(char data) {
        this.data = data;
    }
}