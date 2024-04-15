package com.foo.learning.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> {
    private String errorMessage;
    private String status = "success";
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }
}
