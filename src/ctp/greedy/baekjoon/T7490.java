package ctp.greedy.baekjoon;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/7490
// 난이도 중 : 재귀
public class T7490 {

    private static int N;
    private static List<String> operList = new ArrayList<>();
    private static List<String> answerList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        int tc = Integer.parseInt(br.readLine());
        for (int t=0; t<tc; t++) {
            N = Integer.parseInt(br.readLine());
            operList.clear();
            answerList.clear();

            solution(1);

            for (String answer : answerList) {
                int result = (int) engine.eval(answer.replaceAll(" ", ""));
                if (result == 0) {
                    System.out.println(answer);
                }
            }

            System.out.println();
        }
    }

    private static void solution(int num) {
        if (num == N) {
            answerList.add(getAnswer());
            return;
        }

        operList.add(" ");
        solution(num+1);
        operList.remove(operList.size()-1);

        operList.add("+");
        solution(num+1);
        operList.remove(operList.size()-1);

        operList.add("-");
        solution(num+1);
        operList.remove(operList.size()-1);
    }

    private static String getAnswer() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(index++));
        for (int i=0; i<N-1; i++) {
            sb.append(operList.get(i));
            sb.append(Integer.toString(index++));
        }
        return sb.toString();
    }
}
