# codewars
Application utilises the Codewars API
{ https://dev.codewars.com/#rest­api }.

1. The application has a screen that allows you to search for a member by
name. The same view also shows a list with the last 5 members that have been
searched for and found. The list with members shows basic information such
as name, rank and best language. By default, members are
displayed in order of time of look up, but there is an option to re-order them
by rank. Here is a link to current leaderboard to get you started with members search
{ https://www.codewars.com/users/leaderboard }
2. On member selection the app displays a view with bottom navigation and a list of
challenges. The bottom navigation menu options are completed challenges
and authored challenges, each of them presenting the user with a corresponding list
of items. The list should be populated with more results when the end of the page is
reached, unless there are no more results to be fetched.
3. On list item selection a new screen is displayed showing details of the selected
challenge.


Technologies used:

1. MVVM design pattern
2. Android architecture components
3. Reactive approach
4. Repository pattern with cache policy implemented
5. Dagger 2
