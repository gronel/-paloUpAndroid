package biz.fastgroup.apps.paloup.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gronel on 26/03/2017.
 */

 public class ServiceInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
    Request request=chain.request();

        if(request.header("No-Authentication")==null){
            request=request.newBuilder()
                    .addHeader("Token","MTMwMzgzMDo4ODkyZDgyNS1jZTQyLTQ0OGEtOTNlOS04ZTI1MWI4YWU3ZDd0c2Fm")
                    .build();
        }
    return chain.proceed(request);
}
}
