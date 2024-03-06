import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Dongkyu {	// 다 못 풀었음... 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// delta
		int[] dr = { -1, 1, 0, 0 };	// 상 하 좌 우
		int[] dc = { 0, 0, -1, 1 };	// 상동
		
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int column = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[row][column];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = st.nextToken().charAt(c);
			}
		}

		int max = 0;
		
		char[][] copied = new char[row][column];	// 원본 배열 유지를 위해 copied 배열 생성 
		for (int r = 0; r < copied.length; r++) {
			for (int c = 0; c < copied[r].length; c++) {
				copied[r][c] = map[r][c];
			}
		}
		
		
		// 방문한 곳은 배제하기 위해 visited 배열 생성
		boolean[][] visited = new boolean[row][column];
		
		int cnt = 0;	// dfs를 위한 카운트 
		
		for (int r = 0; r < copied.length; r++) {
			for (int c = 0; c < copied[r].length; c++) {
				if (copied[r][c] == 'L' && !visited[r][c]) {	// 땅인 곳부터 시작하기 위해. 그리고 visited는 false인 곳만 방문 
					visited[r][c] = true;	// 방문처리 
					cnt++;	// 방문했으니 카운트 증가..? 
					
					// dfs를 위한 stack 컬렉션 생성
					Stack<Integer> stack = new Stack<>();
					stack.add(r);
					stack.add(c);
					
					while (!stack.isEmpty()) {
						int tmpR = stack.pop();	// 이 부분 잘 모르겠다.. 
						int tmpC = stack.pop();
						
						for (int d = 0; d < 4; d++) {
							int nr = tmpR + dr[d];
							int nc = tmpC + dc[d];
							
							if (nr >= 0 && nr < row && nc >= 0 && nc < column && copied[nr][nc] == 'L' && !visited[nr][nc]) {
								visited[nr][nc] = true;
								stack.add(nr);
								stack.add(nc);
							}
						}
					}
					
				}
			}
		}
	}
}
