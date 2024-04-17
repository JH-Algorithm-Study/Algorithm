import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());

			int[][] conv = new int[n+2][2];	// 0은 시작점, n+1은 도착점
			boolean[] visited = new boolean[n+2];
			
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				conv[i][0] = Integer.parseInt(st.nextToken());
				conv[i][1] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<ArrayList<Integer>> canGo = new ArrayList<>();
			for(int i = 0; i < n+2; i++) {
				canGo.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i <= n+1; i++) {
				for (int j = 0; j <= n+1; j++) {
					if (1000 >= (Math.abs(conv[i][0] - conv[j][0]) + Math.abs(conv[i][1] - conv[j][1])) && i != j) {
						canGo.get(i).add(j);
					}
				}
			}
			
			Queue<Integer> queue = new LinkedList<>();
			queue.add(0);
			
			boolean isHappy = false; 
			while (!queue.isEmpty() && !isHappy) {
				int p = queue.poll();
				
				if (!visited[p]) {
					visited[p] = true;
					for (int d : canGo.get(p)) {
					
						if (d == n + 1) {
							isHappy = true;
							break;
						} else if (!visited[d]) {
							queue.add(d);
						}
					}
				}
			}
			if (isHappy) System.out.println("happy");
			else System.out.println("sad");
		}
	}
}
