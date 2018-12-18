package com.htw.githubrepofinder.ViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.htw.githubrepofinder.MainApplication;
import com.htw.githubrepofinder.network.Service.ServiceGitHub;
import com.htw.githubrepofinder.network.procesDialog.ApiResponse;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GitHubViewModel extends ViewModel {
    private ServiceGitHub serviceGitHub;
    public MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<ApiResponse>();

    @Inject
    Retrofit api;

    public GitHubViewModel() {

        MainApplication.appComponent.inject(this);
        serviceGitHub = api.create(ServiceGitHub.class);
        loadProjectRepository("intel");
    }

    public void loadProjectRepository(String query) {

        serviceGitHub.getRepositoryList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(items -> responseLiveData.setValue(ApiResponse.success(items.getItems())),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                );
    }
}
