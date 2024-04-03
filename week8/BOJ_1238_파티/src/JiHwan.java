import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] minCost = new int[N+1];	// 1. 방문 여부 처리 및 X부터 최단 거리 기록
		int[] minCost2 = new int[N+1];	
        Arrays.fill(minCost, Integer.MAX_VALUE);
        Arrays.fill(minCost2, Integer.MAX_VALUE);
        
        ArrayList<ArrayList<int[]>> descArr = new ArrayList<>(N + 1); // 2. 시작점, 끝점, cost 저장하는 descArr 초기화
        ArrayList<ArrayList<int[]>> descArr2 = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            descArr.add(new ArrayList<>());
            descArr2.add(new ArrayList<>());
        }
		
		for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            descArr.get(start).add(new int[]{end, cost});
            descArr2.get(end).add(new int[]{start, cost});
        }
		
		Queue<Integer> queue = new LinkedList<Integer>();	// queue
		queue.add(X);
		minCost[X] = 0;
		
		while(!queue.isEmpty()) {
			int q = queue.poll();	// 2를 뽑았어 그걸 descArr에 넣어 
			
			for (int[] num : descArr.get(q)) { //2에 있는 숫자들을 뽑자
				if (minCost[q] + num[1] < minCost[num[0]]) {
					minCost[num[0]] = minCost[q] + num[1];
					queue.add(num[0]);
				}
			}
		}

		queue.add(X);
		minCost2[X] = 0;
		
		while(!queue.isEmpty()) {
			int q = queue.poll();	// 2를 뽑았어 그걸 descArr에 넣어 
			
			for (int[] num : descArr2.get(q)) { //2에 있는 숫자들을 뽑자
				if (minCost2[q] + num[1] < minCost2[num[0]]) {
					minCost2[num[0]] = minCost2[q] + num[1];
					queue.add(num[0]);
				}
			}
		}
		
		int maxInt = 0;
		for (int i = 1; i < N + 1; i++) {
			maxInt = Math.max(maxInt, minCost[i] + minCost2[i]);
		}
		System.out.println(maxInt);
	}
}