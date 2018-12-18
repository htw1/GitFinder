package com.htw.githubrepofinder.network.procesDialog;
import com.htw.githubrepofinder.model.Items;
import java.util.List;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import static com.htw.githubrepofinder.network.procesDialog.Status.ERROR;
import static com.htw.githubrepofinder.network.procesDialog.Status.LOADING;
import static com.htw.githubrepofinder.network.procesDialog.Status.SUCCESS;

public class ApiResponse {

    public final Status status;

    @Nullable
    public final  List<Items> items;

    @Nullable
    public final Throwable error;

    public ApiResponse(Status status, @Nullable   List<Items> items, @Nullable Throwable error) {
        this.status = status;
        this.items = items;
        this.error = error;
    }

    public static ApiResponse loading( ) {

        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull List<Items> items) {
        return new ApiResponse(SUCCESS, items, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}

