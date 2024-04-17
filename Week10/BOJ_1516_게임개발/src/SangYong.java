import java.util.*;

public class SangYong {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] cnt = new int[N+1];
		int[] time = new int[N+1];
		int[] ans = new int[N+1];
		
		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N; i++) {
			time[i] = sc.nextInt();
			
			while(true) {
				int num = sc.nextInt();
				if(num == -1) {
					break;
				}
				else {
					adjList[num].add(i);
					cnt[i]++;
				}
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(cnt[i] == 0) {
				q.offer(i);
				ans[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(int n : adjList[node]) {
				ans[n] = Math.max(ans[n], ans[node]+time[n]);
				cnt[n] -= 1;
				if(cnt[n] == 0)
					q.offer(n);
			}
		}
		
		for(int i=1; i<=N; i++) {
			System.out.println(ans[i]);
		}
	}
}