package com.mcxfate.crm;

import com.mcxfate.crm.utils.MD5Util;
import com.mcxfate.crm.utils.UUIDUtil;
import org.junit.Test;

public class TestDemo01 {

    @Test
    public void test01(){

        String str = "123456";
        String new1 = MD5Util.getMD5(str);
        System.out.println(new1);

        String uuid = UUIDUtil.getUUID();
        System.out.println(uuid);

    }

}
