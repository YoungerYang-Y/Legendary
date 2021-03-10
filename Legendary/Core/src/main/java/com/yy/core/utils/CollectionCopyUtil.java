package com.yy.core.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * CollectionCopyUtil:
 *
 * @Author: YangYang
 * @Date: 2021/2/25 20:50
 */
public class CollectionCopyUtil {

//    /**
//     * Description：List集合的copyProperties
//     *
//     * @author: yangyang
//     * @date: 2021/2/25 21:12
//     * @param list Source List 源数组
//     * @param <K> Target Class 目标对象
//     * @return: java.util.List
//     */
//    public static <T,K> List<K> copyProperties(List<T> list, K k) {
//
//        if (CollectionUtils.isEmpty(list)) {
//            return new ArrayList<>();
//        }
//        else{
//            List<K> resultList = new ArrayList<>();
//            for (T t : list){
//                K copyT = ObjectUtil.clone(k);
//                BeanUtil.copyProperties(t,copyT);
//                resultList.add(copyT);
//            }
//            return resultList;
//        }
//    }


    /**
     * Description：List集合的copyProperties
     *
     * @author: yangyang
     * @date: 2021/2/25 21:12
     * @param sources Source List 源数组
     * @param tClass Target Class 目标对象
     * @return: java.util.List
     */
    public static <T> List copyProperties(List<T> sources, Class tClass) {

        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<>();
        }
        else{
            List resultList = new ArrayList();
            for (T t : sources){
                T copyT = (T) BeanUtils.instantiateClass(tClass);
                BeanUtil.copyProperties(t,copyT);
                resultList.add(copyT);
            }
            return resultList;
        }
    }
}
