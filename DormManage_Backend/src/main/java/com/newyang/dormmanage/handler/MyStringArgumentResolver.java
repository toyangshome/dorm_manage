package com.newyang.dormmanage.handler;

import com.newyang.dormmanage.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import java.util.Arrays;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/18 17:36
 */
@Slf4j
public class MyStringArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
    @Override
    protected NamedValueInfo createNamedValueInfo (MethodParameter parameter) {
        return new NamedValueInfo("", false, ValueConstants.DEFAULT_NONE);
    }

    @Override
    protected Object resolveName (String name, MethodParameter parameter, NativeWebRequest request) {
        String[] param = request.getParameterValues(name);
        if (param == null) {
            return null;
        }

        log.info(Arrays.toString(param));
        if (StringUtil.isEmpty(param[0])) {
            return null;
        }


        return param[0];
    }

    @Override
    public boolean supportsParameter (MethodParameter parameter) {
        return parameter.getParameterType().equals(Object.class);
    }

}
