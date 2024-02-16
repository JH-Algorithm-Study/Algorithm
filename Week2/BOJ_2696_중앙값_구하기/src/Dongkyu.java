
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dongkyu {

	static int[] heap;
	static int heapSize;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			
			heapSize = 0;	// heapSize를 static으로 선언했기 때문에, 테스트 케이스마다 초기화해줘야 한다.
			sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			System.out.println((M + 1) / 2);	// 어차피 (입력받은 값 + 1) / 2 의 값이 먼저 출력되어야 하므로
			
			heap = new int[M + 1];
			
			int sbCnt = 0;	// sb에 10개 들어가면 출력하고 비우기 위해 설정
			int[] tmpArr = new int[M + 1];	// 임시로 heap에서 pop한 원소들 담아두는 배열
			for (int line = 0; line < M / 10; line++) {		// 만약 M이 10 이상이라면, st에 10개씩 담아지므로 이렇게 for문 생성
				
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < 10; i++) {	// 해당 st에는 무조건 10개씩 들어가므로, 10번 순회
					heapPush(Integer.parseInt(st.nextToken()));
					
					if (heapSize % 2 == 1) {
						int cnt = heapSize;		// heapPop() 연산을 수행할 때 heapSize가 변경되므로, 처음에 cnt 변수에 담는다.
						int popped = 0;		// pop한 값을 담을 변수 popped
						for (int j = 0; j < cnt / 2 + 1; j++) {		// 중위값을 구하는 것이므로, cnt / 2 + 1 까지 순회
							popped = heapPop();
							tmpArr[j] = popped;	// 임시 배열 tmpArr에 순서대로 popped를 담는다.
						}
						sb.append(popped + " ");
						sbCnt++;
						
						for (int j = 0; j < cnt / 2 + 1; j++) {		// 다시 heap에 push하기 위해서 배열에 담긴 부분까지 순회 (cnt / 2 + 1)
							heapPush(tmpArr[j]);
							tmpArr[j] = 0;	// 메모리 초과를 방지하기 위해 배열을 반복문 돌 때마다 생성하지 않을 것이기 때문에, 그냥 해당 값을 push 했다면 초기값인 0으로 변환
						}
					}
				}
				if (sbCnt >= 10) {		// 만약 sb에 들어간 개수가 10 이상이라면, (10이라면)
					sb.delete(sb.length() - 1, sb.length());
					System.out.println(sb.toString());	// 출력하고
					sb.setLength(0);	// sb 비운다.
				}
			
			}
			
			
			st = new StringTokenizer(br.readLine());	// 만약 M이 10 이상이 아니었으면 바로 여기로 왔을 것이다.
			for (int i = 0; i < M % 10; i++) {	// 10으로 나눈 나머지만큼 순회
				heapPush(Integer.parseInt(st.nextToken()));
//				int[] tmpArr = new int[M + 1];	// 이렇게 할 경우 메모리 초과 발생
				
				if (heapSize % 2 == 1) {
					int cnt = heapSize;
					int popped = 0;
					for (int j = 0; j < cnt / 2 + 1; j++) {
						popped = heapPop();
						tmpArr[j] = popped;
					}
					sb.append(popped + " ");
					
					for (int j = 0; j < cnt / 2 + 1; j++) {
						heapPush(tmpArr[j]);
						tmpArr[j] = 0;
					}
				}
			}
			
			sb.delete(sb.length() - 1, sb.length());
			System.out.println(sb.toString());
			
		}
		
	}
	
	static void swap(int a, int b) {
		int tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}
	
	static void heapPush(int data) {
		heap[++heapSize] = data;
		
		int ch = heapSize;
		int p = heapSize / 2;
		
		while (p > 0 && heap[p] < heap[ch]) {
			swap(p, ch);
			
			ch = p;
			p = ch / 2;
		}
	}
	
	static int heapPop() {
		int popItem = heap[1];
		heap[1] = heap[heapSize--];
		
		int p = 1;
		int ch = p * 2;
		
		if (ch + 1 <= heapSize && heap[ch] < heap[ch + 1]) {
			ch++;
		}
		
		while (ch <= heapSize && heap[p] < heap[ch]) {
			
			swap(p, ch);
			
			p = ch;
			ch = p * 2;
			
			if (ch + 1 <= heapSize && heap[ch] < heap[ch + 1]) {
				ch++;
			}
		}
		
		return popItem;
	}
	
}
