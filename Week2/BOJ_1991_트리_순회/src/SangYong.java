import java.util.*;

class Node {
    char data;
    Node left;
    Node right;

    public Node(char data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class SangYong {
    
    static Node[] tree;
    
    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new Node[N];
        
        for(int i=0; i<N; i++){
            char parent = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            if(tree[parent-'A']==null){
                tree[parent-'A'] = new Node(parent);
            }
            if(left != '.'){
                tree[left-'A'] = new Node(left);
                tree[parent-'A'].left = tree[left-'A'];
            }
            if(right != '.'){
                tree[right-'A'] = new Node(right);
                tree[parent-'A'].right = tree[right-'A'];
            }
        }

        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);
    }
}