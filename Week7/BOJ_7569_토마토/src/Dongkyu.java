import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Dongkyu {

	static int[] dh = { 0, 0, 0, 0, -1, 1 };	// 위 아래 움직이는 delta
	static int[] dc = { 0, 0, -1, 1, 0, 0 };	// 열(column)이 움직이는 delta
	static int[] dr = { -1, 1, 0, 0, 0, 0 };	// 행(row)이 움직이는 delta
	
	static class Location {		// 위치 클래스 생성
		int h;	// 높이
		int r;	// 행
		int c;	// 열
		
		Location(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] tomato = new int[H][N][M];
		
		Queue<Location> ripeTomato = new LinkedList<>();	// BFS 사용을 위해 큐 ripeTomato 생성. 익은 토마토를 넣는 곳.
		
		int unripe = 0;	// 안 익은 토마토의 개수 담기 위한 변수
		int days = 0;	// 소요된 시간을 담기 위한 변수
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					tomato[h][r][c] = Integer.parseInt(st.nextToken());
					if (tomato[h][r][c] == 0) {		// 안 익은 토마토를 만나면
						unripe++;	// unripe의 개수 1 증가
					} else if (tomato[h][r][c] == 1) {	// 익은 토마토를 만나면
						ripeTomato.offer(new Location(h, r, c));	// 큐에 익은 토마토의 위치 넣기
					}
				}
			}
		}
		
		while (!ripeTomato.isEmpty() && unripe > 0) {	// 큐가 안 비어있고, 안 익은 토마토가 남아있다면
			int size = ripeTomato.size();
			
			for (int i = 0; i < size; i++) {	// 현재 대기열에 있는 모든 익은 토마토 반복
				Location l = ripeTomato.remove();	// 대기열에서 각 토마토 제거하고 토마토 상자를 나타는 3차원 배열에서 해당 위치 h, r, c를 검색
				int h = l.h;
				int r = l.r;
				int c = l.c;
				
				for (int d = 0; d < 6; d++) {
					int nh = h + dh[d];	// 높이
					int nr = r + dr[d];	// 행
					int nc = c + dc[d];	// 열
					
					// 경계조건 안에 들어있고 & 토마토가 안 익은 것일 때
					if (nh >= 0 && nh < H && nr >= 0 && nr < N && nc >=0 && nc < M && tomato[nh][nr][nc] == 0) {
						unripe--;	// 안 익은 토마토의 개수 1 감소
						tomato[nh][nr][nc] = 1;	// 토마토의 상태 익은 것으로 변경
						ripeTomato.offer(new Location(nh, nr, nc));	// 잘 익은 토마토에 추가
					}
				}
			}
			days++;
		}
		
		if (unripe == 0) {
			System.out.println(days);
		} else {
			System.out.println(-1);
		}
		
	}
}
