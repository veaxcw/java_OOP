package com.chengw.thread.validate;

/**
 * @author chengwei
 */
public class UserValidator implements Validator {
    @Override
    public void doValidate() {
        System.out.println("validate user");
    }
}
