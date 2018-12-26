# codewars
Application utilises the Codewars API
{ https://dev.codewars.com/#rest­api }.

The application should:
1. Show view to find a specific member and to list found members
2. Show view listing completed and authored challenges
3. Show detail view of a completed or authored challenge

Functional requirements

1. The application should have a screen that allows you to search for a member by
name. The same view should also show a list with the last 5 members that have been
searched for and found. The list with members should show basic information such
as name, rank and best language with points for it. By default, members should be
displayed in order of time of look up, but there should be an option to re­order them
by rank. Here is a link to current leaderboard to get you started with members search
{ https://www.codewars.com/users/leaderboard }
2. On member selection you should display a view with bottom navigation and a list of
challenges. The bottom navigation menu options should be completed challenges
and authored challenges, each of them presenting the user with a corresponding list
of items. The list should be populated with more results when the end of the page is
reached, unless there are no more results to be fetched, in which case the user
should be notified of this.
3. On list item selection a new screen is displayed showing details of the selected
challenge.
