import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int inputNum;
        for (int i = 0; i < n; i++) {
            inputNum = Integer.parseInt(br.readLine());
            if (inputNum == 0) {
                if (maxHeap.isEmpty()) sb.append(0).append("\n");
                else sb.append(maxHeap.poll()).append("\n");
            } else maxHeap.add(inputNum);
        }
        System.out.print(sb.toString().trim());
    }
}