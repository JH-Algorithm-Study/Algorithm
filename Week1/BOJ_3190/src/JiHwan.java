import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 보드의 크기
        int k = Integer.parseInt(br.readLine()); // 사과의 개수
        int[][] arr = new int[n][n]; // 보드
        int cnt = 0; // 걸리는 시간
        Deque<Integer> deque = new ArrayDeque<>();

        int[] dx = {1, 0, -1, 0}; // 델타 배열
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < k; i++) { // 사과의 위치
            st = new StringTokenizer(br.readLine());
            int appleY = Integer.parseInt(st.nextToken());
            int appleX = Integer.parseInt(st.nextToken());
            arr[appleY-1][appleX-1] = 4; // 사과가 있을 경우
        }

        int l = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 정보
        int[] go = new int[l+1];
        int[] command = new int[l+1]; // D = 1/-1

        for (int i = 0; i < l; i++) { // 뱀의 방향 정보 변환
            st = new StringTokenizer(br.readLine());
            go[i] = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            if (direction.equals("D")) command[i] = 1;
            else command[i] = -1;
        }

        int startY = 0;
        int startX = 0;
        int dirNum = 100;
        int goNum = 0;

        deque.offer(1);
        while (true) {
            if (cnt == go[goNum]) {dirNum += command[goNum++];}

            startY += dy[dirNum % 4];
            startX += dx[dirNum % 4];
            cnt ++;

            if (!(startY >= 0 && startY < n && startX >= 0 && startX < n) || deque.contains(startY * n + startX + 1)) {
                break;
            } else if (arr[startY][startX] == 4) {
                arr[startY][startX] = 0;
                deque.offer(startY * n + startX + 1);
            } else {
                deque.offer(startY * n + startX + 1);
                deque.poll();
            }
        }
        System.out.println(cnt);
    }
}