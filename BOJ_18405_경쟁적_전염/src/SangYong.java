import java.util.*;

class SangYong {

    static int N, K, S, X, Y;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        int[][] map = new int[N][N];
        int[][] copy = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0){
                    map[i][j] = K+1;
                }
            }
        }
        S = sc.nextInt();
        X = sc.nextInt();
        Y = sc.nextInt();


        for(int s=1; s<=S; s++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    copy[i][j] = map[i][j];
                }
            }
            
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j] == K+1){
                        for(int k=0; k<4; k++){
                            int ni = i+di[k];
                            int nj = j+dj[k];
                            if(0<=ni && ni<N && 0<=nj && nj<N && copy[ni][nj] != K+1){
                                map[i][j] = Math.min(map[i][j], map[ni][nj]);
                            }
                        }
                    }
                }
            }
        }
        if(map[X-1][Y-1] == K+1)
            System.out.print(0);
        else
            System.out.print(map[X-1][Y-1]);
    }
}