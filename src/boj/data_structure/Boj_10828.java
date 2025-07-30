package boj.data_structure;

/*
스택 - 실4
소요시간 - 22분

2회차 풀이
문제설명:
push X: 정수 X를 스택에 넣는 연산
pop : 스택에서 가장위에있는 정수 제거 + 출력 (정수가 없다면 -1출력)
size: 스택크기 출력
empty: 스택비어있으면 1 아님 0 출력
top: 스택 가장 위에 있는 정수 출력 --> peek (정수가 없다면 -1출력)

p.s
- 똑같이 풀었다 신기하다. 그래서 코드는 따로 첨부하지 않겠다.
- 더 빠르고 쉬운 방법을 생각해봐야겠다.
 */
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if (str.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
            } else if (str.equals("pop")) {
                if (stack.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.pop() + "\n");
                }
            } else if (str.equals("size")) {
                bw.write(stack.size() + "\n");
            } else if (str.equals("empty")) {
                if (stack.isEmpty()) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            } else if (str.equals("top")) {
                if (stack.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.peek() + "\n");
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
