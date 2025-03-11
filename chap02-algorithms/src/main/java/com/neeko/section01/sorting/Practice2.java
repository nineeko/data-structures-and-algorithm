package com.neeko.section01.sorting;

import java.util.Arrays;

/* TimSort 구현하기
 * 삽입 정렬과 병합 정렬의 장점을 결합한 하이브리드 정렬 알고리즘
 * 작은 배열(예: 길이 32 이하)은 삽입 정렬로 정렬한 후, 병합 정렬 방식으로 전체 배열을 병합한다.
 * */


public class Practice2 {
    // TimSort에서 사용할 RUN의 크기
    private static final int RUN = 32;

    public static void timSort(int[] arr) {
        int n = arr.length;

        // RUN 크기의 작은 배열을 삽입 정렬로 정렬
        for (int i = 0; i < n; i += RUN) {
            insertionSort(arr, i, Math.min(i + RUN - 1, n - 1));
        }
        System.out.println("삽입 정렬 수행 후의 arr : " + Arrays.toString(arr));

        // 정렬된 RUN 단위의 배열을 병합
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
                System.out.println("left : " + left + " mid : " + mid + " right : " + right);
                System.out.println("merge 실행 후의 arr : " + Arrays.toString(arr));
            }
        }
    }

    /* 삽입 정렬 */
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /* 병합 정렬 */
    private static void merge(int[] arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];



        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < len1) {
            arr[k++] = leftArr[i++];
        }

        while (j < len2) {
            arr[k++] = rightArr[j++];
        }
    }
}
