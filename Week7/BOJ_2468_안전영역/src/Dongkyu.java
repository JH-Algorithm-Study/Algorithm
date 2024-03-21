import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dongkyu {
	
	static int N;
	static int[][] arr;
	static boolean[][] visited;		// DFS 중에 방문한 셀을 추적하기 위한 2차원 배열
	static int[] dr = { -1, 1, 0, 0 };		// 상 하 좌 우 delta
	static int[] dc = { 0, 0, -1, 1 };		// 상동
	
	public static void dfs(int r, int c, int height) {	// dfs 메서드
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 경계조건, visited[nr][nc]는 false, 그리고 visited[nr][nc]가 물의 높이(height)보다 높은 경우
			if (nr >=0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && arr[nr][nc] > height) {
				dfs(nr, nc, height);	// 재귀 호출. 이웃 셀에서 DFS 순회를 계속하여 순회 조건을 충족하는 추가 셀을 탐색
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE;	// 2차원 배열 중 가장 큰 값을 위한 max
		int min = Integer.MAX_VALUE;	// 2차원 배열 중 가장 작은 값을 위한 min
		
		arr = new int[N][N];
		for (int r = 0; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < arr[r].length; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if (max < arr[r][c]) {
					max = arr[r][c];
				}
				if (min > arr[r][c]) {
					min = arr[r][c];
				}
			}
		}
		
		int maxCnt = 1;		// 안전한 영역의 수가 1. 그리드에 안전 영역이 하나 이상 있다는 가정에서 근거.
		
		// rain 은 장마철 비가 온 정도를 의미
		for (int rain = min; rain < max; rain++) {
			
			// visited 배열 새로 생성
			visited = new boolean[N][N];
			
			int cnt = 0;	// 해당 경우에서 안전 영역의 수를 카운트 하기 위한 cnt 변수
			
			for (int r = 0; r < arr.length; r++) {
				for (int c = 0; c < arr[r].length; c++) {
					if (!visited[r][c] && arr[r][c] > rain) {
						dfs(r, c, rain);	// 현재 강수량보다 높이가 높은 각 미방문 셀에 대해 DFS 메서드 적용하여 안전 영역 탐색
						cnt++;	// cnt 1 증가
					}
				}
			}
			
			maxCnt = Math.max(maxCnt, cnt);		// 안전 영역의 수가 가장 많은 경우를 구하기 위함.
		}
		
		System.out.println(maxCnt);

	}
}
