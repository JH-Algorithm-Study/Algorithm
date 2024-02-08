import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class JaeSung {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 피연산자 갯수
        int N = Integer.parseInt(br.readLine());
        
        // 후위 표기식
        String prefix = br.readLine();
        
        // A,B,C 등에 해당하는 숫자 담을 배열, 크기는 피연산자 갯수만큼
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 계산 결과 소숫점 둘째 자리까지 출력 => Double형
        Stack<Double> op = new Stack<>();
        
        // 후위표기식 길이 변수 len 선언
        int len = prefix.length();
        
        // 스택에 값 넣기
        for(int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            if('A' <= c && c <= 'Z') { // 피연산자(숫자)일 때
                double d = arr[c - 'A'];
                op.push(d);
            } else { // 연산자일 때
                double num1 = op.pop();
                double num2 = op.pop();
                double num3 = 0.0;
                switch(c) {
                case '+':
                    num3 = num2 + num1;
                    break;
                case '-':
                    num3 = num2 - num1;
                    break;
                case '*':
                    num3 = num2 * num1;
                    break;
                case '/':
                    num3 = num2 / num1;
                    break;
                }
                // 계산된 값 op에 push
                op.push(num3);
            }
        }
        System.out.printf("%.2f", op.pop());
        
    }
}