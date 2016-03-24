package com.zhandaAdmin.common.tools;

import java.util.Arrays;

/**
 * Created by admin on 2016/3/24.
 */
public class MathTools {
    public static float binarySearchKey(float[] array, float targetNum) {

        //Object[] array = temp.clone();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        int targetindex = 0;
        int left = 0,right = 0;
        for (right = array.length-1; left!=right;) {
            int midIndex = (right + left)/2;
            float mid = (right - left);
            float midValue = array[midIndex];
            if (targetNum == midValue) {
                return midIndex;
            }

            if(targetNum > midValue){
                left = midIndex;
            }else{
                right = midIndex;
            }

            if (mid <=1) {
                break;
            }
        }
        float rightnum = array[right];
        float leftnum = array[left];
        float ret =  Math.abs((rightnum - leftnum)/2) > Math.abs(rightnum -targetNum) ? rightnum : leftnum;
        System.out.println("和要查找的数："+targetNum+ "最接近的数：" + ret);
        return ret;
    }
}
