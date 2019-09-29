package com.chengw.jdbc.utils;

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

}
