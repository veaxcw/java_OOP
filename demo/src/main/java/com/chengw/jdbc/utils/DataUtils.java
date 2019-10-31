package com.chengw.jdbc.utils;

import com.chengw.jdbc.po.SysUserBean;
import com.chengw.jdbc.po.UserBean;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chengw
 */
public class DataUtils {

    /**
     * 模拟插入
     **/
    public static List<UserBean> simulateData(int num) {
        List<UserBean> users = new ArrayList<>(num);

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());

        while (num-- > 0) {
            UserBean user = new UserBean();

            user.setName(RandomStringUtils.randomAlphabetic(5));
            user.setCode(RandomStringUtils.randomAlphabetic(5));
            user.setSex(random.nextInt(2) / 2 == 1 ? "male" : "female");
            users.add(user);
        }

        return users;
    }

    public static List<SysUserBean> simulateSysUsers(int num) {
        List<SysUserBean> users = new ArrayList<>(num);

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        long i = 0L;
        while (num-- > 0) {
            SysUserBean sysUser = new SysUserBean();

            sysUser.setId(++i);
            sysUser.setName(RandomStringUtils.randomAlphabetic(5));
            sysUser.setEmail(RandomStringUtils.randomAlphabetic(3) + "@gmail.com");
            sysUser.setSex(random.nextInt(2) / 2 == 1 ? "male" : "female");
            sysUser.setPhone(generatePhone());
            sysUser.setPassword("");
            sysUser.setUserName(sysUser.getEmail());
            users.add(sysUser);
        }

        return users;
    }

    private static Long generatePhone() {
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        StringBuilder phone = new StringBuilder("182");
        for(int i = 0; i < 9; i++){
            phone.append( Math.abs(random.nextInt () % 10));
        }
        return Long.valueOf(phone.toString());
    }

}
