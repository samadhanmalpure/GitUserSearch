package autocomplete.samadhan.com.myapplication.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.network.APICalls;
import autocomplete.samadhan.com.myapplication.network.NetworkCallReponseInterface;
import autocomplete.samadhan.com.myapplication.network.UserService;
import io.reactivex.disposables.CompositeDisposable;

public class UserRepository {

    private UserService userService;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<Resource<UserDetailsWithRepositories>> resourceMutableLiveData = new MutableLiveData<>();

    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public LiveData<Resource<UserDetailsWithRepositories>> getUserDetailsWithRepositoryList(String userId) {

        compositeDisposable.add(userService.getUserDetailsWithReposeObserver(userId, new NetworkCallReponseInterface<UserDetailsWithRepositories>() {
            @Override
            public void onSuccess(UserDetailsWithRepositories response) {
                resourceMutableLiveData.setValue(Resource.success(response));
            }

            @Override
            public void onComplete() {
                // for now, just logging it.
                Log.d(this.getClass().getCanonicalName(),"Taks Completed");
            }

            @Override
            public void onResponseError(Throwable t) {
                t.printStackTrace();
            }
        }));
        return resourceMutableLiveData;
    }
}
