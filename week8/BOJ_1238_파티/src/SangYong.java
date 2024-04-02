import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int v;
    int w;
    
    Node(int v, int w){
        this.v = v;
        this.w = w;
    }

    public int compareTo(Node o) {
        return this.w - o.w;
    }
}

public class SangYong {
    static int dist[][];
    static ArrayList<Node>list[];
    
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
        
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken()); 
            list[start].add(new Node(dest,w));
        }

        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        for(int i=1; i<=N; i++)
            djistra(i, N);
        
        int max = 0;
        for(int i=1; i<=N; i++)
            max = Math.max(max, dist[i][X]+dist[X][i]);
        
        System.out.println(max);
    }
    
    static void djistra(int start, int N) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean visited [] = new boolean[N+1];
        
        pq.add(new Node(start,0));
        dist[start][start] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.v] = true;
            for(Node cur : list[node.v]) {
                if(!visited[cur.v] && dist[start][cur.v] > dist[start][node.v] + cur.w) {
                    dist[start][cur.v] = dist[start][node.v] + cur.w;
                    pq.add(new Node(cur.v, dist[start][cur.v]));
                }
            }
        }
    }
}