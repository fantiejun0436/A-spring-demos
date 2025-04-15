package tech.pdai.springboot.mysql57.mybatis.xml.entity.response;

import lombok.Builder;
import lombok.Data;
import tech.pdai.springboot.mysql57.mybatis.xml.constants.ResponseStatus;

import java.io.Serializable;

@Data
@Builder
public class ResponseResult<T> {

    private long timestamp;

    private String status;

    private String message;

    private T data;

    public static <T> ResponseResult<T> success(){
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data){
        return ResponseResult.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getDescription())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ResponseResult<T> fail(T data, String message){
        return ResponseResult.<T>builder().data(data)
                .message(message)
                .status(ResponseStatus.FAIL.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }
    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }
}
