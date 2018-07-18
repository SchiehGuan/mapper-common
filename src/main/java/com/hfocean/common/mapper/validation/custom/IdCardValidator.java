package com.hfocean.common.mapper.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by guan.sj on 2018/3/16
 */
public class IdCardValidator implements ConstraintValidator<IsIdCard, String> {

    private String message;

    @Override
    public void initialize(IsIdCard constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.trim().equals("")){
//            message = "身份证不能为空";
            //空字符串不做验证，由用户@NotBlank处理
            return true;
        }else{
            if(IdCardRule.isIdCard(value)){
                return true;
            }else{
                message = "身份证号码格式不正确";
            }
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }

}
