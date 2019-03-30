package autocomplete.samadhan.com.myapplication.ui;

import java.util.Collections;

import autocomplete.samadhan.com.myapplication.model.UserDetails;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.model.UserRepos;
import autocomplete.samadhan.com.myapplication.network.NetworkCallReponseInterface;
import autocomplete.samadhan.com.myapplication.network.UserService;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TestUserService implements UserService {

    public NetworkCallReponseInterface<UserDetailsWithRepositories> responseListener;

    DisposableObserver<UserDetailsWithRepositories> disposable;

    public TestUserService(final NetworkCallReponseInterface<UserDetailsWithRepositories> responseListener) {
        this.responseListener = responseListener;
        disposable = new DisposableObserver<UserDetailsWithRepositories>() {
            @Override
            public void onNext(UserDetailsWithRepositories userDetailsWithRepositories) {
                UserDetails userDetails = new UserDetails();
                userDetails.setName("EricDw");
                userDetailsWithRepositories.setUserDetails(userDetails);
                responseListener.onSuccess(userDetailsWithRepositories);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public Disposable getUserDetailsWithReposeObserver(String userId, final NetworkCallReponseInterface<UserDetailsWithRepositories> taks_completed) {
        return disposable;
    }

    public void sendData() {
        disposable.onNext(new UserDetailsWithRepositories(
                new UserDetails(),
                Collections.<UserRepos>emptyList()
        ));
    }
}
