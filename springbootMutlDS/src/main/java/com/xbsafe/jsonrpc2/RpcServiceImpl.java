package com.xbsafe.jsonrpc2;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class RpcServiceImpl implements RpcService {
    @Override
    public JSONObject test(JSONObject obj) {
        System.out.println("get param :" +obj);
        JSONObject result = new JSONObject();
        result.put("name","hello world");
        return result;
    }
}
