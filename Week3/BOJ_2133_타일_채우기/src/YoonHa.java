import java.io.BufferedReader;
import java.io.InputStreamReader;

public class YoonHa {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1]; // dp배열
		int ans = 0;
		
		if(N % 2 == 1) { // 홀수는 불가
			ans = 0;
		} else { // 짝수일 경우
			dp[0] = 1;
			dp[2] = 3;

			for(int i = 4; i <= N + 1; i += 2) { // 4부터 짝수의 경우
				dp[i] = dp[i - 2] * 3; // 전에 구한 경우의 수 * 3 (한쪽에 dp[2]의 경우를 붙인다고 가정)
				for(int j = i - 4; j >= 0; j -= 2) {
					dp[i] += dp[j] * 2; // 방향 반대로 붙여야 하는데 위의 i의 경우를 두번 반복하면 중복이 너무 많음
					// 각 경우마다 2번씩 존재하는 특이한 경우의 수만큼 반복하면 됨
				}
			}
			ans = dp[N];
		}
		System.out.println(ans);
	}
}
