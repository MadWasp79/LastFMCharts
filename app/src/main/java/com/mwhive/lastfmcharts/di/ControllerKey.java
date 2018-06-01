package com.mwhive.lastfmcharts.di;


import com.bluelinelabs.conductor.Controller;
import dagger.MapKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {

  Class<? extends Controller> value();

}
