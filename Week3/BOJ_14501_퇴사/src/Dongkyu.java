import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dongkyu {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] time = new int[N + 1];	// 1일부터 값을 넣을 것이기 때문
		int[] pay = new int[N + 1];		// 상동
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 2];	// 뒤에서부터 접근할 것인데, 초기값을 0으로 설정하는 편의를 위해 N + 2 길이의 배열 생성
		for (int i = dp.length - 2; i >= 1; i--) {	// dp의 길이가 N + 2 이므로, N부터 시작
			if (i + time[i] - 1 > N) {	// 만약 해당 일에서 시간을 더한 값 - 1 이 N 보다 크다면
				dp[i] = dp[i + 1];	// dp 테이블 그냥 전 것(뒤에서부터 오니까 뒤에 것)과 동일. 즉 무시한다는 것.
			} else {
				dp[i] = Math.max(dp[i + 1], pay[i] + dp[i + time[i]]);	// pay[i]와 i일 때 time을 i와 더한 것 (이전)의 dp 값을 더한 것과, dp[i + 1]과 비교
			}
		}
		
		System.out.println(dp[1]);	// dp[1]이 사실상 가장 큰 값이고, 내가 구하고자 하는 '최대 이익'
		
	}
}
