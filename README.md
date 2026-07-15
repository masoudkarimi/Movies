# Movie App

This project is an Android application named **Movie**, where users can explore a list of movies and manage them by adding or removing them from a basket. The app also includes a dedicated Android TV experience and a feature flag that controls the availability of the "Add to Basket" button on the movie list screen.

The project is built following **Clean Architecture** and the **MVVM** (Model-View-ViewModel) pattern to ensure a clean separation of concerns and maintainability.

| Movies Screen                        | Basket Count                        | Movie Detail                        | Feature Flag Disabled                        | Debug Drawer                        |
|--------------------------------------|-------------------------------------|-------------------------------------|----------------------------------------------|-------------------------------------|
| ![Movies Screen](/images/shot_1.png) | ![Basket Count](/images/shot_2.png) | ![Movie Detail](/images/shot_3.png) | ![Feature Flag Disabled](/images/shot_4.png) | ![Debug Drawer](/images/shot_5.png) |

### Android TV

The project now includes a separate `tv` module with a Leanback launcher entry, Android TV banner, TV-specific theme, D-pad friendly navigation, wide movie cards, and a landscape detail screen.

| App Icon                             | TV Banner                             |
|--------------------------------------|---------------------------------------|
| ![App Icon](/images/app-icon.png)    | ![TV Banner](/images/tv-banner.png)   |

| TV Movies Screen                               |
|------------------------------------------------|
| ![TV Movies Screen](/images/tv-screenshot.png) |

To build the TV app:

```bash
./gradlew :tv:assembleDebug
```

---

## Project Structure

The project is organized into multiple modules to follow a clean architecture approach, separating concerns between different layers and functionalities.

### Technologies
- **Kotlin**: Modern programming language for Android development.
- **Jetpack Compose**: Declarative UI toolkit for building native Android interfaces.
- **Compose for TV**: TV-optimized Compose components for D-pad navigation and focus states.
- **Android TV / Leanback**: Dedicated TV launcher support through the `tv` module.
- **Coroutines**: Simplifies asynchronous programming in Kotlin.
- **Hilt**: Dependency injection library for Android.
- **Retrofit**: HTTP client for making API requests.
- **Turbine**: Testing library for Kotlin Flows.
- **MockK**: Mocking library for Kotlin unit tests.


### Architecture

1. **app**: The main entry point of the application, connecting feature modules and data layers.
2. **tv**: Dedicated Android TV entry point with TV navigation, theme, and screens.
3. **feature:movies-list**: Handles the UI for displaying a list of movies.
4. **feature:movie-detail**: Manages the UI for showing movie details.
5. **data:network**: Handles network operations.
6. **data:movies**: Manages movie-related data operations.
7. **data:basket**: Handles basket data operations.
8. **data:feature-flag**: Manages feature flag data.
9. **domain:model**: Contains core data models.
10. **domain:movies**: Includes use cases related to movies.
11. **domain:basket**: Contains basket-related use cases.
12. **domain:feature-flag**: Manages feature flag use cases.
13. **core:android**: Contains Android-specific utilities.

---

Here's a visual representation of the dependencies between the modules, following the clean architecture principles:
[![](https://mermaid.ink/img/pako:eNqNVMtugzAQ_BW0Z4jC08SVemkuPfTUnioubnAABWwEJm2a5N_rkAcGQ9IbzM7O7NjW7mHFYwoYkoqUqfGxfIqYYZCyNCzr2VhTIpqK4oJvM1pbeVaLybIVU0GyXK3HRBDMqPjm1UbDz5oa_EXqDRUafLGy1jlJekVekIxJsZjmI3i_7UQQ23vJxqpKsEt5mEuFu1gq2qVS0WGoa22QaQgPDc64bjF1BCPZpxse0rt5HhC7AR8qtsE15uUi_mE-wpxwH9W82St31zIOr0WZ04IyUR90Ye1OJ3uGR6y8nMmeLqr6_KYGV2cbfVQ91XsMLVCfB6ZcHVkMWFQNNaGglazKX9ifNCIQqcwRAZafeZakIoKIHWVTSdgn58W1r-JNkgJek7yWf00pM9JlRuRWKm4oaQR_37HVtUdKAN7DD2AvdGau7yHHdubeArm-CTvAju3PPA8h5ASB79qLowm_reV8FqAAhaHjonkYLtzABBpngldv513YrkQ5E2UxrV54wwRgGznHPyJawhE?type=png)](https://mermaid.live/edit#pako:eNqNVMtugzAQ_BW0Z4gIYAiu1Etz6aGn9lRxcYMDKGAjY9KmSf69DnlgXklvMDs7s2Nbu4cVjylgSAQpU-Nj-RQxwyBlaVjWs7GmRNaC4oJvM1pZeVbJybIVU0myXK_HRBLMqPzmYjPAz5oD-ItUGyoH8MXKWuck6RR5QTKmxGKaj-DdthNBbu8lG6tqwS7lfi4dbmPpaJtKR_uhrrVepj7cNzjjQ4upIxjJPt3wkN7O84DYDvhQsQk-YF4u4h_mI8wJ91HNm712dw3j8FqUOS0ok9VhKDy408me_hFrL2eyp42qP7-pwfXZRh9VR_UeYxCoywNTrY4sBixFTU0oqFBV9Qv7k0YEMlU5IsDqM8-SVEYQsaNqKgn75Ly49gleJyngNckr9VeXKiNdZkRtpeKGklry9x1bXXuUBOA9_AD2nMUsdEIfoWDuBg7yTNgBns-9meM7yHW9wPWRb7tHE34bU3u2sH0UBnNvgYLQt8PABBpnkou38zpstqIai7KYihdeM6n0fHT8A1ixwqs)

### Future Improvements
There are a few features that I didn't implement in the project but could be done later:

1. **Exception handling and retrial process**: Add proper error handling and retry logic when calling repository functions or making network requests.
2. **Design-system module**: Create a design system module to share reusable components across the project.
3. **UI testing for feature flag**: Implement UI testing to ensure the button is not visible when the feature flag is disabled.
4. **Debug drawer**: ✅ Implement a debug drawer that allows for dynamic changes to the feature flag state for debugging purposes.
5. **Using ktlint library**: Integrate the ktlint library to enforce and control the code style across the project.
6. **Gradle convention plugins**: ✅ Create Gradle convention plugins to reuse common build logic across different modules in the project.
