package com.yy.core.utils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ListSortUtil: 集合排序工具类
 *
 * @Author: YangYang
 * @Date: 2021/2/20 10:41
 */
public class ListSortUtil {

    private static final String DESC = "desc";
    private static final String ASC = "asc";
    /**
     * @param targetList 目标排序List
     * @param sortField 排序字段(实体类属性名)
     * @param sortMode 排序方式（asc or desc）
     */
    private static <T> List<T> sort(List<T> targetList, final String sortField, final String sortMode) {
        Collections.sort(targetList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写
                    String methodStr="get"+sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");
                    Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);

                    Integer a = Integer.parseInt(method1.invoke(((T) obj1), null).toString());
                    Integer b = Integer.parseInt(method2.invoke(((T) obj2), null).toString());

                    if (sortMode != null && DESC.equals(sortMode)) {
                        retVal = b.compareTo(a); // 倒序
                    } else {
                        retVal = a.compareTo(b); // 正序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
        return targetList;
    }

    public static <T> List<T> sortByAsc(List<T> list,final String sortField){
        return sort(list,sortField,ASC);
    }

    public static <T> List<T> sortByDesc(List<T> list, final String sortField){
        return sort(list,sortField,DESC);
    }
}
