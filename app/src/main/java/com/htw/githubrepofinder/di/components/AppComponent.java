package com.htw.githubrepofinder.di.components;
import com.htw.githubrepofinder.ViewModel.GitHubViewModel;
import com.htw.githubrepofinder.di.module.AppModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {
    void inject(GitHubViewModel viewModel);
}
