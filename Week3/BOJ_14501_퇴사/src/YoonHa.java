import java.io.*;
import java.util.*;

public class YoonHa {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N]; //소요 기간
		int[] P = new int[N]; //금액
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i]=Integer.parseInt(st.nextToken());	
			P[i]=Integer.parseInt(st.nextToken());		
		}
		
		int[] dp = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			 if(i + T[i] <= N) { //범위에 벗어나지 않는다면 
				 dp[i + T[i]]=Math.max(dp[i + T[i]], dp[i] + P[i]);	
			 }
			 dp[i + 1] = Math.max(dp[i + 1], dp[i]); //다음dp = 현재 누적값 vs 다음 누적값	 
		}
		System.out.println(dp[N]);	
	}	
}
