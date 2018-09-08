package io.github.ovso.psytest.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.psytest.ui.main.fragment.VideoFragment;
import io.github.ovso.psytest.ui.main.fragment.di.VideoFragmentModule;

@Module(includes = { AndroidSupportInjectionModule.class }) public abstract class FragmentBuilder {
  @ContributesAndroidInjector(modules = { VideoFragmentModule.class })
  abstract VideoFragment contributeRepoFragment();
}