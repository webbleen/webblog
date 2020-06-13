package com.webbleen.webblog.response;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

public class SingleResponse extends BaseResponse {

    public SingleResponse() {
        super(true);
    }

    public SingleResponse(Object data) {
        super(true);
        super.put("data", data);
    }

    public SingleResponse(JSONObject data) {
        super(true);
        for (Entry<String, Object> e : data.entrySet()) {
            super.put(e.getKey(), e.getValue());
        }
    }

    public SingleResponse add(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
