package com.williamsang.mentalarithmetic.utils;

import java.util.ArrayList;
import java.util.Random;

public class Constants {
    public final static int roundSeconds = 10;

    public static ArrayList<Integer> getRandomSeqArray(int start, int end) {
        Random random = new Random();
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numberList.add(i);
        }
        // 随机生成只包含 1-4 的数组
        ArrayList<Integer> resultArray = new ArrayList<>();
        for (int i = 4; i > 1; i--) {
            int tmp = numberList.remove(random.nextInt(i));
            resultArray.add(tmp);
        }
        resultArray.add(numberList.remove(0));
        return resultArray;
    }
}
