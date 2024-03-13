import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class YoonHa {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i][j]);
			}
		}
//		System.out.println(Arrays.deepToString(arr));
//		System.out.println(max);

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		int Y = Integer.parseInt(st.nextToken()) - 1;

		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();

		for (int start = 1; start <= max; start++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == start) {
						visited[i][j] = true;
						q.offer(new int[] { i, j, 0 });

					}
				}
			}
		}

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			int time = pos[2];

			if (time >= S) {
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] == 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					arr[nr][nc] = arr[r][c];
					q.offer(new int[] { nr, nc, time + 1 });

				}
			}
		}
//		System.out.println(Arrays.deepToString(arr));
//		System.out.println(Arrays.deepToString(visited));
		System.out.println(arr[X][Y]);
	}
}