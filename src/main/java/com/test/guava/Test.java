package com.test.guava;

import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        Set<String> oneSet = Sets.newHashSet("chen", "lei", "java");
        Set<String> twoSet = Sets.newHashSet("chen", "lei", "hadoop");

        Long start = System.nanoTime();
        Sets.SetView<String> diffSetHandle = Sets.difference(oneSet, twoSet);//是得到左边中不同或者特有的元素，若无，则返回长度为0的集合
        Long end = System.nanoTime();
        System.out.println(end - start);

        Long start2 = System.nanoTime();
        Set<String> result2 = Sets.difference(oneSet, twoSet);//交集
        Long end2 = System.nanoTime();
        System.out.println(end2 - start2);

        System.out.println("Set的不同元素：" + result2);
        System.out.println("Set的不同元素：" + diffSetHandle);

        Set<String> diffImmutable = diffSetHandle.immutableCopy();//返回一个不可变的左边Set中特有元素集合的Set拷贝
        Iterator iter = diffSetHandle.iterator();
        while (iter.hasNext()) {
            System.out.println("Set的不同元素：" + iter.next().toString());
        }
        Sets.SetView<String> commonSet = Sets.intersection(oneSet, twoSet);
        Set<String> commonImmutable = commonSet.immutableCopy();//返回一个不可变的2个Set中共同元素集合的Set拷贝
    }
}
