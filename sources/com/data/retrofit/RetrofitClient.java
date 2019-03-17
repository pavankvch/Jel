package com.data.retrofit;

import android.support.annotation.NonNull;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter;
import retrofit2.Converter$Factory;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static APIService apiService;
    private static Converter<ResponseBody, APIError> errorConverter;

    static class NullOnEmptyConverterFactory extends Converter$Factory {
        private NullOnEmptyConverterFactory() {
        }

        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            type = retrofit.nextResponseBodyConverter(this, type, annotationArr);
            return new Converter<ResponseBody, Object>() {
                public Object convert(ResponseBody responseBody) throws IOException {
                    if (responseBody.contentLength() == 0) {
                        return null;
                    }
                    return type.convert(responseBody);
                }
            };
        }
    }

    private RetrofitClient() {
    }

    public static APIService getAPIService() {
        if (apiService == null) {
            apiService = (APIService) getClient().create(APIService.class);
        }
        return apiService;
    }

    public static APIService getAPIServiceForMultiPart() {
        if (apiService == null) {
            apiService = (APIService) getClientForMultiPart().create(APIService.class);
        }
        return apiService;
    }

    public static Converter<ResponseBody, APIError> getErrorConverter() {
        if (errorConverter == null) {
            errorConverter = getErrorClient().responseBodyConverter(APIError.class, new Annotation[0]);
        }
        return errorConverter;
    }

    public static APIService changeApiBaseUrlBasedOnLanguage() {
        APIService aPIService = (APIService) getClient().create(APIService.class);
        apiService = aPIService;
        return aPIService;
    }

    private static Retrofit getClient() {
        return new Builder().baseUrl(Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/mobile/api/v1/" : "https://jelsat.com/en/mobile/api/v1/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(getOkHttpClientObject()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static Retrofit getClientForMultiPart() {
        return new Builder().baseUrl(Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/mobile/api/v1/" : "https://jelsat.com/en/mobile/api/v1/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(getOkHttpClientObjectFormultipart()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static Retrofit getErrorClient() {
        return new Builder().baseUrl(Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/mobile/api/v1/" : "https://jelsat.com/en/mobile/api/v1/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new NullOnEmptyConverterFactory()).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }

    private static OkHttpClient getOkHttpClientObject() {
        new HttpLoggingInterceptor().setLevel(Level.BODY);
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(45, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        writeTimeout.dns(new EasyDns());
        writeTimeout.addInterceptor(new Interceptor() {
            public final Response intercept(@NonNull Interceptor$Chain interceptor$Chain) throws IOException {
                Request.Builder newBuilder = interceptor$Chain.request().newBuilder();
                if (PrefUtils.getInstance().getUserAccessToken() != null) {
                    newBuilder.addHeader("X-CSRF-Token", PrefUtils.getInstance().getUserAccessToken());
                    newBuilder.addHeader(HttpHeaders.COOKIE, PrefUtils.getInstance().getCookie());
                }
                return interceptor$Chain.proceed(newBuilder.build());
            }
        });
        return writeTimeout.build();
    }

    private static OkHttpClient getOkHttpClientObjectFormultipart() {
        new HttpLoggingInterceptor().setLevel(Level.BODY);
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(30, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS);
        writeTimeout.dns(new EasyDns());
        writeTimeout.addInterceptor(new Interceptor() {
            public final Response intercept(@NonNull Interceptor$Chain interceptor$Chain) throws IOException {
                StringBuilder stringBuilder = new StringBuilder("multipart/form-data; boundary=----");
                stringBuilder.append(System.currentTimeMillis());
                stringBuilder.append("----");
                String stringBuilder2 = stringBuilder.toString();
                Request.Builder newBuilder = interceptor$Chain.request().newBuilder();
                if (PrefUtils.getInstance().getUserAccessToken() != null) {
                    newBuilder.addHeader("X-CSRF-Token", PrefUtils.getInstance().getUserAccessToken());
                    newBuilder.addHeader(HttpHeaders.COOKIE, PrefUtils.getInstance().getCookie());
                    newBuilder.addHeader("Content-Type", stringBuilder2);
                }
                return interceptor$Chain.proceed(newBuilder.build());
            }
        });
        return writeTimeout.build();
    }
}
