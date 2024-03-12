# Fetch Rewards Coding Exercise

## Overview
It is a native Android app in Kotlin that retrieves the data from https://fetch-hiring.s3.amazonaws.com/hiring.json.  
This list is generated based on the following requirements:
1. Display all the items grouped by "listId"
2. Sort the results first by "listId" then by "name" when displaying.
3. Filter out any items where "name" is blank or null.

## Features

- I utilize the sticky header and LazyColumn to present the final results to the user in a clear and easy-to-read list format.  
- Users can either click on the sticky header of the List ID or click on the filter icon located in the top bar to see only a certain group of items.

## Demo


https://github.com/TYJ99/fetch-rewards-coding-exercise/assets/97372852/c8d2eb2a-b347-4dd5-91ed-58b862ac8a90


  
## Tech Stack

- Kotlin
- Android
- Jetpack Compose
- ViewModel
- Hilt
- Retrofit
- Coroutine
- Unit test
- MVVM
- Clean Architecture


