import java.util.*;

public class SangYong {
	
	static int R, C;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] cheese;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		cheese = new int[R][C];
		
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				cheese[i][j] = sc.nextInt();
				if(cheese[i][j] == 1)
					cnt++;
			}
		}
		
		int time = 0;
		int[][] copy = new int[R][C];
		
		while(true) {
			time++;
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					copy[i][j] = cheese[i][j];
				}
			}
			
			bfs(0, 0, copy);
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(copy[i][j] == 1) {
						for(int k=0; k<4; k++) {
							int ni = i + di[k];
							int nj = j + dj[k];
							if(0<=ni && ni<R && 0<=nj && nj<C && copy[ni][nj]==2) {
								cheese[i][j] = 0;
								break;
							}
						}
					}
				}
			}
			
			int count = 0;
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(cheese[i][j] != 0) {
						count++;
					}
				}
			}
			if(count == 0)
				break;
			else {
				cnt = count;
			}
		}
		System.out.println(time);
		System.out.println(cnt);
	}
	
	static void bfs(int x, int y, int[][] copy) {
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		visited[0][0] =  true;
		copy[0][0] = 2;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int i=0; i<4; i++) {
				int ni = p[0] + di[i];
				int nj = p[1] + dj[i];
				if(0<=ni && ni<R && 0<=nj && nj<C && copy[ni][nj]==0  && !visited[ni][nj]) {
					q.offer(new int[] {ni, nj});
					copy[ni][nj] = 2;
					visited[ni][nj] = true;
				}
			}
		}
	}
}