package squaring.vitrox.buxpj.DependencyInjection.Component;

import android.content.Context;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import squaring.vitrox.buxpj.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.buxpj.Network.RestApiService;
import squaring.vitrox.buxpj.Network.RestApiServiceModule;
import squaring.vitrox.buxpj.Network.SocketConnection;
import squaring.vitrox.buxpj.Support.DataListHelper;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RestApiServiceModule.class})
public interface AppComponent {

    Context appContext();

    DataListHelper getDefaultData();

    RestApiService apiService();

    com.squareup.okhttp.OkHttpClient okHttpClient();

    SocketConnection getSocketConnection();

}
