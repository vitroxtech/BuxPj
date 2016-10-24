package squaring.vitrox.buxpj;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import squaring.vitrox.buxpj.DependencyInjection.Component.AppComponent;
import squaring.vitrox.buxpj.DependencyInjection.Component.DaggerAppComponent;
import squaring.vitrox.buxpj.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.buxpj.Model.Product;

public class App extends Application {

    private AppComponent mAppComponent;
    private Product SelectedProduct;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    protected Product getProduct(){return SelectedProduct;}
    protected void setProduct(Product product){SelectedProduct= product;}

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
