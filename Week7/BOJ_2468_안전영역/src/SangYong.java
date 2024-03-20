import java.util.*;

class Main {
    public static void SangYong(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] map = new int[N][N];

        int min = 101; int max = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] > max)
                    max = map[i][j];
                if(map[i][j] < min)
                    min = map[i][j];
            }
        }
        
        int result = 1;
        for(int x=min; x<max; x++){
            int count = 0;
            boolean[][] visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j] <= x){
                        visited[i][j] = true;
                    }
                }
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!visited[i][j]){
                        dfs(i, j, visited);
                        count++;
                    }
                }
            }
            if(count > result)
                result = count;
        }
        System.out.print(result);
    }

    static void dfs(int i, int j, boolean[][] visited){
        visited[i][j] = true;

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        for(int x=0; x<4; x++){
            int ni = i + di[x];
            int nj = j + dj[x];
            if(ni >= 0 && ni < visited.length && nj >= 0 && nj < visited[0].length && !visited[ni][nj]){
                dfs(ni, nj, visited);
            }
        }
    }
}