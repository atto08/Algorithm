package boj.implementation;

import java.util.Scanner;
/*
재귀함수가 뭔가요? - 실5
소요 시간: 30분

재귀함수의 원리를 다시 공부함..
생각을 잘못하고 있었음
 */
public class Boj_17478 {
    static int N;
    static String[] sentence = {
            "\"재귀함수가 뭔가요?\"",
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
            "라고 답변하였지."};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        recursion(0);

    }

    private static void recursion(int n) {

        if (n == N) {
            printSentence(n, sentence[0]);
            printSentence(n, "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            printSentence(n, sentence[4]);
            return;
        }
        for (int i = 0; i < 4; i++) printSentence(n, sentence[i]);

        recursion(n + 1);
        printSentence(n, sentence[4]);
    }

    private static void printSentence(int n, String str) {
        for (int i = 0; i < n; i++) System.out.print("____");
        System.out.println(str);
    }
}
