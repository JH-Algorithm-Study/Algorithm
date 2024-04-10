import java.io.*;
import java.util.*;

public class Main {
	
	private static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] originArr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				originArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 중복 순열 4 ^ 5
		
		Stack<int[]> stack = new Stack<>();
		for (int i = 0; i < 4; i++) {
			stack.add(new int[] {i});
			for (int j = 0; j < 4; j++) {
				stack.add(new int[] {i, j});
				for (int k = 0; k < 4; k++) {
					stack.add(new int[] {i, j, k});
					for (int l = 0; l < 4; l++) {
						stack.add(new int[] {i, j, k, l});
						for (int m = 0; m < 4; m++) {
							stack.add(new int[] {i, j, k, l, m});
						}
					}
				}
			}
		}

		int maxInt = 0;
		while (!stack.isEmpty()) {
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = originArr[i][j];
				}
			}
			
			int[] ms = stack.pop();
			
			for (int num : ms) {
				if (num == 0) top(arr);
				else if (num == 1) bottom(arr);
				else if (num == 2) left(arr);
				else if (num == 3) right(arr);
			}
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (maxInt < arr[i][j]) maxInt = arr[i][j];
				}
			}
		}
		
		System.out.println(maxInt);
	}
	
	private static void top(int[][] arr) {
		for (int i = 0; i < N; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = 0; j < N; j++) {
				if (arr[j][i] != 0) {
					queue.add(arr[j][i]);
					arr[j][i] = 0; 
				}
			}
			
			int cnt = 0;
			int tmp = 0;
			while(!queue.isEmpty()) {
				int t = queue.poll();
				
				if (tmp == 0) tmp = t;
				else if (tmp == t) {
					tmp = 0;
					arr[cnt++][i] = t * 2;
				} else {
					arr[cnt++][i] = tmp;
					tmp = t;
				}
			}
			if (tmp != 0) arr[cnt][i] = tmp;
		}		
	}
	
	private static void bottom(int[][] arr) {
		for (int i = 0; i < N; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = N - 1; j >= 0; j--) {
				if (arr[j][i] != 0) {
					queue.add(arr[j][i]);
					arr[j][i] = 0; 
				}
			}
			
			int cnt = N - 1;
			int tmp = 0;
			while(!queue.isEmpty()) {
				int t = queue.poll();
				
				if (tmp == 0) tmp = t;
				else if (tmp == t) {
					tmp = 0;
					arr[cnt--][i] = t * 2;
				} else {
					arr[cnt--][i] = tmp;
					tmp = t;
				}
			}
			if (tmp != 0) arr[cnt][i] = tmp;
		}		
	}
	
	private static void left(int[][] arr) {
		for (int i = 0; i < N; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) {
					queue.add(arr[i][j]);
					arr[i][j] = 0; 
				}
			}
			
			int cnt = 0;
			int tmp = 0;
			while(!queue.isEmpty()) {
				int t = queue.poll();
				
				if (tmp == 0) tmp = t;
				else if (tmp == t) {
					tmp = 0;
					arr[i][cnt++] = t * 2;
				} else {
					arr[i][cnt++] = tmp;
					tmp = t;
				}
			}
			if (tmp != 0) arr[i][cnt] = tmp;
		}		
	}
	
	
	private static void right(int[][] arr) {
		for (int i = 0; i < N; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = N - 1; j >= 0; j--) {
				if (arr[i][j] != 0) {
					queue.add(arr[i][j]);
					arr[i][j] = 0; 
				}
			}
			
			int cnt = N - 1;
			int tmp = 0;
			while(!queue.isEmpty()) {
				int t = queue.poll();
				
				if (tmp == 0) tmp = t;
				else if (tmp == t) {
					tmp = 0;
					arr[i][cnt--] = t * 2;
				} else {
					arr[i][cnt--] = tmp;
					tmp = t;
				}
			}
			if (tmp != 0) arr[i][cnt] = tmp;
		}		
	}
	
}
