package autocomplete.samadhan.com.myapplication.network;

import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import io.reactivex.disposables.Disposable;

public interface UserService {
    Disposable getUserDetailsWithReposeObserver(String userId, NetworkCallResponseInterface<UserDetailsWithRepositories> taks_completed);
}
