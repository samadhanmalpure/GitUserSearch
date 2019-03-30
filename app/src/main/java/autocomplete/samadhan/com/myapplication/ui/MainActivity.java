package autocomplete.samadhan.com.myapplication.ui;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import autocomplete.samadhan.com.myapplication.R;
import autocomplete.samadhan.com.myapplication.adapter.RepositoryListAdapter;
import autocomplete.samadhan.com.myapplication.data.UserRepository;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.model.UserRepos;
import autocomplete.samadhan.com.myapplication.network.UserServiceImpl;
import autocomplete.samadhan.com.myapplication.util.UserRepodetailsBottomSheetDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainViewInterface, RepositoryListAdapter.DisplayDialogInterface{

    private MainPresentor mainPresentor;

    @BindView(R.id.username)
    TextInputEditText edtUserName;

    @BindView(R.id.btnSearch)
    AppCompatButton btnSearch;

    @BindView(R.id.imgProfilePic)
    AppCompatImageView imgProfilePic;

    @BindView(R.id.txtuserName)
    AppCompatTextView txtUserName;

    @BindView(R.id.list)
    RecyclerView listofRepositories;

    private UserRepository userRepository = new UserRepository(new UserServiceImpl());

    private RepositoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        imgProfilePic.setVisibility(View.GONE);
        mainPresentor = new MainPresentor(MainActivity.this, userRepository);
        mainPresentor.detailsLiveData.observe(this, new Observer<UserDetailsWithRepositories>() {
            @Override
            public void onChanged(@Nullable UserDetailsWithRepositories userDetailsWithRepositories) {
                displayUserDetailsWithRepos(userDetailsWithRepositories);
            }
        });
    }

    @OnClick(R.id.btnSearch)
    void onSearchButtonClick() {
        InputMethodManager imm =
                (InputMethodManager) edtUserName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        mainPresentor.getUserDetailsWithRepositoryList(edtUserName.getText().toString());
    }

    @Override
    public void displayUserDetailsWithRepos(UserDetailsWithRepositories userDetailsWithRepositories) {
        imgProfilePic.setVisibility(View.VISIBLE);
        txtUserName.setText(userDetailsWithRepositories.getUserDetails().getName());
        Glide.with(this).load(userDetailsWithRepositories.getUserDetails().getProfilePicUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(imgProfilePic);
        adapter = new RepositoryListAdapter(userDetailsWithRepositories, MainActivity.this);
        listofRepositories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listofRepositories.setAdapter(adapter);
    }

    @Override
    public void displayDialog(UserRepos userRepos) {
        UserRepodetailsBottomSheetDialog myBottomSheetDialog = new UserRepodetailsBottomSheetDialog(this, userRepos);
        myBottomSheetDialog.setCanceledOnTouchOutside(true);
        myBottomSheetDialog.show();
    }
}
