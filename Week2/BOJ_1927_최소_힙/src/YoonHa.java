import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class YoonHa {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // 연산의 개수

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 큐 선언

        // 연산 수행
        for(int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine()); // 연산할 값 입력 받기

            if(x == 0) { // x가 0인 경우, 최솟값을 출력
                if(pq.isEmpty()) { // 우선순위 큐가 비어있으면 0 출력
                    sb.append(0).append("\n");
                } else { // 우선순위 큐에서 최솟값을 꺼내어 출력
                    sb.append(pq.poll()).append("\n");
                }
            } else { // x가 0이 아닌 경우, 우선순위 큐에 값 추가
                pq.offer(x);
            }
        }

        // 연산 결과 출력
        System.out.println(sb);
    }
}
