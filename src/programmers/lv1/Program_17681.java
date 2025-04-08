package programmers.lv1;

import java.util.Arrays;

/*
[1차] 비밀지도 - lv1
소요시간 - 30분

풀이 설명:
- 숫자 이진수로 전환 & 이진수 자리 체크(부족하면 앞에 0추가) - 메서드
- 두 이진수의 자리 중 하나라도 '1'이면 # 둘다 '0'이면 공백

p.s
- 시간 복잡도 계산하면서 해보려했는데 속도 측면에서 빠른 속도를 보여줘서 패스.
 */
public class Program_17681 {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            String num1 = checkLength(arr1[i], n);
            String num2 = checkLength(arr2[i], n);

            StringBuilder num = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (num1.charAt(j) == '1' || num2.charAt(j) == '1') {
                    num.append('#');
                } else {
                    num.append(' ');
                }
            }

            result[i] = num.toString();
        }

        return result;
    }

    private static String checkLength(int num, int n) {
        String str = Integer.toBinaryString(num);

        StringBuilder number = new StringBuilder();
        if (n > str.length()) {
            for (int i = 0; i < n - str.length(); i++) {
                number.append(0);
            }
            number.append(str);
            return number.toString();
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
        System.out.println(Arrays.toString(solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10})));
    }
}
