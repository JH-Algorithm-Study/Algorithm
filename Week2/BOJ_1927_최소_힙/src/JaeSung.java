import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class JaeSung {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Long> pQ = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= N; tc++) {
            long x = Integer.parseInt(br.readLine());
            if(x > 0) {
                pQ.offer(x);
            } else {
                if(!pQ.isEmpty()) {
                    System.out.println(pQ.poll());
                } else {
                    System.out.println(0);
                }
            }
        }
    }
}