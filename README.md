# GitUserSearch

This is an Android app which communicates with the public Github API in order to display information about a specific user.

The app is accepting a github user’s id as input and display the specified user’s avatar and name.

For each public repository owned by the user, the name and description are shown in a scrollable list.

When a repository is selected, a card pops up with information about when it was last updated, how many stars it has, and how many times it has been forked.

This call retrieves information about a specific user.

https://api.github.com/users/{userId}

This call retrives a list of public repositories owned by the specified user.

https://api.github.com/users/{userId}/repos

Technology Used :

Android, Java, RxJava, Retrofit, Android Architectural Components like LiveData etc.
