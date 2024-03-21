import java.io.*;
import java.util.*;

public class Main {
	
	private static int M, N, H;
	private static int[][][] box;
	private static int[] dz = {-1, 1, 0, 0, 0, 0};
	private static int[] dy = {0, 0, -1, 1, 0, 0};
	private static int[] dx = {0, 0, 0, 0, -1, 1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        Queue<int[]> queue = new LinkedList<>();
        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        queue.add(new int[]{h, n, m});
                    }
                }
            }
        }
        
        bfs(queue);
        	
        int days = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    days = Math.max(days, box[h][n][m]);
                }
            }
        }
        
        System.out.println(days - 1); // 초기값이 1이므로 최종 값에서 1을 빼줌
    }
    
    public static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int z = pos[0], y = pos[1], x = pos[2];
            for (int dir = 0; dir < 6; dir++) {
                int nz = z + dz[dir], ny = y + dy[dir], nx = x + dx[dir];
                
                if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M && box[nz][ny][nx] == 0) {
                    box[nz][ny][nx] = box[z][y][x] + 1;
                    queue.add(new int[]{nz, ny, nx});
                }
            }
        }
    }
} 