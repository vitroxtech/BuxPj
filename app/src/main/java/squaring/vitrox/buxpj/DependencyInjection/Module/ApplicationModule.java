package squaring.vitrox.buxpj.DependencyInjection.Module;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import squaring.vitrox.buxpj.App;
import squaring.vitrox.buxpj.Network.SocketConnection;
import squaring.vitrox.buxpj.Support.DataListHelper;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public DataListHelper getmockdata(){return new DataListHelper();}


    @Provides
    @Singleton
    public SocketConnection getSocketConnection()
    {
      return new SocketConnection();
    }



}
