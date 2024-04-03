import java.util.*;
import java.io.*;

public class JaeSung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int max_h = 0;
        int max_cnt = 0;

        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] > max_h) max_h = map[r][c]; // 최대 높이 정보 저장
            }
        }

        // 방문 여부를 확인하는 배열
        boolean[][] visited;

        // 델타 배열 설정
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 홍수 높이별로 안전 영역 탐색
        for (int h = 0; h <= max_h; h++) {
            visited = new boolean[N][N];
            int cnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c] && map[r][c] > h) {
                        Stack<int[]> stack = new Stack<>();
                        stack.push(new int[]{r, c});
                        visited[r][c] = true;

                        while (!stack.isEmpty()) {
                            int[] current = stack.pop();
                            int cr = current[0];
                            int cc = current[1];

                            for (int d = 0; d < 4; d++) {
                                int nr = cr + dr[d];
                                int nc = cc + dc[d];

                                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] > h) {
                                    stack.push(new int[]{nr, nc});
                                    visited[nr][nc] = true;
                                }
                            }
                        }
                        cnt++;
                    }
                }
            }
            max_cnt = Math.max(max_cnt, cnt);
        }

        System.out.println(max_cnt);
    }
}
