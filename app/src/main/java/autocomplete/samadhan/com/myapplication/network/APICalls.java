package autocomplete.samadhan.com.myapplication.network;

import java.util.ArrayList;
import java.util.List;

import autocomplete.samadhan.com.myapplication.model.UserDetails;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.model.UserRepos;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class APICalls {

    private static APICalls instance;

    private APICalls() {
        instance = this;
    }

    public static synchronized APICalls getInstance() {
        if (instance == null) {
            instance = new APICalls();
        }
        return instance;
    }

    private Observable<UserDetailsWithRepositories>  getUserDetailsWithRepoList(String userId) {
        Observable<UserDetails> getUserDetailsObservable = NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getUserDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, UserDetails>() {
                    @Override
                    public UserDetails apply(Throwable throwable) throws Exception {
                        return new UserDetails();
                    }
                });

        Observable<List<UserRepos>> getUserReposListObservable =  NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getUserRepos(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, List<UserRepos>>() {
                    @Override
                    public List<UserRepos> apply(Throwable throwable) throws Exception {
                        return new ArrayList<>();
                    }
                });

        Observable<UserDetailsWithRepositories> combine;


         return combine = Observable.zip(getUserDetailsObservable, getUserReposListObservable,
                new BiFunction<UserDetails, List<UserRepos>, UserDetailsWithRepositories>() {
                    @Override
                    public UserDetailsWithRepositories apply(UserDetails userDetailsResponse, List<UserRepos> userReposList) throws Exception {
                        return new UserDetailsWithRepositories(userDetailsResponse, userReposList);
                    }
                });

    }

    public DisposableObserver<UserDetailsWithRepositories> getUserDetailsWithReposeObserver(String userId, final NetworkCallReponseInterface<UserDetailsWithRepositories> callback) {
        return getUserDetailsWithRepoList(userId).subscribeWith(new DisposableObserver<UserDetailsWithRepositories>() {
            @Override
            public void onNext(UserDetailsWithRepositories userDetailsWithRepositories) {
                callback.onSuccess(userDetailsWithRepositories);
            }

            @Override
            public void onError(Throwable e) {
               callback.onResponseError(e);
            }

            @Override
            public void onComplete() {
                //for now just logging it.
                callback.onComplete();
            }
        });
    }
}
