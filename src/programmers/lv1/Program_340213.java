package programmers.lv1;

/*
동영상 재생기(PCCP 기출 1번) - lv1
소요시간 - 41분

[풀이설명]
- 명령어 값을 한번에 더하고 뺄까 생각햇음 - 기각
    -> 동영상 최대길이를 넘어가는 경우와 음수인 경우를 무시하게 되어버림
- 오프닝 시작&종료, 동영상 길이 값, 재생시작 위치를 초로 변환한 값을 갖고있기.
-> 59분59초가 최대 경우이기 때문에, 3599초가 최댓값.
- 커멘드 배열의 값을 일일히 확인하며 수정된 값에 따라 조정

p.s
- 게을러 지지않기.
- 꾸준하기.
- 과한 목표생성 금지.
- 확실하게가보자. 상반기 화이팅!
 */
public class Program_340213 {
    static int max, opS, opE;

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        opS = cvtStrToInt(op_start);
        opE = cvtStrToInt(op_end);
        max = cvtStrToInt(video_len);

        int T = cvtStrToInt(pos);
        for (String cmd : commands) {
            if (isOpBound(T)) {
                T = opE;
            }

            if (cmd.equals("prev")) {
                if (T - 10 < 0) {
                    T = 0;
                } else {
                    T -= 10;
                }
            } else {
                if (T + 10 > max) {
                    T = max;
                } else {
                    T += 10;
                }
            }
        }

        if (isOpBound(T)) {
            T = opE;
        }
        return cvtIntToStr(T);
    }

    private static boolean isOpBound(int T) {
        return opS <= T && T < opE;
    }

    private static int cvtStrToInt(String str) {
        String[] arr = str.split(":");
        int m = Integer.parseInt(arr[0]);
        int s = Integer.parseInt(arr[1]);

        return (m * 60) + s;
    }

    private static String cvtIntToStr(int num) {

        int m = num / 60;
        int s = num % 60;

        StringBuilder str = new StringBuilder();
        if (m < 10) {
            str.append(0);
        }
        str.append(m).append(":");

        if (s < 10) {
            str.append(0);
        }
        str.append(s);

        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"}));
        System.out.println(solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"}));
        System.out.println(solution("07:22", "04:05", "00:15", "04:07", new String[]{"next"}));
    }
}