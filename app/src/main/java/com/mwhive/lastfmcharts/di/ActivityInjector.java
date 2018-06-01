package com.mwhive.lastfmcharts.di;



import android.app.Activity;
import android.content.Context;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.base.BaseActivity;
import com.mwhive.lastfmcharts.base.MyApplication;
import com.mwhive.lastfmcharts.home.MainActivityComponent;
import dagger.android.AndroidInjector;
import dagger.android.AndroidInjector.Factory;
import dagger.android.DispatchingAndroidInjector;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by MadWasp79 on 01-Jun-18.
 * "Upgraded" version of {@link dagger.android.DispatchingAndroidInjector} with cache and only for Activities.
 * This will give us ability to retain same instance of activities through configuration changes
 */


public class ActivityInjector {

  //Map which will hold Key: ActivityInjector, Value: correspondent instance of Activity:
  private final Map<Class<? extends Activity>, Provider<Factory<? extends Activity>>> activityInjectors;

  //Cache where we will hold existed copy of activity instance
  private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();

  @Inject
  ActivityInjector(Map<Class<? extends Activity>,
      Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors) {
    this.activityInjectors = activityInjectors;
  }


  @SuppressWarnings("unchecked")
  void inject(Activity activity) {

    //requested activity should be an instance of BaseActivity
    if (!(activity instanceof BaseActivity)) {
      throw new IllegalArgumentException(activity.getString(R.string.exception_instance_not_a_base_activity));
    }

    String instanceId = ((BaseActivity) activity).getInstanceId();
    //checks if we have instance of requested activity in cache, return it if yes, else create new.
    if(cache.containsKey(instanceId)){
      ((AndroidInjector<Activity>) cache.get(instanceId)).inject(activity);
      return;
    }

    AndroidInjector.Factory<Activity> injectorFactory =
        (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();

    AndroidInjector<Activity> injector = injectorFactory.create(activity);
    cache.put(instanceId, injector);
    injector.inject(activity);
  }

  //clear cache on Activity's finish
  void clear(Activity activity) {
    if (!(activity instanceof BaseActivity)) {
      throw new IllegalArgumentException(activity.getString(R.string.exception_instance_not_a_base_activity));
    }
    cache.remove(((BaseActivity) activity).getInstanceId());
  }

  //Retrieve this injector from outside
  static ActivityInjector get(Context context) {
    return ((MyApplication) context.getApplicationContext()).getActivityInjector();
  }

}
