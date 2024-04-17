import java.util.*;

public class SangYong {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	for(int t=0; t<T; t++) {
    		int N = sc.nextInt();
    		int curx = sc.nextInt(); int cury = sc.nextInt();
    		
    		int[][] cu = new int[N+1][2];
    		for(int i=0; i<N; i++) {
    			cu[i][0] =  sc.nextInt();
    			cu[i][1] =  sc.nextInt();
    		}
    		
    		int endx = sc.nextInt(); int endy = sc.nextInt();
    		cu[N][0] = endx; cu[N][1] = endy;
    		
    		boolean[] visited = new boolean[N+1];
    		
    		Queue<int[]> q = new LinkedList<>();
    		q.offer(new int[] {curx, cury});
    		
    		int flag = 0;
    		while(!q.isEmpty()) {
    			int[] p = q.poll();
    			if(p[0] == endx && p[1] == endy) {
    				flag = 1;
    				break;
    			}
    			
    			for(int i=0; i<=N; i++) {
    				int dist = Math.abs(p[0]-cu[i][0]) + Math.abs(p[1]-cu[i][1]);
    				if(!visited[i] && dist<=1000) {
    					q.offer(new int[] {cu[i][0], cu[i][1]});
    					visited[i] = true;
    				}
    			}
    		}
    		
    		if(flag == 1)
    			System.out.println("happy");
    		else
    			System.out.println("sad");
    	}
    }
}