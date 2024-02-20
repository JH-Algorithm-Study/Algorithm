import java.util.*;

class SangYong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int T = sc.nextInt();

        int[][] dust = new int[R][C];
        

        int pos = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                dust[i][j] = sc.nextInt();
                if(dust[i][j] == -1)
                    pos = i;
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int t=0; t<T; t++) {
            int[][] emit = new int[R][C];
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(dust[i][j] > 0){
                        int cnt = 0;
                        for(int k=0; k<4; k++){
                            if(0<=i+dr[k]&&i+dr[k]<R&&0<=j+dc[k]&&j+dc[k]<C&&dust[i+dr[k]][j+dc[k]] != -1){
                                emit[i+dr[k]][j+dc[k]] += dust[i][j] / 5;
                                cnt++;
                            }
                        }
                        dust[i][j] -= cnt * (dust[i][j] / 5);
                    }
                }
            }
    
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    dust[i][j] += emit[i][j];
                }
            }
    
            int top = pos-1;
            for (int i=top-1; i>0; i--)
                dust[i][0] = dust[i-1][0];
            for (int j=0; j<C-1; j++)
                dust[0][j] = dust[0][j+1];
            for (int i=0; i<top; i++)
                dust[i][C-1] = dust[i+1][C-1];
            for (int j=C-1; j>1; j--)
                dust[top][j] = dust[top][j-1];
            dust[top][1] = 0;
     
            int bottom = pos; 
            for (int i=bottom+1; i<R-1; i++)
                dust[i][0] = dust[i+1][0];
            for (int j=0; j<C-1; j++)
                dust[R-1][j] = dust[R-1][j+1];
            for (int i = R-1; i>bottom; i--)
                dust[i][C-1] = dust[i-1][C-1];
            for (int j=C-1; j>1; j--)
                dust[bottom][j] = dust[bottom][j-1];
            dust[bottom][1] = 0;
        }

        int sum = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(dust[i][j] > 0)
                    sum += dust[i][j];
            }
        }
        System.out.print(sum);
    }
}