package autocomplete.samadhan.com.myapplication.network;

import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import io.reactivex.disposables.Disposable;

public class UserServiceImpl implements UserService {

    @Override
    public Disposable getUserDetailsWithReposeObserver(String userId, NetworkCallReponseInterface<UserDetailsWithRepositories> taks_completed) {
        return APICalls.getInstance().getUserDetailsWithReposeObserver(userId, taks_completed);
    }
}
