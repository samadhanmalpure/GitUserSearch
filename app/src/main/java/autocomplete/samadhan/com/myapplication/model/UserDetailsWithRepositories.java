package autocomplete.samadhan.com.myapplication.model;

import java.util.List;

public class UserDetailsWithRepositories {

    private UserDetails userDetails;
    private List<UserRepos> userReposList;

    public UserDetailsWithRepositories(UserDetails userDetails, List<UserRepos> userReposList) {
        this.userDetails = userDetails;
        this.userReposList = userReposList;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<UserRepos> getUserReposList() {
        return userReposList;
    }

    public void setUserReposList(List<UserRepos> userReposList) {
        this.userReposList = userReposList;
    }
}
