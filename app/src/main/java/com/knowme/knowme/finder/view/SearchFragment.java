package com.knowme.knowme.finder.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knowme.knowme.R;
import com.knowme.knowme.finder.adapter.UserAdapterRecyclerView;
import com.knowme.knowme.finder.repository.IGitHubRepository;
import com.knowme.knowme.model.UserGitHub;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    @BindView(R.id.refresh_view_users)
    SwipeRefreshLayout refreshViewUsers;

    @BindView(R.id.recycler_view_users)
    RecyclerView recyclerViewUser;

    @BindView(R.id.search)
    SearchView searchView;

    private EditText searchEditText;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        loadData();

        return view;
    }

    private void initViews(View view) {

        searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
//        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
//        searchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        refreshViewUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewUser.setAdapter(null);
                loadData();
            }
        });

        refreshViewUsers.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorDarkGray,
                R.color.colorBlue,
                R.color.colorRed
        );
    }

    private void loadData() {

        //GitHubRepository gitHubRepository = new GitHubRepository();
        //gitHubRepository.getRepositories("octocat");

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://api.github.com/")
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build();

        IGitHubRepository service = retrofit.create(IGitHubRepository.class);

        // Call<List<UserGitHub>> call = service.getRepositories("mojombo");

        Call<List<UserGitHub>> call = service.getUsers();

        call.enqueue(new Callback<List<UserGitHub>>() {
            @Override
            public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                switch (response.code()){
                    case 200:
                        List<UserGitHub> data = response.body();

                        UserAdapterRecyclerView userAdapterRecyclerView = new UserAdapterRecyclerView((ArrayList<UserGitHub>) data, R.layout.recycler_item_user_github, getActivity());
                        recyclerViewUser.setAdapter(userAdapterRecyclerView);

                        refreshViewUsers.setRefreshing(false);
                        Log.w("Message", data.toString());
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<UserGitHub>> call, Throwable t) {
                Log.e("error", t.toString());
                refreshViewUsers.setRefreshing(false);
            }
        });
    }

}
