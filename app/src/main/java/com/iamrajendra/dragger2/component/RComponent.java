package com.iamrajendra.dragger2.component;


import com.iamrajendra.dragger2.MainActivity;
import com.iamrajendra.dragger2.dragger2.module.SharedPrefModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface RComponent {
    void inject(MainActivity activity);
}
