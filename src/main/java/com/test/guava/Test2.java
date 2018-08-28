package com.test.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.Set;

public class Test2 {
    public static void main(String[] args) {

        Multimap<Integer, String> multiMapTmp = ArrayListMultimap.create();
        multiMapTmp.put(null, "123");
        System.out.println(multiMapTmp);

        long start = System.currentTimeMillis();

        String url = "'http://qq.e-mallchina.com/3rdparty/coupon/get?sn_code=' + discountId + '&timestamp=' + timestamp + '&channelid=3&validity_time=' + endTime + '&sign=' + upper(md5('C81E728D9D4C2F636F067F89CC14862C' + 'channelid3sn_code' + discountId + 'timestamp' + timestamp + 'validity_time' + endTime + 'C81E728D9D4C2F636F067F89CC14862C'))";

        Iterable<String> matchTmps = Splitter.on('+')
                .trimResults()
                .omitEmptyStrings()
                .split(url);

        //System.out.println("整理前：" + matchTmps);
        Set<String> matchs = Sets.newHashSet();
        for (String i : matchTmps) {
            if (i.contains("\'") || i.contains("(") || i.contains(")")) {
                continue;
            }
            matchs.add(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
        System.out.println("整理后：" + matchs);
    }
}
