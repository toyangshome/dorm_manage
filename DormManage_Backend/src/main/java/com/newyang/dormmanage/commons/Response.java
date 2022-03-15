package com.newyang.dormmanage.commons;


import com.newyang.dormmanage.utils.DateUtil;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 23:11
 */

@Data
@ToString
public class Response<T> implements Serializable {
    private static final long serialVersionUID = - 3744671466041200838L;
    private Integer code;
    private String message;
    private String timestamp = DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
    private T data;

    private Response (T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    private Response (){}
    private Response (int code, String message) {
        this.message = message;
        this.code = code;
    }

    public static <T> Response<T> success (T data, int code, String message) {
        return new Response<>(data, code, message);
    }

    public static <Void> Response<Void> success () {
        Response<Void> result = new Response<Void>();
        result.setStatus(ResStatus.SUCCESS);
        return result;
    }

    public static <T> Response<T> success (T data) {
        Response<T> result = new Response<>();
        result.setData(data);
        result.setStatus(ResStatus.SUCCESS);
        return result;
    }

    public static <Void> Response<Void> failure (ResStatus resultCode) {
        Response<Void> result = new Response<>();
        result.setStatus(resultCode);
        return result;
    }

    public static <T> Response<T> failure (ResStatus resultCode, T data) {
        Response<T> result = new Response<>();
        result.setStatus(resultCode);
        result.setData(data);
        return result;
    }

    public static <T> Response<T> failure (Integer code, String message, T data) {
        Response<T> result = new Response<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <Void> Response<Void> failure (Integer code, String message) {
        Response<Void> result = new Response<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public void setStatus (ResStatus resp) {
        this.message = resp.message();;
        this.code = resp.code();
    }
}
