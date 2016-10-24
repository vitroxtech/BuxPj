package squaring.vitrox.buxpj.Network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import squaring.vitrox.buxpj.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.buxpj.Support.Config;


@Module(includes = ApplicationModule.class)
public class RestApiServiceModule {

    @Provides
    @Singleton
    public RestApiService RestApiServiceModule(OkHttpClient client) {

        String demoUrl = Config.REST_BASEURL;
        //Jackson Mapper and deserializer
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Retrofit.Builder()
                .baseUrl(demoUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(client)
                .build()
                .create(RestApiService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }



}