package com.xiaolyuh;

/**
 * @author yuhao.wang3
 * @since 2019/11/30 17:09
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int i = 0;
        System.gc();
    }
}
