package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1260 DFS와 BFS (https://www.acmicpc.net/problem/1260)
// https://covenant.tistory.com/132?category=727170
public class BFS {
    // 큐를 사용하고, 재귀적으로 동작하지 않음
    // 최단 거리 문제에 적합
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());	// 정점의 개수
        int m = Integer.parseInt(st.nextToken());	// 간선의 개수
        int v = Integer.parseInt(st.nextToken());	// 탐색을 시작할 정점의 번호
        String[] info = new String[m];
        for (int i=0; i<m; i++) {
            info[i] = br.readLine();
        }

        // Array를 사용하여 간선 정보 저장
        solution_Array(n, m, v, info);
        // List를 사용하여 간선 정보 저장
        solution_List(n, m, v, info);
    }

    private static void solution_Array(int n, int m, int v, String[] info) {
        int[][] array = new int[n+1][n+1];
        boolean[] visited = new boolean[n+1];

        for (int i=0; i<m; i++) {
            String[] split = info[i].split(" ");
            int v1 = Integer.parseInt(split[0]);
            int v2 = Integer.parseInt(split[1]);

            array[v1][v2] = 1;
            array[v2][v1] = 1;
        }

        BFS_Array(v, array, visited);
        System.out.println();
    }

    private static void BFS_Array(int v, int[][] array, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        int len = array.length-1;

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print(v + " ");    // 거쳐가는 경로 모두 출력

            for (int i=1; i<=len; i++) {
                if (array[v][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    private static void solution_List(int n, int m, int v, String[] info) {
        List<Integer>[] nodeList = new ArrayList[n+1];
        boolean[] visited = new boolean[n+1];

        for (int i=1; i<=n; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            String[] split = info[i].split(" ");
            int v1 = Integer.parseInt(split[0]);
            int v2 = Integer.parseInt(split[1]);
            nodeList[v1].add(v2);
            nodeList[v2].add(v1);
        }

        for (int i=1; i<=n; i++) {
            Collections.sort(nodeList[i]);
        }

        BFS_List(v, nodeList, visited);
    }

    private static void BFS_List(int v, List<Integer>[] nodeList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print(v + " ");    // 거쳐가는 경로 모두 출력

            for (Integer node : nodeList[v]) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
    }
}
