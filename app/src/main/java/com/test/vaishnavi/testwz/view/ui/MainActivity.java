package com.test.vaishnavi.testwz.view.ui;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.LinearLayout;

import com.test.vaishnavi.testwz.R;
import com.test.vaishnavi.testwz.repository.model.Photos;
import com.test.vaishnavi.testwz.util.InternetUtil;
import com.test.vaishnavi.testwz.view.adapter.PhotosAdapter;
import com.test.vaishnavi.testwz.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PhotosAdapter adapter;
    List<Photos> photosList;
    RecyclerView recyclerView;
    MainViewModel viewModel;
    ProgressDialog progress;
    CoordinatorLayout layout;
    private static final String LIST_STATE = "listState";
    private Parcelable recyclerViewstate = null;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = findViewById(R.id.search);
        photosList = new ArrayList<>();

        //set up recycler view
        recyclerView = findViewById(R.id.recycler_view);
        layout = findViewById(R.id.layout_linear);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //set up search view
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                progress = new ProgressDialog(MainActivity.this);
                progress.setMessage(getString(R.string.loading));
                progress.show();
                setQuery(query);
                recyclerView.invalidate();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Initialise View Model in the Layout
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
    }

    @VisibleForTesting
    private void setQuery(String query) {
        if(isNetworkAvailable()) {
            //subscribe to LiveData
            viewModel.getDataList(query).observe(this, new Observer<List<Photos>>() {
                @Override
                public void onChanged(@Nullable List<Photos> photosList) {
                    progress.dismiss();
                    recyclerView.invalidate();
                    adapter = new PhotosAdapter(photosList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }

    private boolean isNetworkAvailable() {
        if(InternetUtil.isNetworkAvailable(MainActivity.this)) {
            return true;
        }else{
            if(progress.isShowing()){
                progress.dismiss();
            }
            showErrorMessage(layout,getString(R.string.no_network_message));
            return false;
        }
    }

    private void showErrorMessage(View view, String message) {
        Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        recyclerViewstate = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, recyclerViewstate);

    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (recyclerViewstate != null)
        recyclerViewstate = state.getParcelable(LIST_STATE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewstate != null) {
            layoutManager.onRestoreInstanceState(recyclerViewstate);
        }
    }

}
