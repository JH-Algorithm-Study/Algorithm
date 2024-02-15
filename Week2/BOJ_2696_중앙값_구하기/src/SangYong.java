import java.util.*;
import java.io.*;

class SangYong {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            
            int M = Integer.parseInt(br.readLine());
            
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<(int)Math.ceil(M/10.0); i++){
                sb.append(br.readLine()).append(" ");
            }
            
            StringTokenizer st = new StringTokenizer(sb.toString());
            System.out.println((M+1)/2);
            int count = 0;
            for(int i=0; i<M; i++){
                int num = Integer.parseInt(st.nextToken());

                if(maxHeap.size() == minHeap.size()){
                    if(maxHeap.size() == 0){
                        maxHeap.offer(num);
                        System.out.print(maxHeap.peek() + " ");
                    }
                    else{
                        if(num > minHeap.peek()){
                            minHeap.offer(num);
                            maxHeap.offer(minHeap.poll());
                            System.out.print(maxHeap.peek() + " ");
                        }
                        else{
                            maxHeap.offer(num);
                            System.out.print(maxHeap.peek() + " ");
                        }
                    }
                    count++;
                }
                else if(maxHeap.size() > minHeap.size()){
                    if(num >= maxHeap.peek()){
                        minHeap.offer(num);
                    }
                    else{
                        maxHeap.offer(num);
                        minHeap.offer(maxHeap.poll());
                    }
                }
                if(count == 10){
                    System.out.println();
                    count = 0;
                }
            }
            System.out.println();
        }
    }
}