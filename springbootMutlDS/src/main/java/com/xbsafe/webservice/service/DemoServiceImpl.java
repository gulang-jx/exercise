package com.xbsafe.webservice.service;

public class DemoServiceImpl implements DemoService {
    @Override
    public String test(String param) {
        return "webservice demo get param:"+param;
    }
}
