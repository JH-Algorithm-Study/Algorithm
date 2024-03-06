import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int my = Integer.parseInt(st.nextToken());
		int mx = Integer.parseInt(st.nextToken());
		
		int[] dy = {0, 1, 0, -1};
		int[] dx = {1, 0, -1, 0};
		
		int maxInt = 0;
		int[][] arr = new int[my][mx];
		for (int i = 0; i < my; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == 'L')
					arr[i][j] = 0; // 육지
				else
					arr[i][j] = -1; // 물
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < my; i++) {
			for (int j = 0; j < mx; j++) {
				if (arr[i][j] == 0) {
					queue.add(i);
					queue.add(j);
					
					int[][] visited = new int[my][mx];
					visited[i][j] = 1;
					while (!queue.isEmpty()) {
						int y = queue.poll();
						int x = queue.poll();
						
						for (int dir = 0; dir < 4; dir++) {
							int ny = y + dy[dir];
							int nx = x + dx[dir];
							
							if (ny >= 0 && ny < my && nx >= 0 && nx < mx && arr[ny][nx] == 0 && visited[ny][nx] == 0) {
								visited[ny][nx] = visited[y][x] + 1;
								queue.add(ny);
								queue.add(nx);
							}
						}
					}
					
					for (int a = 0; a < my; a++) {
						for (int b = 0; b < mx; b++) {
							if (maxInt < visited[a][b]) {
								maxInt = visited[a][b];
							}
						}						
					}
				}
			}
		}
		System.out.println(maxInt-1);
	}
}
