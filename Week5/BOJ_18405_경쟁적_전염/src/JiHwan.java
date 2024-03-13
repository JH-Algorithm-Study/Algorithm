import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int MinDist = Integer.MAX_VALUE; // 최소 거리
        int MinNum = 0; // 최소 번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) { // 바이러스일 경우
                    int d = Math.abs(i - (Y-1)) + (Math.abs(j - (X-1))); // 해당 지점에 도착할 수 있는 가장 빠른 시간 측정
                    if (MinDist > d) { // 바이러스가 해당 지점에 도착하는 시간이 최소 시간보다 작을 경우
                        MinDist = d;
                        MinNum = arr[i][j];
                    } else if (MinDist == d) { // 바이러스가 해당 지점에 도착하는 시간이 최소 시간과 같을 경우
                        if (MinNum > arr[i][j]) { // 바이러스 번호가 기존 번호보다 작을 경우 갱신
                            MinNum = arr[i][j];
                        }
                    }
                }
            }
        }

        if (S < MinDist) System.out.println(0); // 시간이 더 걸렸을 경우
        else System.out.println(MinNum); // 시간이 더 조금 걸린 경우
    }
}