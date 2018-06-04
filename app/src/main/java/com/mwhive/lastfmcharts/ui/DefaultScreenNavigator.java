package com.mwhive.lastfmcharts.ui;


import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.mwhive.lastfmcharts.di.ActivityScope;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 02-Jun-18.
 * Default implementation of Screens navigation
 */
@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

  private Router router;

  @Inject
  DefaultScreenNavigator() {

  }


  //overriding this method gave us ability to preserve same instance of controller on screen orient.change.
  @Override
  public void initWithRouter(Router router, Controller rootController) {
    this.router = router;
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(rootController));
    }
  }

  @Override
  public boolean pop() {
    return router!=null && router.handleBack();
  }

  @Override
  public void clear() {
    router = null;
  }
}
