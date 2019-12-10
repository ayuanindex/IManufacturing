package com.lenovo.manufacture;

import java.util.ArrayList;

public class Utils {
    public static <T> void sort(ArrayList<T> a, Com<? super T> com) {
        for (int i = 0; i < a.size() - 1; i++) {
            /*boolean isSort = true;*/
            for (int j = 0; j < a.size() - 1; j++) {
                if (com.compare(a.get(j), a.get(j + 1)) > 0) {
                    /*isSort = false;*/
                    T tmp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, tmp);
                }
            }
            /*判断是否已经排好了*//*
            if (isSort) {
                break;
            }*/
        }
    }

    public interface Com<T> {
        public int compare(T v1, T v2);
    }
}
