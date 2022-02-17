package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 1260 DFS와 BFS (https://www.acmicpc.net/problem/1260)
// https://covenant.tistory.com/132?category=727170
public class DFS {
    // 스택 혹은 재귀 사용
    // 찾으려는 노드가 깊은 단계에 있는 경우 BFS보다 빠름
    // 최단 경로라는 보장이 없음
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());	// 정점의 개수
        int m = Integer.parseInt(st.nextToken());	// 간선의 개수
        int v = Integer.parseInt(st.nextToken());	// 탐색을 시작할 정점의 번호

        int[][] array = new int[n+1][n+1];
        boolean[] visited = new boolean[n+1];

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            array[v1][v2] = 1;
            array[v2][v1] = 1;
        }
        
        // DFS 재귀로 구현
        DFS_Recursion(v, array, visited);
        System.out.println();

        Arrays.fill(visited, false);

        // DFS 스택으로 구현
        DFS_Stack(v, array, visited, true);
    }

    private static void DFS_Recursion(int v, int[][] array, boolean[] visited) {
        int len = array.length-1;
        visited[v] = true;
        System.out.print(v + " ");

        for (int i=0; i<=len; i++) {
            if (array[v][i] == 1 && !visited[i]) {
                DFS_Recursion(i, array, visited);
            }
        }
    }

    private static void DFS_Stack(int v, int[][] array, boolean[] visited, boolean flag) {
        int len = array.length - 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        System.out.print(v + " ");

        while (!stack.isEmpty()) {
            v = stack.peek();
            flag = false;

            for (int i=1; i<=len; i++) {
                if (array[v][i] == 1 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                    System.out.print(i + " ");

                    flag = true;
                    break;
                }
            }
            if (!flag) {
                // 연결된 간선이 없고, 방문하지 않은 정점을 찾지 못한 경우
                // 더이상 탐색할 수 없으므로 스택에서 제외
                stack.pop();
            }
        }
    }
}
