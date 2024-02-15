import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진 트리의 노드를 나타내는 클래스
class Node {
    char data; // 노드에 저장되는 데이터
    Node left; // 왼쪽 자식 노드
    Node right; // 오른쪽 자식 노드
    
    // 생성자: 노드의 데이터를 초기화하고 왼쪽, 오른쪽 자식 노드를 null로 설정
    public Node(char data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class YoonHa {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 트리의 노드 개수
        
        // 트리의 각 노드를 저장할 배열 선언
        Node[] nodes = new Node[N];
        
        // 트리의 각 노드 정보를 입력받아 배열에 저장
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());            
            char parent = st.nextToken().charAt(0); // 부모 노드
            char left = st.nextToken().charAt(0); // 왼쪽 자식 노드
            char right = st.nextToken().charAt(0); // 오른쪽 자식 노드
            
            // 부모 노드가 존재하지 않으면 부모 노드 생성
            if(nodes[parent - 'A'] == null) {
                nodes[parent - 'A'] = new Node(parent);
            }
            
            // 왼쪽 자식 노드가 존재하면 왼쪽 자식 노드 생성 후 부모 노드와 연결
            if(left != '.') {
                nodes[left - 'A'] = new Node(left);
                nodes[parent - 'A'].left = nodes[left - 'A'];
            }
            
            // 오른쪽 자식 노드가 존재하면 오른쪽 자식 노드 생성 후 부모 노드와 연결
            if(right != '.') {
                nodes[right - 'A'] = new Node(right);
                nodes[parent - 'A'].right = nodes[right - 'A'];
            }
        }
        
        // 전위 순회 수행 및 결과 출력
        preorder(nodes[0]);
        System.out.println();
        
        // 중위 순회 수행 및 결과 출력
        inorder(nodes[0]);
        System.out.println();
        
        // 후위 순회 수행 및 결과 출력
        postorder(nodes[0]);
    }
    
    // 전위 순회 메소드: 루트 -> 왼쪽 서브트리 -> 오른쪽 서브트리 순서로 방문
    static void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data); // 노드 방문
        preorder(node.left); // 왼쪽 서브트리 순회
        preorder(node.right); // 오른쪽 서브트리 순회
    }
    
    // 중위 순회 메소드: 왼쪽 서브트리 -> 루트 -> 오른쪽 서브트리 순서로 방문
    static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left); // 왼쪽 서브트리 순회
        System.out.print(node.data); // 노드 방문
        inorder(node.right); // 오른쪽 서브트리 순회
    }
    
    // 후위 순회 메소드: 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트 순서로 방문
    static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left); // 왼쪽 서브트리 순회
        postorder(node.right); // 오른쪽 서브트리 순회
        System.out.print(node.data); // 노드 방문
    }
}
