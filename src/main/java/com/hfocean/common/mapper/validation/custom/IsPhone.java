package com.hfocean.common.mapper.validation.custom;

import com.hfocean.common.mapper.validation.RegexConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 手机号码格式验证
 * Created by guan.sj on 2018/3/16
 */
@Constraint(validatedBy = PhoneValidator.class)
@Target(java.lang.annotation.ElementType.FIELD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface IsPhone {
    String message() default RegexConstant.PASSWORD_REG_RULE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
