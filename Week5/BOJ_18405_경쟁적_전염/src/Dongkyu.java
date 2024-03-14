
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dongkyu {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		
		boolean[][] visited = new boolean[N][N]; 
		
		int leftCnt = N * N;	// visited[r][c]가 false인 것들의 개수
		
		for (int r = 0; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < arr[r].length; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if (arr[r][c] != 0) {
					visited[r][c] = true;
					leftCnt--;	// visited[r][c]의 값이 true이면 남아있는 개수를 줄이는 것
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		// delta 배열
		int[] dr = { -1, 1, 0, 0 };		// 상 하 좌 우
		int[] dc = { 0, 0, -1, 1 };
		
		
		
		for (int i = 0; i < S; i++) {
			
			if (leftCnt <= 0) {
				break;	// 만약 leftCnt의 값이 0 이하라면(즉 0이면) 그냥 반복문 종료 => 시간 초과를 방지하기 위해 매우 중요한 코드
			}
			
			for (int r = 0; r < arr.length; r++) {
				for (int c = 0; c < arr[r].length; c++) {
					if (visited[r][c] == true) {
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] == false) {	// 경계조건 안에 들어가고, visited[nr][nc]의 값이 false일 때에만
								if (arr[nr][nc] == 0 || arr[nr][nc] >= arr[r][c]) {		// 그리고 해당 값이 0이 아니거나, 기존에 있는 값보다 크거나 같다면
									arr[nr][nc] = arr[r][c];	// 값 변경
								}
							}
						}
					}
				}
			}
			
			for (int r = 0; r < arr.length; r++) {
				for (int c = 0; c < arr[r].length; c++) {
					if (arr[r][c] != 0 && visited[r][c] == false) {
						visited[r][c] = true;	// visited 배열 갱신
						leftCnt--;	// leftCnt의 값에 반영
					}
				}
			}
		}
		
		System.out.println(arr[X - 1][Y - 1]);
	}
}
