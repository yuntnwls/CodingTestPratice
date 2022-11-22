package ctp.sorting.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1655
// 우선순위 큐
public class T1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 입력한 값의 작은 값을 담을 공간
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        // 입럭한 값의 큰 값을 담을 공간
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // StringBuilder로 출력하지 않으면 시간 초과 발생
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++)
        {
            int num = Integer.parseInt(br.readLine());

            // 최대/최소 우선순위 큐의 크기가 값다면 최대힙에 숫자 입력
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
            } else {
                // 같지 않다면 최소힙에 숫자 입력
                minHeap.add(num);
            }

            // 두 힙이 비어있지 않고, 최대 힙의 peek값이 최소 힙의 peek값보다 큰 경우 peek 값을 서로 변경
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(minHeap.poll());
            }

            // 최대 힙의 peek 값 출력
            sb.append(maxHeap.peek() + "\n");
        }
        System.out.println(sb);
    }
}

// https://gh402.tistory.com/32
// https://moonsbeen.tistory.com/193