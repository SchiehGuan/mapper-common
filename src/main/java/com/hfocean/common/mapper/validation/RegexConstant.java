package com.hfocean.common.mapper.validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则表达式
 * Created by guan.sj on 2018/3/16
 */
public interface RegexConstant {
    /**
     * 手机正则表达式
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除4的任意数
     * 17+除9的任意数
     * 147
     */
    String PHONE_REG = "^((13[0-9])|(15[^4])|(18[^4])|(17[0-8])|(147))\\d{8}$";
    String PHONE_REG_RULE = "手机号码格式错误";
    String PHONE_REG_NULL = "手机号码为空";

    /**
     * 邮箱验证
     */
    String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    String EMAIL_MSG = "邮箱格式错误";

    /**
     * 性别格式验证
     */
    String SEX_MF = "^[MF]{1}$";
    String SEX_MSG = "性别格式错误(M/F)";

    /**
     * 用户名正则表达式6-16位
     * 1. 必须字母开头
     * 2. 字母加数字
     */
    String USERNAME_REG = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,15}$";
    String USERNAME_REG_RULE = "用户名必须首字母数字6~16位";


    /**
     * 密码正则表达式8-16位
     * 1. 不能全部是数字
     * 2. 不能全部是字母
     * 3. 必须是数字或字母
     */
    String PASSWORD_REG = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    String PASSWORD_REG_RULE = "密码必须字母数字8-16位";


    public static void main(String[] args) {
        Pattern p = null;
        Matcher m = null;
        boolean flg = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("------用户注册------");
        //匹配用户名
        System.out.println("用户名：   ---（由字母数字下划线组成且开头必须是字母，不能超过16位）");
        String name = sc.nextLine();
        p = Pattern.compile(RegexConstant.USERNAME_REG);
        m = p.matcher(name);
        flg = m.matches();
        System.out.println(flg);
    }
}
