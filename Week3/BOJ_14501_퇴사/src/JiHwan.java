import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] Ti = new int[N+1]; // 소요 기간
        int[] Pi = new int[N+1]; // 금액

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Ti[i] = Integer.parseInt(st.nextToken());
            Pi[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];

        for (int i = 0; i < N; i++) {
            if(i + Ti[i] <= N) {
                dp[i + Ti[i]] = Math.max(dp[i + Ti[i]], dp[i] + Pi[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
//            System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[N]);
    }
}

/*
[0, 0, 0, 10, 0, 0, 0, 0]
[0, 0, 0, 10, 0, 0, 20, 0]
[0, 0, 0, 10, 0, 0, 20, 0]
[0, 0, 0, 10, 30, 0, 20, 0]
[0, 0, 0, 10, 30, 30, 45, 0]
[0, 0, 0, 10, 30, 30, 45, 0]
[0, 0, 0, 10, 30, 30, 45, 45]
45
 */