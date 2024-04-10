import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JaeSung {
    static class Enemies implements Comparable<Enemies> {
        int x, y, d;

        public Enemies(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Enemies o) {
            if (this.d == o.d) { // 거리 같을 때
                return this.y - o.y; // 더 왼쪽 값
            } else {
                return this.d - o.d; // 거리 더 작을 때
            }
        }

    }

    static int N, M, D, cnt, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0}; // 좌상우

    static int[] dc = {-1, 0, 1}; // 좌상우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        max = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // map 입력 종료

        // - 같은 거리일 시 왼쪽부터 제거 // dfs // 좌표 저장해두고 한 번에 지우기(겹쳤을 때 고려)
        // - D만큼 갔을 때 해당 위치에 1이 있을 때 0으로 변경
        // - 궁수 어디에 배치할 지 - 조합으로 모든 경우 탐색 || 비트마스킹

        action();
        System.out.println(max);
    }

    private static void action() {
        for (int i = 0; i < (1 << M); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) != 0) {
                    list.add(j);
                }
            } // 궁수가 위치할 column값 저장

            if (list.size() != 3) continue; // 궁수가 3명일 경우만 남도록 필터링
            // 궁수 배치 끝
            // 궁수의 각 자리마다 bfs 돌리기
            visited = new boolean[N][M];
            cnt = 0;
            for (int row = N; row > 0; row--) {
                List<Integer> listX = new ArrayList<>();
                List<Integer> listY = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    bfs(row, list.get(j), 0, 0, 0, listX, listY);
                }
                // bfs 돌면서 1을 만나면 List에 좌표값 저장
                // 1 싸이클 돌면 1을 0으로 바꾸기
                // 바꾼 1의 수만큼 cnt에 더하기
                for (int j = 0; j < listX.size(); j++) {
                    map[listX.get(j)][listY.get(j)] = 0;
                }
            }
            max = Math.max(cnt, max);
        }
    }

    public static void bfs(int curr_X, int curr_Y, int dis, int dir, int count, List<Integer> list_X, List<Integer> list_Y) {
        // base case
        if (dis > D || dir >= 3) return;
        if (isZero(map)) return;

        // recursive case
        for (int d = 0; d < 3; d++) {
            int currX = curr_X + dr[d];
            int currY = curr_Y + dc[d];

            if (currX >= 0 && currX < N && currY >= 0 && currY < M && !visited[currX][currY]) {
                visited[currX][currY] = true;
                if (map[currX][currY] == 1) {
                    list_X.add(currX); // 왼쪽 적부터 선택하는 로직 없음
                    list_Y.add(currY);
                    bfs(currX, currY, dis + 1, dir + 1, cnt + 1, list_X, list_Y);
                } else {
                    bfs(currX, currY, dis + 1, dir + 1, cnt, list_X, list_Y);
                }
            }
        }
    }

    public static boolean isZero(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) return false;
            }
        }
        return true;
    }

}