package com.mwhive.lastfmcharts.di;


import android.app.Activity;
import com.bluelinelabs.conductor.Controller;
import com.mwhive.lastfmcharts.base.BaseActivity;
import com.mwhive.lastfmcharts.base.BaseController;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.AndroidInjector.Factory;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */
@ActivityScope

public class ScreenInjector {

  private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
  private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();


  @Inject
  ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors) {
    this.screenInjectors = screenInjectors;
  }

  void inject(Controller controller) {
    if (!(controller instanceof BaseController)) {
      throw new IllegalArgumentException("Controller must be an instance of BaseController!");
    }

    String instanceId = controller.getInstanceId();
    if(cache.containsKey(instanceId)) {
      cache.get(instanceId).inject(controller);
      return;
    }

    AndroidInjector.Factory<Controller> injectorFactory =
        (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
    AndroidInjector<Controller> injector = injectorFactory.create(controller);
    cache.put(instanceId, injector);
    injector.inject(controller);
  }

  void clear(Controller controller) {
    cache.remove(controller.getInstanceId());
  }

  static ScreenInjector get(Activity activity) {
    if (!(activity instanceof BaseActivity)) {
      throw new IllegalArgumentException("Controller must be hosted by BaseActivity!");
    }
    return ((BaseActivity) activity).getScreenInjector();
  }

}
