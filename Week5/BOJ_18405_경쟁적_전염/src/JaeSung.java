package BOJ;

import java.util.*;
import java.io.*;

public class JaeSung {
    static int N, K, X, Y, S; // 변수 저장
    static int[][] arr; // 시험관
    static int[] dr = { -1, 1, 0, 0 }; // 델타배열 r
    static int[] dc = { 0, 0, 1, -1 }; // 델타배열 c

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 시험관 크기
        K = Integer.parseInt(st.nextToken()); // 바이러스 종류 수
        arr = new int[N][N];

        for (int r = 0; r < N; r++) { // 시험관 채우기
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 시간 경과
        X = Integer.parseInt(st.nextToken()); // X좌표
        Y = Integer.parseInt(st.nextToken()); // Y좌표

        exit: for (int s = 0; s < S; s++) { // s: 시간 정보
            for (int k = 1; k <= K; k++) { // k: 바이러스 정보
                BFS(k);
                if (arr[X - 1][Y - 1] != 0) // 특정 칸에 바이러스가 전파되었다면 종료
                    break exit;
            }
        }

        System.out.println(arr[X - 1][Y - 1]);

    }

    public static void BFS(int k) {
        Queue<Node> q = new LinkedList<>();

        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++) {
                if (arr[r][c] == k) // 바이러스 유형과 같은지 파악
                    q.add(new Node(r, c)); // 같다면 노드에 위치 추가
            }

        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = n.x + dr[d];
                int ny = n.y + dc[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) // 시험관 탈출하면 안됨
                    continue;
                if (arr[nx][ny] != 0) // 이미 다른 값이 차있으면 안됨
                    continue;

                arr[nx][ny] = k; // 위의 조건을 다 만족하지 않으면 해당 값 채우기
            }

        }
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}