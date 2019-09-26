package com.osg.myapplication.http;


import android.text.TextUtils;
import android.util.Log;

import com.osg.myapplication.MyApplication;
import com.osg.myapplication.utils.Constant;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;
import java.net.URLDecoder;


public class RequestDataChangeHandler implements HttpRequestHandler
{
    private static final String tag = RequestDataChangeHandler.class.getSimpleName();

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse,
            HttpContext httpContext) throws HttpException, IOException
    {

        String target = URLDecoder.decode(httpRequest.getRequestLine().getUri(),
            Constant.ENCODING);

        if (target.contains("pingjia"))
            MyApplication.getInstance().fireDataChangeListener(target, "test");
    }


}
