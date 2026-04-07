package boj.implementation;

import java.io.*;
import java.util.*;

/*
줄 세우기 - 실5
소요시간 - 35분

[문제설명]
- 20명의 학생, 같은 키 존재x
- 우선 아무나 한 명을 뽑아 줄의 맨 앞에 세운다. 그리고 그 다음부터는 학생이 한 명씩 줄의 맨 뒤에 서면서 다음 과정을 거친다.
- 자기(B) 앞에 자기(B)보다 키가 큰 학생이 X
    -> 그냥 그 자리에 서고 차례 종료
- 자기(B) 앞에 자기(B)보다 키가 큰 학생이 한 명 이상 O
    -> 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다.
    - 이때, A부터 그 뒤의 모든 학생들은 한 발씩 뒤로 물러섬

- 줄 서기가 끝났을때, 테스트별 학생들이 뒤로 물러선 합의 수를 구하기

[풀이설명]
1차시도 - 20분(실패)
- 줄 서야하는 학생보다 이미 줄선 학생중에 키가큰 학생이 있다면 무조건 맨 앞에간다고 이해함
-> 키의 max 값만 갱신함

2차시도 - +15분(성공)
- List의 인덱스를 확인하며 자기보다 큰 친구를 확인하면 그곳에 삽입(세움)하고 뒤에 인원 수만큼 뒤로 물러서기(+)

 */
public class BOJ_10431 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int test_case = 1; test_case <= P; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int T = Integer.parseInt(st.nextToken());

            List<Integer> line = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken());
            line.add(first);
            int sum = 0;
            for (int i = 1; i < 20; i++) {
                int h = Integer.parseInt(st.nextToken());

                boolean isTaller = false;
                for (int j = 0; j < line.size(); j++) {
                    if (line.get(j) > h) {
                        sum += line.size() - j;
                        isTaller = true;
                        line.add(j, h);
                        break;
                    }
                }

                if (!isTaller) {
                    line.add(h);
                }
            }

            result.append(T).append(" ").append(sum).append("\n");
        }
        System.out.println(result);
    }
}