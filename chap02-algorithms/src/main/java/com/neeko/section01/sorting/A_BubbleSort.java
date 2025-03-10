package com.neeko.section01.sorting;

import java.util.Arrays;

/* 버블 정렬
* 인접한 두 요소를 비교하여 크기가 순서대로 정열되지 않았으면 교환하는 방식의 정렬이다.
* 배열 전체를 여러 번 반복하며 각 반복마다 가장 큰 요소가 끝으로 이동한다.
* 시간 복잡도는 0(n^2)이며, 데이터가 거의 정렬된 경우 최선의 경우 0(n)이 될 수도 있다.
* 하지만 교환 연산이 많아 다른 0(n^2) 정렬보다 느릴 수 있다.*/
public class A_BubbleSort {
    /* 문제 : n개의 정수가 주어졌을 때 버블 정렬 알고리즘을 사용하여 오름차순으로 정렬하는 프로그램 작성 */
    public static void soluction(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println((i+1)+"번째 : "+ Arrays.toString(arr));
            for(int j = 0; j < arr.length - i - 1; j++) {
                // 만약 j 인덱스의 값이 j+1 인덱스의 값보다 크다면 오름차순 기준에 맞지 않으므로
                // 교환이 필요하며 내림차순일 경우 반대로 체크하면 됨.
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }

    }

}
