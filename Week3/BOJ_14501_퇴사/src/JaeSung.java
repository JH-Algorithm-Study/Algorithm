package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JaeSung {
    static int startday;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ti = new int[N + 1];
        int[] pi = new int[N + 1];
        int[] price = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ti[i] = Integer.parseInt(st.nextToken());
            pi[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            int tomorrow = i + ti[i];

            if (tomorrow > N + 1) {
                price[i] = price[i + 1];
            } else {
                if (price[i + 1] >= pi[i] + price[tomorrow]) {
                    price[i] = price[i + 1];
                } else {
                    price[i] = pi[i] + price[tomorrow];
                }

            }
        }
        System.out.println(price[1]);

    }

}
