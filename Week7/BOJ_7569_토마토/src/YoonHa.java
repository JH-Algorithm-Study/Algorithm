import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메소드O
public class YoonHa {
	static int M, N, H;
	static int[][][] arr;
	static boolean[][][] visited;
	static int day, cnt;
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[N][M][H];
        
        for(int h = 0; h < H; h++) { // 높이
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    arr[i][j][h] = Integer.parseInt(st.nextToken());
                }
            }
        }
        
//        // 배열 출력
//        for (int h = 0; h < H; h++) {
//            System.out.println("Height " + (h + 1) + ":");
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(arr[i][j][h] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
        
        bfs(arr, visited, M, N, H);
        
        if(cnt == 0) {
        	System.out.println(day);
        } else {
        	System.out.println(-1);
        }
    }
    
    static void bfs(int[][][] arr, boolean[][][] visited, int M, int N, int H) {    	
    	Queue<int[]> q = new LinkedList<>();
    	visited = new boolean[N][M][H];
    	day = -1; // 다 익는데 걸리는 시간, while문에 들어갈때 +1 되기 때문에 -1로 초기화
    	cnt = 0; // 안익은 토마토 수
    	
    	for(int h = 0; h < H; h++) {
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < M; j++) {
    				if(arr[i][j][h] == 1 && !visited[i][j][h]) {
    					visited[i][j][h] = true;
    					q.offer(new int[] {i, j, h});
    				} else if(arr[i][j][h] == 0) {
    					cnt++;
    				}
    			}
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		int size = q.size();
    		for(int i = 0; i < size; i++) {
    			int[] pos = q.poll();
    			int r = pos[0];
    			int c = pos[1];
    			int h = pos[2];
    			
    			for(int dir = 0; dir < 6; dir++) {
    				int nr = r + dr[dir];
    				int nc = c + dc[dir];
    				int nh = h + dh[dir];
    				
    				if(nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H && arr[nr][nc][nh] == 0 && !visited[nr][nc][nh]) {
    					visited[nr][nc][nh] = true;
    					arr[nr][nc][nh] = 1;
    					q.offer(new int[] {nr, nc, nh});
    					cnt--;
    				}
    			}
    		}
    		day++;
    	}
    }
}

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// // 메소드X
// public class YoonHa {
//     public static void main(String[] args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int M = Integer.parseInt(st.nextToken());
//         int N = Integer.parseInt(st.nextToken());
//         int H = Integer.parseInt(st.nextToken());
//         int[][][] arr = new int[N][M][H];
//         int[] dr = { -1, 0, 1, 0, 0, 0 };
//         int[] dc = { 0, 1, 0, -1, 0, 0 };
//         int[] dh = { 0, 0, 0, 0, 1, -1 };
        
//         for(int h = 0; h < H; h++) { // 높이
//             for(int i = 0; i < N; i++) {
//                 st = new StringTokenizer(br.readLine());
//                 for(int j = 0; j < M; j++) {
//                     arr[i][j][h] = Integer.parseInt(st.nextToken());
//                 }
//             }
//         }
        
// //        // 배열 출력
// //        for (int h = 0; h < H; h++) {
// //            System.out.println("Height " + (h + 1) + ":");
// //            for (int i = 0; i < N; i++) {
// //                for (int j = 0; j < M; j++) {
// //                    System.out.print(arr[i][j][h] + " ");
// //                }
// //                System.out.println();
// //            }
// //            System.out.println();
// //        }
        
//         Queue<int[]> q = new LinkedList<>();
//         boolean[][][] visited = new boolean[N][M][H];
//         int day = -1; // 다 익는데 걸리는 시간, while문에 들어갈때 +1 되기 때문에 -1로 초기화
//         int cnt = 0; // 안익은 토마토 수
        
//         for(int h = 0; h < H; h++) {
//             for(int i = 0; i < N; i++) {
//                 for(int j = 0; j < M; j++) {
//                     if(arr[i][j][h] == 1 && !visited[i][j][h]) {
//                     	visited[i][j][h] = true;
//                     	q.offer(new int[] {i, j, h});
//                     } else if(arr[i][j][h] == 0) {
//                     	cnt++;
//                     }
//                 }
//             }
//         }
        
//         while(!q.isEmpty()) {
//         	int size = q.size();
//         	for(int i = 0; i < size; i++) {
//         		int[] pos = q.poll();
//         		int r = pos[0];
//         		int c = pos[1];
//         		int h = pos[2];
        		
//         		for(int dir = 0; dir < 6; dir++) {
//         			int nr = r + dr[dir];
//         			int nc = c + dc[dir];
//         			int nh = h + dh[dir];
        			
//         			if(nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H && arr[nr][nc][nh] == 0 && !visited[nr][nc][nh]) {
//         				visited[nr][nc][nh] = true;
//         				arr[nr][nc][nh] = 1;
//         				q.offer(new int[] {nr, nc, nh});
//         				cnt--;
//         			}
//         		}
//         	}
//         	day++;
//         }
        
//         if(cnt == 0) {
//         	System.out.println(day);
//         } else {
//         	System.out.println(-1);
//         }
//     }
// }