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
    monitorBackStack();
    super.onCreate(savedInstanceState);
  }


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

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (isFinishing()){
      Injector.clearComponent(this);
    }
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
