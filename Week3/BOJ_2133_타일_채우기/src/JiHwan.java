import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1]; // 1, 2, 3번 case
        int[] dp2 = new int [n+1]; // 4, 5번 case

        if (n == 1) System.out.println(0);
        else {
            dp[2] = 1;
            dp2[2] = 0;

            for (int i = 4; i <= n; i += 2) {
                dp2[i] = dp[i-2] + dp2[i-2];
                dp[i] = dp[i-2] * 3 + dp2[i-2] * 2;
            }
            System.out.println(dp[n] * 3 + dp2[n] * 2);
        }
    }
}
