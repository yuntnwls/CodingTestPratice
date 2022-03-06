package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬 : 사이클 없는 방향 그래프(DAG)의 모든 노드를 방향성에 거스르지 않도록 순서대로 나열
// DFS나 큐를 이용해서 구현 가능
// 시간복잡도 : O(V+E) = 정점의 수 + 간선의 수 만큼 소요
public class TopologySort {

    private static int v;   // 노드 수 (최대 100,000개라고 가정)
    private static int e;   // 간선 수
    // 모든 노드에 대한 진입 차수
    private static int[] indegree = new int[100001];
    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i=0; i<=v; i++) {
            graph.add(new ArrayList<>());
        }

        // 방향 그래프의 모든 간선 정보 입력 받기
        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.get(v1).add(v2);  // v1 -> v2로 이동 가능
            indegree[v2] += 1;      // 진입 차수 증가
        }
        
        runTopologySort();
    }

    private static void runTopologySort() {
        // 알고리즘 수행 결과를 담을 리스트(큐에 들어간 순서를 저장할 결과 리스트)
        List<Integer> result = new ArrayList<>();
        // 큐 라이브러리를 통해 진행
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 노드를 큐에 삽입
        for (int i=1; i<=v; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);    // 큐에 나오는대로 결과에 추가
            for (int i=0; i<graph.get(now).size(); i++) {
                int node = graph.get(now).get(i);
                // 해당 노드에서 나가는 간선 제거
                // 제거되는 간선이 진입하는 간선인 노드의 간선 갯수-1
                indegree[node] -= 1;
                if (indegree[node] == 0) {
                    queue.add(node);
                }
            }
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i=0; i<result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}

// https://loosie.tistory.com/161
// https://www.youtube.com/watch?v=aOhhNFTIeFI&t=1259s&ab_channel=동빈나