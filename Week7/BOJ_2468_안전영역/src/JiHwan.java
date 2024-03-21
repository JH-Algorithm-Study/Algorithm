import java.io.*;
import java.util.*;

public class Main {
	
	private static int safteyRegion, N;
	private static boolean[][] visited, safteyMap;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int maxInt = 0;
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (maxInt < arr[i][j]) maxInt = arr[i][j];
			}
		}
		
		int maxSR = 0; // 안전 영역의 최대 개수
		for (int m = 1; m <= maxInt; m++) {
			safteyMap = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] >= m) {
						safteyMap[i][j] = true; // 안전 영역
					}
				}
			}
			
			safteyRegion = 0; // 안전 영역의 개수
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (safteyMap[i][j] && !visited[i][j]) {
						visited[i][j] = true;
						safteyRegion ++;
						dfs(i, j);
					}
				}
			}
			if (maxSR <= safteyRegion) maxSR = safteyRegion;
		}
		System.out.println(maxSR);
	}
	
	public static void dfs(int y, int x) {
		for (int dir = 0; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (ny >= 0 && ny < N && nx >= 0 && nx < N && safteyMap[ny][nx] && !visited[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny, nx);
			}
		}
	}
}
