package com.htw.githubrepofinder.network.Service;
import com.htw.githubrepofinder.model.RootSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceGitHub {

    String GITHUB_API_BASE_URL = "https://api.github.com/";

    @GET("/search/repositories")
    Observable<RootSearchResponse> getRepositoryList(
            @Query("q") String repositoryName);
}

