package com.ushier.hospital.illness.web;

import com.ushier.hospital.illness.web.util.DateUtil;

public class Test {
    public static void main(String[] args) {
        System.out.println(DateUtil.getCurrentQuarterStartTime());
        System.out.println(DateUtil.getCurrentQuarterEndTime());
    }
}
