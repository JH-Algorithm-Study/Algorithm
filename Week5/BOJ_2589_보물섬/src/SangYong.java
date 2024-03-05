import java.util.*;
import java.io.*;

class SangYong {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        char[][] map = new char[R][C];
        
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int max = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                boolean[][] visited = new boolean[R][C];
                int[][] distance = new int[R][C];
                if(map[i][j] == 'L') {
                    int submax = bfs(i, j, map, visited, distance);
                    if(submax > max){
                        max = submax;
                    }
                }
            }
        }
        System.out.print(max);
    }

    public static int bfs(int i, int j, char[][] map, boolean[][] visited, int[][] distance) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        distance[i][j] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int d = 0; d < 4; d++) {
                int newR = curR + dr[d];
                int newC = curC + dc[d];

                if (newR >= 0 && newR < map.length && newC >= 0 && newC < map[0].length) {
                    if (!visited[newR][newC] && map[newR][newC] == 'L') {
                        visited[newR][newC] = true;
                        distance[newR][newC] = distance[curR][curC] + 1;
                        q.add(new int[]{newR, newC});
                    }
                }
            }
        }
        int max_dist = 0;
        for(int m=0; m<map.length; m++){
            for(int n=0; n<map[0].length; n++){
                if(distance[m][n] > max_dist)
                    max_dist = distance[m][n];
            }
        }
        return max_dist;
    }
}