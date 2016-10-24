package squaring.vitrox.buxpj.DependencyInjection.Component;

import dagger.Component;
import squaring.vitrox.buxpj.DependencyInjection.ActivityScope;
import squaring.vitrox.buxpj.DetailsActivity;
import squaring.vitrox.buxpj.MainActivity;


@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent{

    void inject(MainActivity activity);

    void inject(DetailsActivity activity);

}