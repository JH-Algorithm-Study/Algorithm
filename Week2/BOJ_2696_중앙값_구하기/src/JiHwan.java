import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int M = Integer.parseInt(br.readLine());
            int[] arr = new int[M];
            int idx = 0;
            while (M > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    arr[idx++] = Integer.parseInt(st.nextToken());
                    M--;
                }
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 작은 수 넣는 통
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 큰 수 넣는 통
            ArrayList<Integer> res = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {
                if (maxHeap.isEmpty() || arr[i] < maxHeap.peek()) {
                    maxHeap.add(arr[i]);
                } else {
                    minHeap.add(arr[i]);
                }

                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.add(maxHeap.poll());
                } else if (minHeap.size() > maxHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }

                if (i % 2 == 0) {
                    res.add(maxHeap.peek());
                }
            }

            sb.append(res.size()).append("\n");
            for (int i = 0; i < res.size(); i++) {
                sb.append(res.get(i));
                if ((i + 1) % 10 == 0) sb.append("\n");
                else if (i < res.size() - 1) sb.append(" ");
            }
            if (res.size() % 10 != 0) sb.append("\n");
        }
        System.out.print(sb);
    }
}