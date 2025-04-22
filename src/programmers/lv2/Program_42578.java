package programmers.lv2;

import java.util.*;
/*
의상 - lv2
소요시간 - 8분

풀이설명:
- 같은 이름을 가진 의상은 존재X -> 중복아이템 존재 x
- 의상 카테고리의 갯수로 만들수 있는 모든 조합 갯수를 구하면 됨.
- 대신 주어진 카테고리를 모두 착용하지 않는경우도 존재함. but 최소 1개는 착용함
- 착용하지 않는 경우의 수를 모든 카테고리에 추가해주고 모든 카테고리의 수를 곱하고 모두 착용하지 않는 경우의 수1을 빼주면 됨.

p.s
이전에 한번 시도했다가 못 푼문제임
해시 카테고리에만 집중해서 못 풀었었다 생각함.
경우의 수를 파악하는게 더 중요했다.
 */
public class Program_42578 {
    public static int solution(String[][] clothes) {
        int result = 1;

        HashMap<String, Integer> map = new HashMap<>();
        for(String[] c : clothes) {
            if(map.containsKey(c[1])) {
                map.replace(c[1], map.get(c[1]) + 1);
            } else {
                map.put(c[1], 1);
            }
        }

        for(int v : map.values()) {
            result *= (v + 1);
        }

        return --result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"} , {"green_turban", "headgear"}}));
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"} , {"smoky_makeup", "face"}}));
    }
}
