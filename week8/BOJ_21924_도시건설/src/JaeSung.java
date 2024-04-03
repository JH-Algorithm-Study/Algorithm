import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class JaeSung {
	static class Edge implements Comparable<Edge> {
		int A, B, W;

		public Edge(int a, int b, int w) {
			A = a;
			B = b;
			W = w;
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return this.W - o.W;
		}
	}

	static int[] p;
	static int[] rank; // 트리 높이 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] edges = new int[E][3];
		long total = 0;
		long price = 0;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken()); // 시작정점
			edges[i][1] = Integer.parseInt(st.nextToken()); // 끝정점
			edges[i][2] = Integer.parseInt(st.nextToken()); // 가중치
			total += edges[i][2]; // 총 비용 total에 담기
		}

		Arrays.sort(edges, new Comparator<int[]>() { // 가중치 기준으로 정렬

			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
			}
		});

		p = new int[V + 1];
		rank = new int[V + 1];
		for (int i = 1; i < V + 1; i++) { // 자기 자신 가리키도록 함
			p[i] = i;
		}

		int pick = 0; // 간선 몇개 골랐는지 카운트

		for (int i = 0; i < E; i++) {
			int px = findset(edges[i][0]); // 부모 찾기
			int py = findset(edges[i][1]);

			if (px != py) { // 두 부모가 다르다면 union
				union(px, py);
				price += edges[i][2]; // 연결했기 때문에 가격 추가!
				pick++; // 간선 골랐으니까 추가!
			}
			if (pick == (V - 1)) // 간선을 정점의 갯수보다 1개 덜 골랐다면 다 고른 것이므로 break;
				break;
		}
		
		boolean isConnected = true; // 연결성 검사 플래그
		int root = findset(1); // 1번 정점의 루트를 기준으로 함
		for(int i = 2; i <= V; i++) {
			if(findset(i) != root) {
				isConnected = false;
				break;
			}
		}
		
		if(pick == (V - 1) && isConnected) // 정상 종료
			System.out.println(total - price);
		else // 건물 연결되지 않았을 경우
			System.out.println(-1);
	}

	private static void union(int x, int y) { // union
		if(rank[x] > rank[y]) {
			p[y] = x;
		} else {
			p[x] = y;
			if(rank[x] == rank[y]) {
				rank[y]++;
			}
		}
	}

	private static int findset(int x) { // 부모 찾기
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}
}