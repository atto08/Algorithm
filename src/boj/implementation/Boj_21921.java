package boj.implementation;
import java.io.*;
import java.util.*;

/*
 블로그 - 실3
 소요시간 - 25분
 X일동안 가장많이 들어온 방문자와 기간이 몇개 있는지 리턴.
 1) X일동안 가장많이 들어온 방문자 수 출력
 2) 가장많이 들어온 경우의 수 갯수 출력

- X일 간의 누적합을 첫번째꺼 구해놓기.
- 맨앞에꺼 지우고 하루꺼 추가하기. -> 이 과정을 반복하면서 해시 등록&업데이트
- 제일 높은 수는 max 정수형 - 업데이트 필수

p.s
- 알고리즘의 고수를 향하여.
- 오늘 배운 슬라이딩 윈도우 확인했다.
    - 구간 넓이 고정 -> 앞에꺼 하나씩 제거 및 뒤에꺼 하나씩 추가 방식
 */
public class Boj_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visitor = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) visitor[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        for(int i = 0; i < X; i++) sum += visitor[i];

        int max = sum;
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.put(sum, 1);
        for(int i = 1; i <= N - X; i++) {
            sum -= visitor[i - 1];
            sum += visitor[i + X - 1];

            if(max < sum) max = sum;

            if(!hash.containsKey(sum)) {
                hash.put(sum, 1);
            } else {
                hash.put(sum, hash.get(sum) + 1);
            }
        }

        if(max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max + "\n" + hash.get(max));
        }
    }
}
