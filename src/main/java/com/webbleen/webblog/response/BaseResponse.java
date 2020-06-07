package com.webbleen.webblog.response;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;

public abstract class BaseResponse extends HashMap<String, Object> {

    protected BaseResponse(boolean success) {
        super.put("success", success);
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
