package com.mwhive.lastfmcharts.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;
import com.mwhive.lastfmcharts.di.Injector;


/**
 * Created by MadWasp79 on 01-Jun-18.
 */

public abstract class BaseController extends Controller {

  private boolean injected = false;

  @Override
  protected void onContextAvailable(@NonNull Context context) {

    // Controller instances are retained across config changes,
    // so this method can be called more than once. This makes
    // sure we don't waste any time injecting more than once,
    // though technically it wouldn't change functionality.
    if(!injected) {
      Injector.inject(this);
      injected = true;
    }

    super.onContextAvailable(context);
  }
}
