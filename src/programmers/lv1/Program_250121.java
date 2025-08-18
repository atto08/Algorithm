package programmers.lv1;

/*
[PCCE 기출문제] 10번 / 데이터 분석 - lv1
소요시간 - 29분

문제설명:
ext와 sort_by의 값은 다음 중 한 가지
"code", "date", "maximum", "remain"
코드 번호, 제조일, 최대 수량, 현재 수량

풀이설명:
- 구현
- ext에 따라서 비교할 값이 달라짐.

p.s
- Array.sort 메서드 기준 인덱스 잡는 방법 배움.
*/

import java.util.*;

public class Program_250121 {
    // 데이터, 데이터를 뽑을 기준 문자열, 뽑은 데이터의 기준 값, 정보 정렬 기준 문자열
    public static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        int pickIdx = getIdx(ext); // 뽑기 기준 인덱스

        Queue<int[]> q = new LinkedList<>();
        for (int[] arrD : data) {
            if (arrD[pickIdx] < val_ext) q.offer(arrD);
        }

        int sortIdx = getIdx(sort_by); // 정렬 기준 인덱스
        int[][] result = new int[q.size()][4];
        int idx = 0;
        while (!q.isEmpty()) {
            result[idx] = q.poll();
            idx++;
        }

        Arrays.sort(result, Comparator.comparingInt(o -> o[sortIdx])); // sort_by 기준 정렬

        return result;
    }

    private static int getIdx(String target) {
        String[] arr = {"code", "date", "maximum", "remain"};

        int idx = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i].equals(target)) {
                idx = i;
                break;
            }
        }

        return idx;
    }
}