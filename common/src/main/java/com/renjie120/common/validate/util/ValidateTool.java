package com.renjie120.common.validate.util;

import com.renjie120.common.validate.annotation.config.ValidateMethod;
import com.renjie120.common.validate.inte.NeedValidateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by lishuiqing on 2017/9/8.
 */

@Service
public class ValidateTool
{   private static final Logger logger = LoggerFactory.getLogger(ValidateTool.class);

    @ValidateMethod
    public void validate(NeedValidateRequest request) throws Exception {

    }
}

/**
 * 使用方法，直接在需要进行form表单校验的service中使用本tool即可。通过捕获异常得到验证失败的提示信息.
 * try {
 validateTool.validate(request);
 } catch (ValidateException e) {
 responseDto.setErrorMessage(e.getErrMessage());
 return responseDto;
 }catch (Exception e) {
 responseDto.setErrorMessage(e.getMessage());
 return responseDto;
 }
 */