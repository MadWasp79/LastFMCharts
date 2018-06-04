package com.mwhive.lastfmcharts.base;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;
import com.bluelinelabs.conductor.Router;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.di.Injector;
import com.mwhive.lastfmcharts.di.ScreenInjector;
import com.mwhive.lastfmcharts.ui.ScreenNavigator;
import java.util.UUID;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

public abstract class BaseActivity extends AppCompatActivity {

  //This are the key and value for the InstanceState Bundle which will
  // help us to prevent creation of new Activities by Injector.
  private static String INSTANCE_ID_KEY = "instance_id";
  private String instanceId;

  //Since instead of Fragments I use Controller library,
  // Router could be called a substitution for FragmentManager;
  private Router router;

  @Inject ScreenInjector screenInjector;
  @Inject ScreenNavigator screenNavigator;



  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
    } else {
      instanceId = UUID.randomUUID().toString();
    }
    Injector.inject(this);
    setContentView(layoutRes());

    ViewGroup screenContainer = findViewById(R.id.screen_container);
    if (screenContainer == null) {
      throw new NullPointerException("Activity must have a view with id: screen_container");
    }
    router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
    screenNavigator.initWithRouter(router, initialScreen());
    monitorBackStack();
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onBackPressed() {
    if(!screenNavigator.pop()){
      super.onBackPressed();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    screenNavigator.clear();
    if (isFinishing()){
      Injector.clearComponent(this);
    }
  }

  //Since this app will use only one activity, I could have provide there our initial screen (ChartScreenController),
  //but for the sake of re-usability and option to add more activities late we will use abstract method so that every
  //derived activity subclass explicitly specify it starting root Controller.
  protected abstract Controller initialScreen();

  @LayoutRes
  protected abstract int layoutRes();

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(INSTANCE_ID_KEY, instanceId);
  }

  public String getInstanceId() {
    return instanceId;
  }

  public ScreenInjector getScreenInjector() {
    return screenInjector;
  }

  protected void monitorBackStack(){
   router.addChangeListener(new ControllerChangeListener() {
     @Override
     public void onChangeStarted(
         @Nullable Controller to,
         @Nullable Controller from,
         boolean isPush,
         @NonNull ViewGroup container,
         @NonNull ControllerChangeHandler handler) {

     }

     @Override
     public void onChangeCompleted(
         @Nullable Controller to,
         @Nullable Controller from,
         boolean isPush,
         @NonNull ViewGroup container,
         @NonNull ControllerChangeHandler handler) {
        if(!isPush && from != null) Injector.clearComponent(from);
     }
   });
  }
}
