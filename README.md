## Build tools & versions used
- **Android Studio**: Android Studio Giraffe | 2024.2.2 (Ladybug)
- **Kotlin**: 1.9.23
- **Gradle Plugin**: 8.8.0
- **Min SDK**: 26
- **Target SDK**: 34
- **Architecture**: MVVM
- **Libraries**:
    - Koin
    - Kotlin Coroutines + Flow
    - Ktor Client (for requests HTTP)
    - SwipeRefreshLayout
    - RecyclerView + DiffUtil (to efficiently update only the changed items in the list)
    - MockK + Turbine (for unit testing, Turbine was used to test Kotlin Flows in ViewModel)
    - ConstraintLayout

## Steps to run the app
Clone the repository:
git clone https://github.com/seu-usuario/nodes-lightning-challenge.git
Open it in Android Studio
Sync Gradle and run it on an emulator or physical device (Android 8.0+)

## What areas of the app did you focus on?
I focused on building a reactive and testable UI using Kotlin Flow and StateFlow.
I also prioritized clean architecture (MVVM) and a user-friendly list with loading and error states.

## What was the reason for your focus? What problems were you trying to solve?
My goal was to build a solid foundation for a scalable and user-friendly app.
I wanted to ensure that users could clearly understand the app's state (loading, error, success)

## How long did you spend on this project?
I spent approximately 4 to 6 hours on this project.

## Did you make any trade-offs for this project? What would you have done differently with more time?
Rewrite the UI using Jetpack Compose for a more modern declarative approach.
Cache the API response locally using Room, to avoid fetching data from the backend every time — likely with a time-based validation, such as updating every 5 minutes.
Add network availability checks.
Include Espresso UI tests.
Improve the screen design.
Expand test coverage to include more edge cases.

## What do you think is the weakest part of your project?
The current interface, although functional, could be more elegant. 
In addition, there could be a language switch using strings with translations.

## Is there any other information you’d like us to know?
I started implementing a floating action button to switch between the languages provided by the API. 
It successfully updates the node data, but I didn’t have time to apply it to the UI labels.
You can test the functionality by switching to the language-button branch — it’s working as expected.