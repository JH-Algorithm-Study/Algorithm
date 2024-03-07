package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JaeSung {
    static int R;
    static int C;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌
    static int[] dc = {0, 1, 0, -1}; // 상우하좌
    static boolean[][] visited;
    static int max_d;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 맵 Row size
        C = Integer.parseInt(st.nextToken()); // 맵 Column size

        map = new char[R][C]; // 맵 정보 집어넣기
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
            }
        }

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == 'L') {
                    bfs(r, c);
                }
            }
        }
        System.out.println(max_d);
    }


    static void bfs(int start_r, int start_c) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start_r, start_c, 0}); // 시작좌표(r,c)와 거리를 큐에 추가

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int distance = curr[2];

            if (map[r][c] == 'L') {
                max_d = Math.max(max_d, distance);
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == 'L') {
                    queue.add(new int[]{nr, nc, distance + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }
}