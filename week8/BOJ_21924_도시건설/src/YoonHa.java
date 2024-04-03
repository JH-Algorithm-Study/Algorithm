import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class YoonHa {
    static int N, M;
    static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			p[i] = i;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->Integer.compare(a[2], b[2]));
		
		long sum = 0l;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			pq.add(new int[] {start, end, price});
			sum += price;
		}
		
		int cnt = 0;
		long min = 0l;
		while(!pq.isEmpty()) {
			if(cnt == N - 1) break;
			
			int[] temp = pq.poll();
			
			if(!union(temp[0], temp[1])) {
				min += temp[2];
				cnt++;
			}
		}
		
		if(cnt != N - 1) {
			System.out.println(-1);
		} else {
			System.out.println(sum - min);
		}
		
	}
	
    private static int find(int a) {
        if(p[a] == a)
        	return a;
    	return p[a] = find(p[a]);
    }
    
    private static boolean union(int a, int b) {
    	int A = find(a);
    	int B = find(b);
    	
    	if(A != B) {
    		p[B] = A;
    		return false;
    	}
    	
    	else return true;
    }
}