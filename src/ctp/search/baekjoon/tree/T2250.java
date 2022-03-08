package ctp.search.baekjoon.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2250
// 트리, 구현 / 난이도 중
public class T2250 {

    private static int maxLevel = 0;// 최대 레벨
    private static Node[] nodes;
    private static int[] levelMax;  // index 레벨에서 가장 큰 값
    private static int[] levelMin;  // index 레벨에서 가장 작은 값
    private static int current = 1; // 현재 순회 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int root = 0;
        nodes = new Node[N+1];
        levelMax = new int[N+1];
        levelMin = new int[N+1];

        for (int i=1; i<=N; i++) {
            nodes[i] = new Node(i, -1, -1);
            levelMax[i] = 0;
            levelMin[i] = N;
        }

        StringTokenizer st = null;
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[idx].left = left;
            nodes[idx].right = right;

            if (left != -1)
                nodes[left].parent = idx;
            if (right != -1)
                nodes[right].parent = idx;
        }

        for (int i=1; i<=N; i++) {
            if (nodes[i].parent == -1)
                root = i;
        }

        // 중위순회
        inOrder(nodes[root], 1);

        int resultLevel = 1;
        int resultWidth = levelMax[1] - levelMin[1] + 1;
        int width = 0;
        for (int i=2; i<=maxLevel; i++) {
            width = levelMax[i] - levelMin[i] + 1;
            if (resultWidth < width) {
                resultWidth = width;
                resultLevel = i;
            }
        }

        System.out.println(resultLevel + " " + resultWidth);
    }

    private static void inOrder(Node node, int level) {
        maxLevel = Math.max(maxLevel, level);

        if (node.left != -1)
            inOrder(nodes[node.left], level+1);

        levelMin[level] = Math.min(levelMin[level], current);
        levelMax[level] = Math.max(levelMax[level], current);
        current++;

        if (node.right != -1)
            inOrder(nodes[node.right], level + 1);
    }

    static class Node {
        int index = -1;
        int parent = -1;
        int left = -1;
        int right = -1;

        public Node(int index, int left, int right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }
}
