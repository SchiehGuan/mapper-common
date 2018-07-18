package com.hfocean.common.mapper.util;

import com.hfocean.common.mapper.validation.RegexConstant;
import com.hfocean.common.mapper.validation.custom.IdCardInfo;
import com.hfocean.common.mapper.validation.custom.IdCardRule;
import com.hfocean.common.mapper.validation.custom.PhoneValidator;

/**
 * Created by guan.sj on 2018/3/16
 */
public class ValidatorUtils {

    public static boolean isUsername(String str) {
        return RegexUtils.match(str, RegexConstant.USERNAME_REG);
    }

    public static boolean isPassword(String str) {
        return RegexUtils.match(str, RegexConstant.PASSWORD_REG);
    }

    public static boolean isPhone(String str) {
        return RegexUtils.match(str, RegexConstant.PHONE_REG);
    }

    public static boolean isIdCard(String str) {
        return IdCardRule.isIdCard(str);
    }


}
