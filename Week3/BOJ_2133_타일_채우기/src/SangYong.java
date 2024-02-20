import java.util.*;

class SangYong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N+1];

        if(N % 2 == 1)
            System.out.print(0);
        else {
            arr[2] = 3;
            for(int i=4; i<=N; i+=2){
                arr[i] += 3 * arr[i-2];
                for(int j=i-4; j>0; j-=2){
                    arr[i] += 2 * arr[j];
                }
                arr[i] += 2;
            }
            System.out.print(arr[N]);
        }
    }
}