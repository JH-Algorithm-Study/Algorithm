import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class YoonHa {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()); // 행
        int C = Integer.parseInt(st.nextToken()); // 열

        int[][] arr = new int[R][C];

        int[] dr = { 0, 1, 0, -1 };
        int[] dc = { 1, 0, -1, 0 };
        Queue<Integer> queue = new LinkedList<>();
        int max = 0;

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                if (ch == 'L') { // L이면 1, W면 0
                    arr[i][j] = 1;
                }
            }
        }
//        System.out.println(Arrays.deepToString(arr));

        // !visited 이면서 L이면 사방탐색
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(arr[i][j] == 1) {
                    boolean[][] visited = new boolean[R][C];
                    int[][] distance = new int[R][C];
                    visited[i][j] = true;

                    queue.offer(i);
                    queue.offer(j);

                    while (!queue.isEmpty()) {
                        int x = queue.poll();
                        int y = queue.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nr = x + dr[dir];
                            int nc = y + dc[dir];

                            if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 1 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                distance[nr][nc] = distance[x][y] + 1;

                                queue.offer(nr);
                                queue.offer(nc);
                            }
                        }
                    }

                    // 최대값 찾아서 출력
                    for (int n = 0; n < R; n++) {
                        for (int m = 0; m < C; m++) {
                            if (distance[n][m] > max) {
                                max = distance[n][m];
                            }
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}