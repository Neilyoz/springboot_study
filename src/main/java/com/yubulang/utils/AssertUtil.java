package com.yubulang.utils;

import com.yubulang.exceptions.ParamsException;

public class AssertUtil {
    public static void isTrue(Boolean flag, String message) {
        if (flag) {
            throw new ParamsException(message);
        }
    }
}
