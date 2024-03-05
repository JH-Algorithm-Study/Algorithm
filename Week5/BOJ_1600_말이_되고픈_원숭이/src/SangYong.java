import java.util.*;

public class SangYong {   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int K = sc.nextInt();
        int W = sc.nextInt();
        int H = sc.nextInt();
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[H][W][K+1];
        
        int[][] map = new int[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        q.offer(new int[] {0, 0, 0, 0});
        visited[0][0][0] = true;
        
        int[] hi = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] hj = {1, 2, 2, 1, -1, -2, -2, -1};
        
        int[] mi = {-1, 0, 1, 0};
        int[] mj = {0, 1, 0, -1};
        
        while(!q.isEmpty()) {
            int[] p = q.poll();
            int r = p[0]; int c = p[1]; int cnt = p[2]; int t = p[3];
            
            if(r==H-1 && c==W-1) {
                System.out.println(cnt);
                return;
            }
            
            for(int i=0; i<4; i++) {
                int ni = r + mi[i];
                int nj = c + mj[i];
                if(0<=ni && ni<H && 0<=nj && nj<W && !visited[ni][nj][t] && map[ni][nj] != 1) {
                    q.offer(new int[] {ni, nj, cnt+1, t});
                    visited[ni][nj][t] = true;
                }
            }
            
            if(t<K) {
                for(int i=0; i<8; i++) {
                    int ni = r + hi[i];
                    int nj = c + hj[i];
                    if(0<=ni && ni<H && 0<=nj && nj<W && !visited[ni][nj][t+1] && map[ni][nj] != 1) {
                        q.offer(new int[] {ni, nj, cnt+1, t+1});
                        visited[ni][nj][t+1] = true;
                    }
                }
            }
        }
        
        System.out.println(-1);
    }
}
