package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://blog.naver.com/ndb796/221230994142
// 크루스칼 알고리즘 : 적은 비용으로 모든 노드를 연결(최소 비용 신장 트리만들기)
// 모든 노드를 연결할 떄 가장 적은 비용으로 연결하기
// 필요한 간선 수 = 노드 수 - 1
// 간선을 거리가 짧은 순서대로 그래프에 포함시킨다.
// 모든 간선 정보를 오름차순을 정렬한 뒤 비용이 적은 간선부터 포함 => 사이클이 형성되지 않도록 주의!
// 포함시키기 전에 사이클 테이블을 확인 => union-find로 찾기
public class Kruskal {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[m];
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(v1, v2, c);
        }
        
        kruskal(nodes, n);
    }

    private static void kruskal(Node[] nodes, int n) {
        // 간선 비용으로 오름차순 정렬
        Arrays.sort(nodes);

        // 각 정점이 포함된 그래프가 어디인지 저장
        int[] parent = new int[n+1];
        for (int i=1; i<=n; i++) {
            parent[i] = i;
        }

        // 사이클여부 확인 후 거리의 합 구하기
        int sum = 0;
        for (Node node : nodes) {
            // 두 노드가 연결되어있는지 확인
            if (!findParent(parent, node.v1, node.v2)) {
                sum += node.cost;
                // 두 노드를 연결
                unionParent(parent, node.v1, node.v2);
            }
        }

        System.out.println(sum);
    }

    private static int getParent(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        else
            return  parent[x] = getParent(parent, parent[x]);
    }

    private static void unionParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    // 연결되어있는지 확인 = 같은 부모를 가지고있는지 확인
    private static boolean findParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b;
    }

    static class Node implements Comparable<Node> {
        int v1;
        int v2;
        int cost;

        public Node(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}

/*
Sample Input
7 11
1 7 12
1 4 28
1 2 67
1 5 17
2 4 24
2 5 62
3 5 20
3 6 37
4 7 13
5 6 45
5 7 73

Output
123
 */