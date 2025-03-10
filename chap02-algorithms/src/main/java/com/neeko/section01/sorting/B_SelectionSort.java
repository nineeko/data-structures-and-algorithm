package com.neeko.section01.sorting;

import java.util.Arrays;

/* 선택 정렬
* 배열에서 최소값을 찾아 맨 앞과 교환하는 방식의 정렬이다.
* 배열을 여러 번 반복하면서, 선택한 요소와 나머지 요소들을 비교하여 정렬한다.
* 시간 복잡도는 0(n^2)이며, 정렬을 수행하는 과정에서 교환 횟수가 적은 편이다.
* 하지만 정렬이 어느정도 되어 있어도 시간 복잡도는 변하지 않아 비효율적이다. */
public class B_SelectionSort {
    /* 문제 : n개의 정수가 주어졌을 때 선택 정렬 알고리즘을 사용하여 오름차순으로 정렬하는 프로그램 작성 */

        public static void solution(int[] arr) {
            for (int i = 0; i < arr.length - 1; i++) {
                // 가장 작은 값의 인덱스 찾기
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) { // j는 i+1부터 시작
                    if (arr[j] < arr[minIndex]) { // 최소값 찾기
                        minIndex = j;
                    }
                }

                // 선택된 최소값을 i번째 요소와 교환
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;

                // 정렬 진행 상태 출력
                System.out.println((i + 1) + "번째 : " + Arrays.toString(arr));
            }
        }

}
