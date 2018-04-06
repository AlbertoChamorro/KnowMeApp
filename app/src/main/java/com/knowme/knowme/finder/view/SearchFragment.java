package com.knowme.knowme.finder.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.knowme.knowme.R;
import com.knowme.knowme.finder.adapter.UserAdapterRecyclerView;
import com.knowme.knowme.finder.presenter.IUserGitHubPresenter;
import com.knowme.knowme.finder.presenter.UserGitHubPresenter;
import com.knowme.knowme.model.UserGitHub;
import com.knowme.knowme.util.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements IUserGitHubView{


    @BindView(R.id.refresh_view_users)
    SwipeRefreshLayout refreshViewUsers;

    @BindView(R.id.recycler_view_users)
    RecyclerView recyclerViewUser;

    @BindView(R.id.search)
    SearchView searchView;

    private EditText searchEditText;

    private IUserGitHubPresenter userGitHubPresenter;

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

        this.userGitHubPresenter = new UserGitHubPresenter(this);
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
    @Override
    public void loadData() {
        this.setAdapter(null);
        this.userGitHubPresenter.getUsers();
    }
    @Override
    public void setAdapter(ArrayList<UserGitHub> data) {
        recyclerViewUser.setAdapter(new UserAdapterRecyclerView(data, R.layout.recycler_item_user_github, getActivity()));
    }

    @Override
    public void showRefreshView(Boolean state) {
        refreshViewUsers.setRefreshing(state);
    }

    @Override
    public void showMessageError(String error) {
        Helper.showSnackBar(this.getView(), error, Snackbar.LENGTH_LONG,
                getResources().getColor(R.color.colorWhite),
                getResources().getColor(R.color.colorPrimary));
    }
}
