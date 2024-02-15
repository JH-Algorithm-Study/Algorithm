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
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		heap = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			
			if (x >= 1) {
				heapPush(x);
			} else {
				if (heapSize < 1) {
					System.out.println(0);
				} else {
					System.out.println(heapPop());
				}
			}
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
		
		while (p >= 0 && heap[p] > heap[ch]) {
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
		
		while (ch <= heapSize) {
			if (ch + 1 <= heapSize && heap[ch] > heap[ch + 1]) {
				ch++;
			}
			
			if (heap[p] > heap[ch]) {
				swap(p, ch);
				
				p = ch;
				ch = p * 2;
			} else {
				break;
			}
		}
		
		return popItem;
	}
}
