package com.htw.githubrepofinder.view;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import com.htw.githubrepofinder.R;
import com.htw.githubrepofinder.ViewModel.GitHubViewModel;
import com.htw.githubrepofinder.adapter.AdapterGitHub;
import com.htw.githubrepofinder.callback.RecyclerOnClickListener;
import com.htw.githubrepofinder.databinding.UiMainFragment;
import com.htw.githubrepofinder.model.Items;
import com.htw.githubrepofinder.network.NetworkChecker;
import com.htw.githubrepofinder.network.procesDialog.ApiResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment implements LifecycleOwner, RecyclerOnClickListener {

    List<Items> itemsModel;
    RecyclerView.LayoutManager mLayoutManager;
    AdapterGitHub adapter;

    private LifecycleRegistry mLifecycleRegistry;
    GitHubViewModel viewModel;
    UiMainFragment binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,false);

        //Lifecycle
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        // Recycler
        adapter = new AdapterGitHub(getActivity(),this );
        mLayoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders
                .of(getActivity())
                .get(GitHubViewModel.class);
        observer(viewModel);
    }

    private void observer(GitHubViewModel viewModel) {
        viewModel.responseLiveData.observe(getActivity(), apiResponse -> consumeResponse(apiResponse));
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status){

            case LOADING:
                binding.setIsLoading(true);
                break;

            case SUCCESS:
                binding.setIsLoading(false);
                loadOnlineView();
                adapter.setAdapterList(apiResponse.items);
                itemsModel=apiResponse.items;

            case ERROR:
                if (!NetworkChecker.checkInternetConnection(getActivity())) {
                    binding.setIsLoading(false);
                    loadOfflineView();
                    Toast.makeText(getActivity(), getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }

    @BindingAdapter("visibleGone")
    public static void show(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void loadOfflineView() {
        binding.noConnection.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
    }
    private void loadOnlineView() {
        binding.noConnection.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) throws ParseException {

        Items item = itemsModel.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        String lastUpdate =item.getmUpdatedAt();

        //Format Date
        SimpleDateFormat fromServer = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("EEEE,  d MMMM yyyy");
        Date date = null;
        date = fromServer.parse(lastUpdate);
        String outputDateStr =myFormat.format(date);

        dialog.setMessage(Html.fromHtml(
                "<b>" + "Login: " + "</b>" + "<br>" + item.getmOwner().getmLogin() +
                        "<br>" + "<br>" + "<b>" + "Description:" + "</b>" + "<br>" + item.getmDescription() +
                        "<br>" + "<br>" + "<b>" + "Last Update:" + "</b>" + "<br>" + outputDateStr));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }
}
