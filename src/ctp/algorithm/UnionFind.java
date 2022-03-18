package ctp.algorithm;

// https://blog.naver.com/ndb796/221230967614
// Union-Find 알고리즘 (합집합 찾기)
// = 서로소 집합(Disjoint-Set) 알고리즘
// 노드 번호와 부모노드를 관리
// 부모를 합칠 때 일반적으로 더 작은 값 쪽이 부모가 됨 => Union
// 재귀 함수를 사용하여 연결된 가장 작은 부모를 부모 노드로 설정
// 두개의 노드의 부모를 확인하여 현재 같은 집합(같은 부모)에 있는지 확인 => Find
public class UnionFind {

    public static void main(String[] args) {
        int[] parent = new int[11];
        for (int i=1; i<=10; i++) {
            parent[i] = i;  // 처음엔 자기자신을 부모로 저장
        }

        union(parent, 1, 2);
        union(parent, 2, 3);
        union(parent, 3, 4);
        union(parent, 5, 6);
        union(parent, 6, 7);
        union(parent, 7, 8);

        System.out.println(String.format("1과 5가 연결되어있나요? %b", find(parent, 1, 5)));
        union(parent, 1, 5);
        System.out.println(String.format("1과 5가 연결되어있나요? %b", find(parent, 1, 5)));
    }

    private static int getParent(int[] parent, int x) {
        if (parent[x] == x) // 부모가 자기자신이라면 가장 작은 값이므로 반환
            return x;
        // 부모의 부모를 찾기위해 재귀 실행
        return parent[x] = getParent(parent, parent[x]);
    }

    private static void union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    private static boolean find(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a == b)
            return true;
        return false;
    }
}
