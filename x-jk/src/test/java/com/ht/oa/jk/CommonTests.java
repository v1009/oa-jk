package com.ht.oa.jk;

import org.junit.Test;

public class CommonTests {

    @Test
    public void test() {
        Object status = 1;
        if (status instanceof Long) {
            System.out.println("c");
        }else {
            System.out.println("d");
        }

    }


}
