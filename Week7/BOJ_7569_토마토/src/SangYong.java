import java.io.*;
import java.util.*;

public class SangYong {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] tomato = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    tomato[h][n][m] = Integer.parseInt(st.nextToken());
                    if(tomato[h][n][m] == 1){
                        q.offer(new int[]{h, n, m});
                    }
                }
            }
        }
        int[] dh = {0, 0, 0, 0, 1, -1};
        int[] dn = {-1, 0, 1, 0, 0, 0};
        int[] dm = {0, 1, 0, -1, 0, 0};
        int max = 1;
        while(!q.isEmpty()){
            int[] p = q.poll();

            for(int i=0; i<6; i++){
                if(0 <= p[0]+dh[i] && p[0]+dh[i] <= H-1 && 0 <= p[1]+dn[i] && p[1]+dn[i] <= N-1 && 0 <= p[2]+dm[i] && p[2]+dm[i] <= M-1){
                    if(tomato[p[0]+dh[i]][p[1]+dn[i]][p[2]+dm[i]] == 0){
                        q.add(new int[]{p[0]+dh[i], p[1]+dn[i], p[2]+dm[i]});
                        tomato[p[0]+dh[i]][p[1]+dn[i]][p[2]+dm[i]] = tomato[p[0]][p[1]][p[2]] + 1;
                        if(max < tomato[p[0]+dh[i]][p[1]+dn[i]][p[2]+dm[i]])
                            max = tomato[p[0]+dh[i]][p[1]+dn[i]][p[2]+dm[i]];
                    }
                }
            }
        }
        
        int flag = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if(tomato[h][n][m] == 0){
                        flag = 1;
                    }
                }
            }
        }
        
        if(flag == 1)
            System.out.print(-1);
        else{
            System.out.print(max - 1);
        }
    }
}

---------------------------------------------------------------------------------
---------------------------------------------------------------------------------

import java.io.*;
import java.util.*;

public class SangYong {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] tomato = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    tomato[h][n][m] = Integer.parseInt(st.nextToken());
                    if (tomato[h][n][m] == 1) {
                        q.offer(new int[]{h, n, m});
                    }
                }
            }
        }
        
        int maxDays = bfs(tomato, q, H, N, M);
        
        int flag = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomato[h][n][m] == 0) {
                        flag = 1;
                    }
                }
            }
        }

        if (flag == 1)
            System.out.print(-1);
        else {
            System.out.print(maxDays - 1);
        }
    }

    public static int bfs(int[][][] tomato, Queue<int[]> q, int H, int N, int M) {
        int[] dh = {0, 0, 0, 0, 1, -1};
        int[] dn = {-1, 0, 1, 0, 0, 0};
        int[] dm = {0, 1, 0, -1, 0, 0};
        int maxDays = 1;
        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int i = 0; i < 6; i++) {
                if (0 <= p[0] + dh[i] && p[0] + dh[i] <= H - 1 && 0 <= p[1] + dn[i] && p[1] + dn[i] <= N - 1 && 0 <= p[2] + dm[i] && p[2] + dm[i] <= M - 1) {
                    if (tomato[p[0] + dh[i]][p[1] + dn[i]][p[2] + dm[i]] == 0) {
                        q.add(new int[]{p[0] + dh[i], p[1] + dn[i], p[2] + dm[i]});
                        tomato[p[0] + dh[i]][p[1] + dn[i]][p[2] + dm[i]] = tomato[p[0]][p[1]][p[2]] + 1;
                        if (maxDays < tomato[p[0] + dh[i]][p[1] + dn[i]][p[2] + dm[i]])
                            maxDays = tomato[p[0] + dh[i]][p[1] + dn[i]][p[2] + dm[i]];
                    }
                }
            }
        }
        return maxDays;
    }
}
