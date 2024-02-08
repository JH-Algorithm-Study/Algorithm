import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class YoonHa {
    public static void main(String[] args) throws Exception {
        Queue<Integer> queue = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        
//        System.out.println(queue);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        
        while(queue.size() > 1) {
            for(int i = 0; i < K - 1; i++) {
                int value = queue.poll();
                queue.offer(value);
            }
            sb.append(queue.poll()).append(", ");
        }
        
        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }
}