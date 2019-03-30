package autocomplete.samadhan.com.myapplication.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;

import autocomplete.samadhan.com.myapplication.data.Resource;
import autocomplete.samadhan.com.myapplication.data.UserRepository;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.network.APICalls;
import autocomplete.samadhan.com.myapplication.network.NetworkCallReponseInterface;
import autocomplete.samadhan.com.myapplication.util.UserRepodetailsBottomSheetDialog;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresentor implements MainPresenterInterface {

    MainViewInterface mainViewInterface;
    private String TAG = "MainPresenter";
    private UserRepository userRepository;
    private MutableLiveData<String> mutableUserId = new MutableLiveData<>();

    private final MediatorLiveData<UserDetailsWithRepositories> detailsMediator = new MediatorLiveData<>();

    private final LiveData<Resource<UserDetailsWithRepositories>> detailsResourceMediator =
            Transformations.switchMap(mutableUserId, new Function<String, LiveData<Resource<UserDetailsWithRepositories>>>() {
                @Override
                public LiveData<Resource<UserDetailsWithRepositories>> apply(String input) {
                    return userRepository.getUserDetailsWithRepositoryList(input);
                }
            });

    public final LiveData<UserDetailsWithRepositories> detailsLiveData = detailsMediator;

    public MainPresentor(final MainViewInterface mainViewInterface, final UserRepository userRepository) {
        this.mainViewInterface = mainViewInterface;
        this.userRepository = userRepository;

        detailsMediator.addSource(detailsResourceMediator, new Observer<Resource<UserDetailsWithRepositories>>() {
            @Override
            public void onChanged(@Nullable Resource<UserDetailsWithRepositories> userDetailsWithRepositoriesResource) {
                if (userDetailsWithRepositoriesResource.status == Resource.Status.SUCCESS) {
                    //  mainViewInterface.displayUserDetailsWithRepos(userDetailsWithRepositoriesResource.data);
                    detailsMediator.setValue(userDetailsWithRepositoriesResource.data);
                }
            }
        });
    }

    @Override
    public void getUserDetailsWithRepositoryList(String userId) {
        mutableUserId.setValue(userId);
    }
}
