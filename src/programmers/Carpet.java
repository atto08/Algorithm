package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
카펫 - level2
소요 시간: 42분
1) 통과
>> 타일의 총합의 약수들 중 가로길이가 가장 작을 때 부터 검사하는 방식으로 구현
 */
public class Carpet {
    static int w, h;
    public int[] solution(int brown, int yellow) {
        int[] result = new int[2];
        List<Integer> list = new ArrayList<>();
        int sum = brown + yellow;

        int num = 1;
        while (num <= sum) {
            if (sum % num == 0) {
                list.add(num);
            }
            num++;
        }

        // 약수의 갯수가 짝수 일때
        if (list.size() % 2 == 0) {
            w = list.size() / 2;
            h = list.size() / 2 - 1;

        }
        // 약수의 갯수가 홀수 일때
        else {
            w = list.size() / 2;
            h = list.size() / 2;
        }

        int width = list.get(w);
        int height = list.get(h);

        while (true) {
            int brw = width * 2 + (height - 2) * 2;
            int yel = sum - brw;
            if (brw == brown && yel == yellow){
                result[0] = width;
                result[1] = height;
                break;
            }

            w++;
            h--;
            width = list.get(w);
            height = list.get(h);
        }

        return result;
    }

    public static void main(String[] args) {
        Carpet carpet = new Carpet();
        System.out.println(Arrays.toString(carpet.solution(10,2)));
        System.out.println(Arrays.toString(carpet.solution(8,1)));
        System.out.println(Arrays.toString(carpet.solution(24,24)));
    }
}
