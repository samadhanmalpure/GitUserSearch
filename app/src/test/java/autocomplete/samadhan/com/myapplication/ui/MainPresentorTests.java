package autocomplete.samadhan.com.myapplication.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import autocomplete.samadhan.com.myapplication.data.Resource;
import autocomplete.samadhan.com.myapplication.data.UserRepository;
import autocomplete.samadhan.com.myapplication.model.UserDetails;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.model.UserRepos;
import autocomplete.samadhan.com.myapplication.network.NetworkCallReponseInterface;

import static org.junit.Assert.*;

public class MainPresentorTests {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private MainPresentor presenter;
    private UserDetailsWithRepositories actualData;
    private UserRepository userRepository;

    private MainViewInterface view = new MainViewInterface() {
        @Override
        public void displayUserDetailsWithRepos(UserDetailsWithRepositories userDetailsWithRepositories) {
            actualData = userDetailsWithRepositories;
        }
    };

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepository(new TestUserService(new NetworkCallReponseInterface<UserDetailsWithRepositories>() {
            @Override
            public void onSuccess(UserDetailsWithRepositories response) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onResponseError(Throwable t) {

            }
        }));
        presenter = new MainPresentor(view, userRepository);
    }

    @Test
    public void getUserDetailsWithRepositoryList() {
        // Arrange
        String input = "EricDw";
        String expected = input;

        // Act
        presenter.getUserDetailsWithRepositoryList(input);
        String actual = actualData.getUserDetails().getName();

        // Assert
        Assert.assertEquals(expected, actual);

    }
}