package com.test.guava;

import com.google.common.base.CaseFormat;

public class Test3 {
    public static void main(String[] args) {
        String p = "accToken";
        String name = "match" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, p);
        System.out.println(name);
    }
}
