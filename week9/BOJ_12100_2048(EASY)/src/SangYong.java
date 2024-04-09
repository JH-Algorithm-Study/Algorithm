import java.util.*;

public class SangYong {
	static int[] target = new int[] {1, 2, 3, 4};
	static int[] result = new int[5];
	static int N;
	static int[][] board;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		permutation(0);
		
		System.out.println(max);
	}
	
	static void permutation(int cnt) {
		if (cnt == 5) {
			move();
			return;
		}
		for (int i = 0; i < 4; i++) {
			result[cnt] = target[i];
			permutation(cnt + 1);
		}
	}
	
	static void move() {
		int[][] copy = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = board[i][j];
			}
		}
		
		for(int d=0; d<5; d++) {
			if(result[d] == 1) {
				for(int i = 0; i < N; i++) {
	                int index = 0;
	                int block = 0;
	                for(int j = 0; j < N; j++) {
	                    if(copy[j][i] != 0) {
	                        if(block == copy[j][i]) {
	                        	copy[index - 1][i] = block * 2; 
	                            block = 0;
	                            copy[j][i] = 0;
	                        }
	                        else {
	                            block = copy[j][i];
	                            copy[j][i] = 0;
	                            copy[index][i] = block;
	                            index++;
	                        }
	                    }
	                }
	            }
			}
			else if(result[d] == 2) {
				for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(copy[j][i] != 0) {
	                        if(block == copy[j][i]) {
	                        	copy[index + 1][i] = block * 2;
	                            block = 0;
	                            copy[j][i] = 0;
	                        }
	                        else {
	                            block = copy[j][i];
	                            copy[j][i] = 0;
	                            copy[index][i] = block;
	                            index--;
	                        }
	                    }
	                }
	            }
			}
			else if(result[d] == 3) {
				for(int i = 0; i < N; i++) {
	                int index = 0;
	                int block = 0;
	                for(int j = 0; j < N; j++) {
	                    if(copy[i][j] != 0) {
	                        if(block == copy[i][j]) {
	                        	copy[i][index - 1] = block * 2;
	                            block = 0;
	                            copy[i][j] = 0;
	                        }
	                        else {
	                            block = copy[i][j];
	                            copy[i][j] = 0;
	                            copy[i][index] = block;
	                            index++;
	                        }
	                    }
	                }
	            }			
			}
			else if(result[d] == 4) {
				for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(copy[i][j] != 0) {
	                        if(block == copy[i][j]) {
	                        	copy[i][index + 1] = block * 2;
	                            block = 0;
	                            copy[i][j] = 0;
	                        }
	                        else {
	                            block = copy[i][j];
	                            copy[i][j] = 0;
	                            copy[i][index] = block;
	                            index--;
	                        }
	                    }
	                }
	            }
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copy[i][j] > max)
					max = copy[i][j];
			}
		}
	}
}