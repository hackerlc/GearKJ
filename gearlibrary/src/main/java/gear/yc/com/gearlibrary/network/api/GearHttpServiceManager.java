package gear.yc.com.gearlibrary.network.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 使用这个类可以快速的创建一个全局的Retrofit引用
 * Created by YichenZ on 2016/4/13 14:41.
 * @version 2.0 由单例模式更换为静态类方式
 */
public class GearHttpServiceManager {

    private static String sBaseUrl;

    private static Retrofit sRetrofit;

    public static void setBaseUrl(String url){
        sBaseUrl=url;
    }

    public static String getBaseUrl(){
        return sBaseUrl;
    }

    public static Retrofit build(String url,OkHttpClient okHttpClient){
        sBaseUrl = url;
        if(sBaseUrl == null) {
            new IllegalAccessException("url is not null");
        }
        if(okHttpClient == null){
            new IllegalAccessException("okHttpClient is not null");
        }
        sRetrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return getRetrofit();
    }

    public static Retrofit getRetrofit() {
        if(sRetrofit==null) {
            new IllegalAccessException("sRetrofit is null");
        }
        return sRetrofit;
    }

    /**
     * 可以设置自己定义的Retrofit
     * @param ret
     */
    public static void setRetrofit(Retrofit ret){
        sRetrofit = ret;
    }

}
