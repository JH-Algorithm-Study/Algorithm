import java.util.*;
import java.io.*;

public class JaeSung {
    static int L, W, H, day; // length, width, height
    static int[][][] box;
    static boolean[][][] visited;
    static int[] dx = {0, 1, 0, -1, 0, 0}; // 앞 우 뒤 좌 상 하
    static int[] dy = {-1, 0, 1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][W][L];

        // box setting
        for(int h = 0; h < H; h++) { // h가 제일 마지막에 바뀐다.
            for(int w = 0; w < W; w++) {
                st = new StringTokenizer(br.readLine());
                for(int l = 0; l < L; l++) {
                    box[h][w][l] = Integer.parseInt(st.nextToken());
                }
            }
        }

//        while()
        visited = new boolean[H][W][L];
        for(int h = 0; h < H; h++) {
            for(int w = 0; w < W; w++) {
                for(int l = 0; l < L; l++) {
                    if(!visited[h][w][l] && box[h][w][l] == 1) {
                        Queue<Integer> queue = new LinkedList<>();
                        queue.offer(h);
                        queue.offer(w);
                        queue.offer(l);
                        visited[h][w][l] = true;

                        while(!queue.isEmpty()) {
                            int z = queue.poll();
                            int y = queue.poll();
                            int x = queue.poll();

                            for(int d = 0; d < 6; d++) {
                                int nz = z + dz[d];
                                int ny = y + dy[d];
                                int nx = x + dx[d];

                                if(nz >= 0 && nz < H && ny >= 0 && ny < W && nx >= 0 && nx < L
                                        && !visited[nz][ny][nx] && box[nz][ny][nx] == 1) {
                                    queue.offer(nz);
                                    queue.offer(ny);
                                    queue.offer(nx);
                                    visited[nz][ny][nx] = true;
                                }
                            }
                        }
                    }
                    day++;
                }
            }
        }



    }
}
