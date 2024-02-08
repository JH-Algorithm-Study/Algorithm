import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JaeSung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // queue에 1 ~ N까지 차례대로 값 삽입
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        
        // queue에 뺄 값이 있을 때까지만 반복
        while(queue.size() > 1) {
            // queue에서 빼서 value에 담고 다시 queue에 삽입
            for(int i = 1; i < K; i++) {
                int value = queue.poll();
                queue.offer(value);
            }
            sb.append(queue.poll()).append(", ");
        }
        // queue에 마지막에 남은 값 poll 하고 > append
        sb.append(queue.poll()).append('>');
        // sb 출력
        System.out.println(sb);
    }
}