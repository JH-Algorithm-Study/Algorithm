import java.util.*;
import java.io.*;

public class SangYong {
    static int[] parent;
    static PriorityQueue<int[]> edges = new PriorityQueue<>((a, b) -> {
        return a[2] - b[2];
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        long init = 0;
        long add = 0;
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new int[] {start ,dest, weight});
            init += weight;
        }

        int cnt = 0;
        while (!edges.isEmpty()) {
            int[] p = edges.poll();
            int start = p[0];
            int dest = p[1];
            int weight = p[2];

            if (find(start) != find(dest)) {
                add += weight;
                union(start, dest);
                cnt++;
                if (cnt == N-1) 
                    break;
            }
        }

        if (cnt != N-1) 
            System.out.println(-1);
        else 
            System.out.println(init - add);
    }

    public static void union(int a, int b){
        int aP = find(a);
        int bP = find(b);

        if (aP < bP) {
            parent[bP] = aP;
        } else if (bP < aP) {
            parent[aP] = bP;
        }
    }

    public static int find(int n) {
        if (parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }
}