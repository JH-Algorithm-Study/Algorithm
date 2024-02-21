import java.io.BufferedReader;
import java.io.InputStreamReader;
// import java.util.Arrays;
import java.util.StringTokenizer;

public class YoonHa {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
//		System.out.println(Arrays.deepToString(arr));
//      버퍼드리더랑 2차원 배열로 집 구현까지
		
	}
}
