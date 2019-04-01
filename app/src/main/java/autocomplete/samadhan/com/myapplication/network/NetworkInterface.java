package autocomplete.samadhan.com.myapplication.network;

import java.util.List;

import autocomplete.samadhan.com.myapplication.model.UserDetails;
import autocomplete.samadhan.com.myapplication.model.UserRepos;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkInterface {
    @GET("users/{user_id}")
    Observable<UserDetails> getUserDetails(@Path("user_id") String userId);

    @GET("users/{user_id}/repos")
    Observable<List<UserRepos>> getUserRepos(@Path("user_id") String userId);
}
