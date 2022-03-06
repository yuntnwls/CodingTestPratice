package ctp.sorting.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2252
// 위상 정렬
public class T2252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 비교 횟수

        // ArrayList<ArrayList<Integer>> 로 선언시 메모리 초과 에러 발생
        ArrayList<Integer>[] graph = new ArrayList[N];
        int[] indegree = new int[N];

        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            graph[a].add(b);
            indegree[b] += 1;
        }

        solution(N, indegree, graph);
    }

    private static void solution(int n, int[] indegree, ArrayList<Integer>[] graph) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i=0; i<n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            // queue에서 빼낸 데이터를 따로 저장하면 메모리 초과가 발생하므로 바로 출력
            System.out.print((now+1) + " ");
            for (int i=0; i<graph[now].size(); i++) {
                int node = graph[now].get(i);
                indegree[node] -= 1;
                if (indegree[node] == 0) {
                    queue.add(node);
                }
            }
        }
    }
}
