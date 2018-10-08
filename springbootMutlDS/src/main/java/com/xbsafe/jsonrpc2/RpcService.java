package com.xbsafe.jsonrpc2;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/jsonRpc")
public interface RpcService {
    public JSONObject test(JSONObject obj);
}
