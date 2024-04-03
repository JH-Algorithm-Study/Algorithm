import java.io.*;
import java.util.*;

public class Main {
	
	private static int[] p;
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long totalCost = 0;	// 도로를 다 설치할 때 드는 비용
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] tmp = new int[3];
			tmp[0] = Integer.parseInt(st.nextToken()); 
			tmp[1] = Integer.parseInt(st.nextToken()); 
			tmp[2] = Integer.parseInt(st.nextToken());
			totalCost += tmp[2];
			
			pq.add(tmp);
		}
		
		p = new int[N+1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}
		
		int cnt = 0;
		long minCost = 0;	// 최소비용
		while(!pq.isEmpty()) {
			int[] arr = pq.poll();
			if (!union(arr[0], arr[1])) {
				cnt ++;
				minCost += arr[2];
			}
			
			if (cnt == N - 1) break;
		}
		
		if (cnt != N - 1) System.out.println(-1);
		else System.out.println(totalCost - minCost);
	}
	
	private static int find(int x) {
		if(x == p[x]) return x;
		else return p[x] = find(p[x]);
	}
	
	private static boolean union(int x, int y) {
		x = find(p[x]);
		y = find(p[y]);
		
		if (x != y) {
			if (x < y) p[y] = x;
			else p[x] = y;			
			return false;
		} else {
			return true;
		}
	}
	
}
