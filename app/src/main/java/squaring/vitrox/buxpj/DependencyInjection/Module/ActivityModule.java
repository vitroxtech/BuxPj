package squaring.vitrox.buxpj.DependencyInjection.Module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import squaring.vitrox.buxpj.Support.DataListHelper;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Module
public class ActivityModule {
    final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Context activityContext() {
        return mActivity;
    }


}
