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
    - Jetpack Compose (used for modern UI development)

## Steps to run the app
Clone the repository:
git clone https://github.com/seu-usuario/nodes-lightning-challenge.git
Open it in Android Studio
Sync Gradle and run it on an emulator or physical device (Android 8.0+)

## What areas of the app did you focus on?
I focused on building a reactive and testable UI using Kotlin Flow and StateFlow.
I also prioritized clean architecture (MVVM) and a user-friendly interface with loading and error states.
The UI was implemented in both XML and Jetpack Compose, but currently, the app uses only one screen, and it is fully built with Jetpack Compose.
There is also an XML version of the screen available in a separate Activity, and you can easily switch between them by editing the AndroidManifest.xml.

## What was the reason for your focus? What problems were you trying to solve?
My goal was to build a solid foundation for a scalable and modern Android app.
I wanted to ensure the UI clearly communicates its state (loading, error, success) while exploring Compose for a cleaner and more flexible development experience.

## How long did you spend on this project?
I spent approximately 6 to 8 hours on this project.

## Did you make any trade-offs for this project? What would you have done differently with more time?
Add local caching using Room (e.g., refresh every 5 minutes)
Add network availability checks
Improve the visual design
Implement full language switching using string translations

## What do you think is the weakest part of your project?
The UI works well, but it could be more polished visually.
Also, language switching is only partially implemented and not reflected in UI labels yet.

## Is there any other information you’d like us to know?
I started implementing a floating action button (in XML project) to switch between the languages provided by the API.
It correctly updates the displayed node data, but I didn’t have enough time to apply it to UI labels.
You can test the feature in the language-button branch, it’s working as expected.