package ctp.sorting.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1766
// 위상정렬 / 난이도 중
public class T1766 {

    private static ArrayList<Integer>[] graph;
    private static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        indegree = new int[N+1];

        for (int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            graph[idx1].add(idx2);
            indegree[idx2]++;
        }
        
        solution();
    }

    private static void solution() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=1; i<indegree.length; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");

            for (int i=0; i<graph[now].size(); i++) {
                int node = graph[now].get(i);
                indegree[node] -= 1;
                if (indegree[node] == 0)
                    queue.add(node);
            }
        }
    }
}
