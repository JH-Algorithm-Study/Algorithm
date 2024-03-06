import java.io.*;
import java.util.*;

public class Main {
	static int my, mx;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] arr, tmpArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		my = Integer.parseInt(st.nextToken());
		mx = Integer.parseInt(st.nextToken());
		
		arr = new int[my][mx];
		for (int i = 0; i < my; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mx; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		outerCheese(0, 0);
		
		int time = 0;
		int res = 0;
		while (true) {
			int tmp = 0;
			tmpArr = new int[my][mx];
			for (int i = 0; i < my; i++) { // cheese의 배열을 tmpArr로 복사
				for (int j = 0; j < mx; j++) {
					tmpArr[i][j] = arr[i][j];
					if (arr[i][j] == 1) {
						tmp ++;
					}
				}
			}
			
			if (tmp == 0) break;
			else res = tmp;
			
			for (int i = 0; i < my; i++) { // 치즈인 경우 체크 
				for (int j = 0; j < mx; j++) {
					if (arr[i][j] == 1) {
						melting(i, j);
					}
				}
			}
			
			for (int i = 0; i < my; i++) { //  tmpArr의 배열을 cheese로 복사
				for (int j = 0; j < mx; j++) {
					arr[i][j] = tmpArr[i][j];
				}
			}
			
			time ++;
		}
		System.out.println(time);
		System.out.println(res);
	}
	
	public static void outerCheese(int y, int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(y);
		queue.add(x);
		
		while (!queue.isEmpty()) {
			int qy = queue.poll();
			int qx = queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int ny = qy + dy[dir];
				int nx = qx + dx[dir];
				
				if (ny >= 0 && ny < my && nx >= 0 && nx < mx && arr[ny][nx] == 0) {
					arr[ny][nx] = 2;
					queue.add(ny);
					queue.add(nx);
				}
			}
		}
	}
	
	public static void melting(int y, int x) {
		for (int dir = 0; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (ny >= 0 && ny < my && nx >= 0 && nx < mx && arr[ny][nx] == 2) {
				tmpArr[y][x] = 2;
				innerCheese(y, x);
				break;
			}
		}
	}
	
	public static void innerCheese(int y, int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(y);
		queue.add(x);
		
		while (!queue.isEmpty()) {
			int qy = queue.poll();
			int qx = queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int ny = qy + dy[dir];
				int nx = qx + dx[dir];
				
				if (ny >= 0 && ny < my && nx >= 0 && nx < mx && tmpArr[ny][nx] == 0) {
					tmpArr[ny][nx] = 2; // 내기가 외기가 된 경우
					queue.add(ny);
					queue.add(nx);
				}
			}
		}
	}
}
