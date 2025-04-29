package programmers.lv3;

import java.util.*;

/*
이중우선순위 큐 - lv3
소요시간 - 1시간 초과

풀이설명:

1차시도 -
최솟값 큐 >> 내림차순 정렬
최댓값 큐 >> 오름차순 정렬

- 첫번째 값을 최솟값 큐에 넣기
- 양쪽 큐를 비교해가며 숫자 삽입하기
최솟값 / 최댓값
X    / X    -> 최솟값 큐에 넣기
O    / X    -> 최솟값 중 제일 큰놈이랑 비교
X    / O    -> 최댓값 중 제일 작은수랑 비교
O    / O    -> 최솟값 젤 큰수와 최댓값 젤 작은수와 비교
문제점 - 삭제 명령 수행에서 문제발생. 반대의 정렬로 값이 삭제되어야 하는데 현재 상황에서는 max에서 최솟값을 min에서 최댓값을 삭제할 수 있는상황

2차시도 - 풀이참고
- 최소 최대 큐 모두 일치하게 값 넣기
- 작은 수 제거는 min.poll() & max.remove(해당 숫자)
- 큰 수 제거는 max.poll() & min.remove(해당 숫자)
- 이렇게 되면 두 큐는 같은상태를 유지하게 됨(minQ와 maxQ는 크기가 같아짐)
- 즉, 하나의 큐가 비어있다면 [0,0]을 리턴하고 하나만 있다면 최소 최대값은 같고 두개의 값이 있다면 최소 최대값 출력 가능.

p.s
- 우선순위 큐를 구현하면서 같은 상태를 유지하는게 중요한 문제였던거 같다.
- remove를 이런식으로 써서 같은 상태를 유지할 수 있다 생각하지 못했음.
- 삽입 & 제거 모두 가능했어야했는데 일단 앞만보고 둘중 하나만 선택했다가 이런거 같다.
- 경우의 수를 미리 고려해본건 좋았다.
*/
public class Program_42628 {
    public static int[] solution(String[] operations) {

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String[] now = op.split(" ");
            int num = Integer.parseInt(now[1]);

            if (now[0].charAt(0) == 'I') {
                minQ.offer(num);
                maxQ.offer(num);
            } else {
                if (num < 0) {
                    if (!minQ.isEmpty()) {
                        int m = minQ.poll();
                        maxQ.remove(m);
                    }
                } else {
                    if (!maxQ.isEmpty()) {
                        int m = maxQ.poll();
                        minQ.remove(m);
                    }
                }
            }
        }

        int[] result = {0, 0};
        if (!minQ.isEmpty()) {
            result[0] = maxQ.poll();
            result[1] = minQ.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println(Arrays.toString(solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }
}
