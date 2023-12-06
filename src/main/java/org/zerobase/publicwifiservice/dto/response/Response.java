package org.zerobase.publicwifiservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response<T> of(String resultCode, T result) {
        return new Response<>(resultCode, result);
    }

    public static <T> Response<T> error(String errorCode) {
        return Response.of(errorCode, null);
    }

    public static <Void> Response<Void> success() {
        return Response.of("SUCCESS", null);
    }
    public static <Void> Response<Void> fail() {
        return Response.of("FAIL", null);
    }

    public static <T> Response<T> success(T result) {
        return Response.of("SUCCESS", result);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"resultCode\":" + "\"" + resultCode + "\"," +
                    "\"result\":" + null +
                    "}";
        }

        return "{" +
                "\"resultCode\":" + "\"" + resultCode + "\"," +
                "\"result\":" + "\"" + result + "\"," +
                "}";
    }
}
