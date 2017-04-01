package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.utils.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anhdt on 4/1/2017.
 */

public class InitService {


    public static InitService _instance;

    private OkHttpClient.Builder httpClient;

    public static InitService getInstance() {
        if (_instance == null) {
            _instance = new InitService();
        }
        return _instance;
    }

    private static Retrofit.Builder builderNormal =
            new Retrofit.Builder()
                    .baseUrl(Constant.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public <S> S createService(Class<S> serviceClass) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header(Constant.AUTH_TOKEN_API_KEY, Constant.AUTH_TOKEN_API_VALUE)
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builderNormal.client(client).build();
        return retrofit.create(serviceClass);
    }

}
