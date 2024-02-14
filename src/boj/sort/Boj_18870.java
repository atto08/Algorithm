package boj.sort;

import java.io.*;
import java.util.*;

/*
1) 시간초과
list.indexOf 메서드는 O(N)의 시간 복잡도를 가짐.
==> 이분탐색을 이용
 */
public class Boj_18870 {
    static int[] sortedNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            set.add(numbers[i]);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        sortedNumbers = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sortedNumbers[i] = list.get(i);
        }

        for (int i = 0; i < N; i++) {
            if (i != N - 1) {
                bw.write(binarySearch(numbers[i], 0, list.size() - 1) + " ");
            } else {
                bw.write(binarySearch(numbers[i], 0, list.size() - 1) + "");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static int binarySearch(int key, int low, int high) {
        int mid;

        if (low <= high) {
            mid = (low + high) / 2;

            if (key == sortedNumbers[mid]) {
                return mid;
            } else if (key < sortedNumbers[mid]) {
                return binarySearch(key, low, mid - 1);
            } else {
                return binarySearch(key, mid + 1, high);
            }
        }
        return -1;
    }
}
