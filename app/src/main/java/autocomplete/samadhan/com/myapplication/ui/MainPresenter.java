package autocomplete.samadhan.com.myapplication.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;

import autocomplete.samadhan.com.myapplication.data.Resource;
import autocomplete.samadhan.com.myapplication.data.UserRepository;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;

public class MainPresenter implements MainPresenterInterface {

    private final MutableLiveData<String> mutableUserId = new MutableLiveData<>();
    {
        mutableUserId.setValue("");
    }

    private final MediatorLiveData<UserDetailsWithRepositories> detailsMediator =
            new MediatorLiveData<>();

    final LiveData<UserDetailsWithRepositories> detailsLiveData = detailsMediator;

    MainPresenter(final UserRepository userRepository) {

        LiveData<Resource<UserDetailsWithRepositories>> detailsResourceMediator =
                Transformations.switchMap(mutableUserId,
                        new Function<String, LiveData<Resource<UserDetailsWithRepositories>>>() {
                            @Override
                            public LiveData<Resource<UserDetailsWithRepositories>> apply(String input) {
                                return userRepository.getUserDetailsWithRepositoryList(input);
                            }
                        });

        detailsMediator.addSource(detailsResourceMediator, new Observer<Resource<UserDetailsWithRepositories>>() {
            @Override
            public void onChanged(@Nullable Resource<UserDetailsWithRepositories> userDetailsWithRepositoriesResource) {
                if (userDetailsWithRepositoriesResource != null
                        && userDetailsWithRepositoriesResource.data.getUserDetails().getName() != null
                        &&  userDetailsWithRepositoriesResource.data.getUserDetails().getProfilePicUrl() != null
                        && userDetailsWithRepositoriesResource.status == Resource.Status.SUCCESS)
                    detailsMediator.setValue(userDetailsWithRepositoriesResource.data);
            }
        });
    }

    @Override
    public void getUserDetailsWithRepositoryList(String userId) {
        if (userId != null) mutableUserId.setValue(userId);
    }
}
