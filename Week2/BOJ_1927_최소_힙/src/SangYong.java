import java.util.*;
import java.io.*;

class SangYong {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(n==0){
                if(pq.isEmpty()){
                    System.out.println(0);
                }
                else{
                    System.out.println(pq.peek());
                    pq.poll();
                }
            }
            else{
                pq.offer(n);
            }
        }
    }
}