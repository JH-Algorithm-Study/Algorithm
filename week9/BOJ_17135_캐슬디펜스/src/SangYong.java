import java.util.*;

public class SangYong {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int D = sc.nextInt();
		
		int[][] map = new int[N][M];
		int[] di = {0, -1, 0};
		int[] dj = {-1, 0, 1};
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int max = 0;
		
		for(int i=0; i<(1<<M); i++) {
			List<Integer> cols = new ArrayList<>();
			for(int j=0; j<M; j++) {
				if((i&(1<<j)) != 0) {
					cols.add(j);
				}
			}
			
			if(cols.size() == 3) {
				int cnt = 0;
				
				int[][] copy = new int[N][M];
				
				for(int m=0; m<N; m++) {
					for(int n=0; n<M; n++) {
						copy[m][n] = map[m][n];
					}
				}
				
				while(true) {
					List<int[]> points = new ArrayList<>();
					for(Integer col : cols) {
						boolean[][] visited = new boolean[N][M];
						Queue<int[]> q = new LinkedList<>();
						
						q.offer(new int[] {N-1, col, 1});
						visited[N-1][col] = true;
						
						while(!q.isEmpty()) {
							int[] p = q.poll();
							if(copy[p[0]][p[1]] == 1) {
								points.add(new int[] {p[0], p[1]});
								break;
							}
							
							for(int k=0; k<3; k++) {
								int ni = p[0] + di[k];
								int nj = p[1] + dj[k];
								if(0<=ni && ni<N && 0<=nj && nj<M && !visited[ni][nj] && p[2]<D) {
									q.offer(new int[] {ni, nj, p[2]+1});
									visited[ni][nj] = true;
								}
							}
						}
					}
					for(int[] p : points) {
						if(copy[p[0]][p[1]] == 1) {
							copy[p[0]][p[1]] = 0;
							cnt++;
						}
					}
					
					for(int m=N-1; m>=1; m--) {
						for(int n=0; n<M; n++) {
							copy[m][n] = copy[m-1][n];
						}
					}
					Arrays.fill(copy[0], 0);
					
					int flag = 1;
					out : for(int m=0; m<N; m++) {
						for(int n=0; n<M; n++) {
							if(copy[m][n] == 1) {
								flag = 0;
								break out;
							}
						}
					}
					
					if(flag == 1)
						break;
				}
				
				if(max < cnt) {
					max = cnt;
				}
			}
		}
		System.out.println(max);		
	}
}