package com.yuge.yimcommon.proxy;

import com.alibaba.fastjson.JSONObject;
import com.yuge.yimcommon.exception.YIMException;
import com.yuge.yimcommon.util.HttpClient;
import okhttp3.OkHttpClient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.yuge.yimcommon.enums.StatusEnum.*;

public final class HttpInvokeProxy<T> {

    private String url;
    private OkHttpClient okHttpClient;
    private Class<T> tClass;

    public HttpInvokeProxy( Class<T> tClass,String url, OkHttpClient okHttpClient) {
        this.url = url;
        this.okHttpClient = okHttpClient;
        this.tClass = tClass;
    }

    public T getInstance(){
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{tClass},new ProxyInvocation());
    }

    private class ProxyInvocation implements InvocationHandler{
        //YIMServerResDTO.ServerInfo getYIMServer(LoginReqDTO loginReqDTO);
        ////args：方法调用时传递的参数 loginReqDTO
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            JSONObject jsonObject = new JSONObject();
            String serverUrl =url+"/"+method.getName();

            if(args!=null&& args.length>1)
                throw new YIMException(ARGUMENT_CHECK_FAIL);

            //method.getParameterTypes() LoginReqDTO
            if(method.getParameterTypes().length>0){
                Object para = args[0];
                Class<?> parameterType = method.getParameterTypes()[0];
                for (Field field : parameterType.getDeclaredFields()) {
                    field.setAccessible(true);
                    jsonObject.put(field.getName(),field.get(para));
                }
            }
            return HttpClient.call(okHttpClient, jsonObject.toString(), serverUrl);
        }
    }
}
