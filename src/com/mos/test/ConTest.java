package com.mos.test;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.mos.utils.DBUtils;
import org.junit.Test;

import java.sql.Connection;

public class ConTest {

    @Test
    public void connTest() {
        Connection connection = DBUtils.getConnection();
        if(connection != null) {
            System.out.println("连接成功");
        }
    }

    @Test
    public void maxNum() {
        String a = "151231252334643";
        String b = "532423634264352345";

        //长度
        int maxLen = 0;
        if(a.length() < b.length()) {
            maxLen = a.length();
        } else {
            maxLen = b.length();
        }
        char[] arrA = new char[maxLen];
        arrA = a.toCharArray();
        char[] arrB = new char[maxLen];
        arrB = b.toCharArray();
        char[] sumArr = new char[maxLen];

        for(int i = maxLen-1; i >= 0; i--) {
            System.out.print(arrA[i]);
          //  System.out.println(arrB[i]);
        }
        System.out.println("");
        System.out.println(arrA.length);
        System.out.println(arrA[0]);

    }

}
