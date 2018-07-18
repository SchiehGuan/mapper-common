package com.hfocean.common.mapper.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guan.sj on 2018/3/16
 */
public class RegexUtils {

    public static boolean match(String str, String pattern) {
        boolean flag = false;
        if (!StringUtils.isEmpty(str)) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            flag = m.matches();
        }
        return flag;
    }

}
