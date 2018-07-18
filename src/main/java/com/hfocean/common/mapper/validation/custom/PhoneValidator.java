package com.hfocean.common.mapper.validation.custom;

import com.hfocean.common.mapper.util.RegexUtils;
import com.hfocean.common.mapper.validation.RegexConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by guan.sj on 2018/3/16
 */
public class PhoneValidator implements ConstraintValidator<IsPhone, String> {

    private String message;

    @Override
    public void initialize(IsPhone constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().equals("")) {
//            message = RegexConstant.PHONE_REG_NULL;
            //空字符串不做验证，由用户@NotBlank处理
            return true;
        } else {
            if (RegexUtils.match(value, RegexConstant.PHONE_REG)) {
                return true;
            } else {
                message = RegexConstant.PASSWORD_REG_RULE;
            }
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }

}
