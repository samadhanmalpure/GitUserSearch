package autocomplete.samadhan.com.myapplication.ui;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import autocomplete.samadhan.com.myapplication.data.UserRepository;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;

public class MainPresenterTests {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private MainPresenter presenter;
    private UserDetailsWithRepositories actualData;
    private UserRepository userRepository;
    private TestUserService userService = new TestUserService();

    private MainViewInterface view = new MainViewInterface() {

        @Override
        public void displayUserDetailsWithRepos(UserDetailsWithRepositories userDetailsWithRepositories) {

        }
    };

    @Before
    public void setUp() {
        userRepository = new UserRepository(userService);
        presenter = new MainPresenter(userRepository);
        presenter.detailsLiveData.observeForever(new Observer<UserDetailsWithRepositories>() {
            @Override
            public void onChanged(
                    @Nullable UserDetailsWithRepositories userDetailsWithRepositories
            ) {
                actualData = userDetailsWithRepositories;
            }
        });
    }

    @Test
    public void getUserDetailsWithRepositoryList() {
        // Arrange
        String input = "EricDw";
        String expected = input;

        // Act
        presenter.getUserDetailsWithRepositoryList(input);
        userService.sendData();
        String actual = actualData.getUserDetails().getName();


        // Assert
        Assert.assertEquals(expected, actual);

    }
}