import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Dongkyu {
    
    // 크루스칼 알고리즘 사용
    static class Edge implements Comparable<Edge> {     // Edge 클래스 생성 및 정렬 위해 Comparable implements
        int A;  // 도시 시작점 (왼쪽)
        int B;  // 도시 끝점 (오른쪽)
        int W;  // 도로 건설 비용 (가중치)
        
        public Edge(int A, int B, int W) {  // 생성자
            this.A = A;
            this.B = B;
            this.W = W;
        }

        @Override
        public int compareTo(Edge o) {  // Comparable을 implements 했기 때문에 무조건 정의해줘야 한다.
            return Integer.compare(this.W, o.W);
        }
    }
    
    static int[] rep;   // 대표 저장할 배열 static으로 빼둔다.
    
    static void makeSet(int x) {
        rep[x] = x;
    }
    
    static void union(int x, int y) {
        rep[findSet(y)] = findSet(x);
    }
    
    static int findSet(int x) {
        if (x != rep[x]) {
            rep[x] = findSet(rep[x]);
        }    
        return rep[x];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Edge> edges = new ArrayList<>();   // 배열 리스트 사용
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(a, b, c));
        }
        
        rep = new int[N + 1];   // 대표 배열 초기화
        for (int i = 1; i <= N; i++) {
            makeSet(i);
        }
        
        Collections.sort(edges);    // 가중치 기준으로 오름차순 정렬
        
        long totalOriginalCost = 0; // 전체 다 연결했을 때 가격
        for (Edge edge : edges) {
            totalOriginalCost += edge.W;
        }

        long totalMinimalCost = 0;  // 최솟값
        int edgeCount = 0;
        
        for (Edge edge : edges) {
            
            if (findSet(edge.A) != findSet(edge.B)) {   // 서로를 대표하는 값이 다를 때
                union(edge.A, edge.B);  // union 메서드 적용
                totalMinimalCost += edge.W; // 그리고 최솟값에 가중치(비용) 추가
                edgeCount++;    // 선의 개수 1 증가
                
                
                if (edgeCount == N - 1) {   // 만약 전체 연결한 선의 개수가 도시의 수 - 1 에 도달하면
                    break;  // 스탑
                }
            }
        }

        // int 형으로 하면 틀린다. long 형으로 설정해야 한다.
        long costSavings = totalOriginalCost - totalMinimalCost;    // 요구하는 답이 '절약한 값'이기 때문

        
        if (edgeCount < N - 1) {    // 필수 조건 맞추지 못하면
            System.out.println(-1); // -1 출력
        } else {
            System.out.println(costSavings);
        }
    }
}