import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JaeSung {
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean [][] visited;
    static char[][] map;
    static ArrayList<Node> list; // 같은 색 푸요 저장할 리스트
    static int row = 12;
    static int col = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new char[row][col];
        int cnt = 0;
        for(int i = 0; i < row; i++) {
            String line = br.readLine();
            for(int j = 0; j < col; j++) {
                map[i][j] = line.charAt(j);
            }
        } // puyo map 입력 완료

        while(true) {
            boolean isFinished = true; // puyo 끝났는지 확인
            visited = new boolean[row][col];
            for(int r = 0; r < row; r++) {
                for(int c = 0; c < col; c++) {
                    if(map[r][c] != '.') {
                        list = new ArrayList<>();
                        bfs(map[r][c], r, c);

                        if(list.size() >= 4) {
                            isFinished = false;
                            for(int i = 0; i < list.size(); i++) {
                                map[list.get(i).x][list.get(i).y] = '.'; // puyo 제거
                            }
                        }
                    }
                }
            }
            if(isFinished)
                break;
            fallPuyos();
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void bfs(char c, int x, int y) {
        Queue<Node> q = new LinkedList<>();
        list.add(new Node(x, y));
        q.offer(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for(int d = 0; d < 4; d++) {
                int nx = curr.x + dr[d];
                int ny = curr.y + dc[d];

                if(nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny] && map[nx][ny] == c) {
                    visited[nx][ny] = true;
                    list.add(new Node(nx, ny));
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void fallPuyos() {
        for(int c = 0; c < col; c++) {
            int idx = row - 1; // 아래에서부터 시작
            for(int r = row - 1; r >= 0; r--) { // 아래에서 위로 이동
                if(map[r][c] != '.') { // puyo가 있을 때
                    char temp = map[r][c];
                    map[r][c] = '.';
                    map[idx][c] = temp; // 위아래 바꾸기
                    idx--; // 다음 낙하 위치 갱신
                }
            }
        }
    }
}
