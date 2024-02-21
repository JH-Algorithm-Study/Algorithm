import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // row
        int c = Integer.parseInt(st.nextToken()); // col
        int T = Integer.parseInt(st.nextToken()); // T초 후 방의 미세먼지

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] arr = new int[r][c]; // 미세먼지 지도
        int[] airCleaner = new int[2];

        for (int i = 0; i < r; i++) { // 방에 미세먼지 arr에 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    airCleaner[1] = i;
                }
            }
        }
        airCleaner[0] = airCleaner[1] - 1; // 윗 칸이므로

        for (int time = 0; time < T; time++) {
            int[][] tmp = new int[r][c]; // 미세먼지 임시 저장 지도
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (arr[i][j] > 0) {
                        int k = arr[i][j] / 5;
                        for (int dir = 0; dir < 4; dir++) {
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];

                            if (ny >= 0 && ny < r && nx >= 0 && nx < c && arr[ny][nx] != -1) {
                                tmp[ny][nx] += k;
                                tmp[i][j] -= k;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    arr[i][j] += tmp[i][j];
                }
            }
            
            // 공기청정기 위쪽 순환
            int limit = 3;
            int y = airCleaner[0] + dy[limit % 4];
            int x = dx[limit % 4];
            arr[y][x] = 0;

            while (arr[y][x] != -1) {
                int ay = y + dy[limit % 4];
                int ax = x + dx[limit % 4];

                if (ay >= 0 && ay <= airCleaner[0] && ax >= 0 && ax < c) {
                    if (arr[ay][ax] >= 1) {
                        arr[y][x] = arr[ay][ax];
                        arr[ay][ax] = 0;
                    }
                    y = ay;
                    x = ax;

                } else {
                    limit++;
                }
            }

            // 공기청정기 아래쪽 순환
            limit = 5;
            y = airCleaner[1] + dy[limit % 4];
            x = dx[limit % 4];
            arr[y][x] = 0;

            while (arr[y][x] != -1) {
                int ay = y + dy[limit % 4];
                int ax = x + dx[limit % 4];

                if (ay >= airCleaner[1] && ay < r && ax >= 0 && ax < c) {
                    if (arr[ay][ax] >= 1) {
                        arr[y][x] = arr[ay][ax];
                        arr[ay][ax] = 0;
                    }
                    y = ay;
                    x = ax;

                } else {
                    limit--;
                }
            }
        }

        int res = 2;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res += arr[i][j];
            }
        }
        System.out.println(res);
    }
}