# Movie App

This project is an Android application named **Movie**, where users can explore a list of movies and manage them by adding or removing them from a basket. The app also features a feature flag that controls the availability of the "Add to Basket" button on the movie list screen.

The project is built following **Clean Architecture** and the **MVVM** (Model-View-ViewModel) pattern to ensure a clean separation of concerns and maintainability.

| Movies Screen                        | Basket Count                        | Movie Detail                        | Feature Flag Disabled                        | Debug Drawer                        |
|--------------------------------------|-------------------------------------|-------------------------------------|----------------------------------------------|-------------------------------------|
| ![Movies Screen](/images/shot_1.png) | ![Basket Count](/images/shot_2.png) | ![Movie Detail](/images/shot_3.png) | ![Feature Flag Disabled](/images/shot_4.png) | ![Debug Drawer](/images/shot_5.png) |


---

## Project Structure

The project is organized into multiple modules to follow a clean architecture approach, separating concerns between different layers and functionalities.

### Technologies
- **Kotlin**: Modern programming language for Android development.
- **Jetpack Compose**: Declarative UI toolkit for building native Android interfaces.
- **Coroutines**: Simplifies asynchronous programming in Kotlin.
- **Hilt**: Dependency injection library for Android.
- **Retrofit**: HTTP client for making API requests.
- **Turbine**: Testing library for Kotlin Flows.
- **MockK**: Mocking library for Kotlin unit tests.


### Architecture

1. **app**: The main entry point of the application, connecting feature modules and data layers.
2. **feature:movies-list**: Handles the UI for displaying a list of movies.
3. **feature:movie-detail**: Manages the UI for showing movie details.
4. **data:network**: Handles network operations.
5. **data:movies**: Manages movie-related data operations.
6. **data:basket**: Handles basket data operations.
7. **data:feature-flag**: Manages feature flag data.
8. **domain:model**: Contains core data models.
9. **domain:movies**: Includes use cases related to movies.
10. **domain:basket**: Contains basket-related use cases.
11. **domain:feature-flag**: Manages feature flag use cases.
12. **core:android**: Contains Android-specific utilities.

---

Here's a visual representation of the dependencies between the modules, following the clean architecture principles:
[![](https://mermaid.ink/img/pako:eNqdU7FugzAQ_RV0MyCgYBNX6pSlQ6d2qljccAEUsBGYtinh32tIJEggQspmvXvv3buz3cJOxggMkoqXqfGxfY6EYfCyNCzrxdgjV02FrJDfGdZWntVqWo654kyg-pHVYYafNTP4i9cHnLtcOln7nCdXRVnwTGizGPMeX0g05d3arNDHjCvEMfWq4yXolXCZMa5jqJ9eizLHAoWqT_O-sy3d1dzu4GrW-0km9SXXcVXTW3909PVI81kWeGBCgZVGYv2C214XgUp18giYPuZZkqoIItFpIm-UfD-KHTBVNWhCU-pBcJtx_fILYHue1xotuQDWwi8w37EdQgOX0sAJqUc9E47APH9je08bsvHc0A1Dh5DOhD8ptYNjU0pJENDA99wgdAkxAeNMyert_MWGnza0-BwEfY7uH1HyL24?type=png)](https://mermaid.live/edit#pako:eNqdU7FugzAQ_RV0MyCgYBNX6pSlQ6d2qljccAEUsBGYtinh32tIJEggQspmvXvv3buz3cJOxggMkoqXqfGxfY6EYfCyNCzrxdgjV02FrJDfGdZWntVqWo654kyg-pHVYYafNTP4i9cHnLtcOln7nCdXRVnwTGizGPMeX0g05d3arNDHjCvEMfWq4yXolXCZMa5jqJ9eizLHAoWqT_O-sy3d1dzu4GrW-0km9SXXcVXTW3909PVI81kWeGBCgZVGYv2C214XgUp18giYPuZZkqoIItFpIm-UfD-KHTBVNWhCU-pBcJtx_fILYHue1xotuQDWwi8w37EdQgOX0sAJqUc9E47APH9je08bsvHc0A1Dh5DOhD8ptYNjU0pJENDA99wgdAkxAeNMyert_MWGnza0-BwEfY7uH1HyL24)

### Future Improvements
There are a few features that I didn't implement in the project but could be done later:

1. **Exception handling and retrial process**: Add proper error handling and retry logic when calling repository functions or making network requests.
2. **Design-system module**: Create a design system module to share reusable components across the project.
3. **UI testing for feature flag**: Implement UI testing to ensure the button is not visible when the feature flag is disabled.
4. **Debug drawer**: ✅ Implement a debug drawer that allows for dynamic changes to the feature flag state for debugging purposes.
5. **Using ktlint library**: Integrate the ktlint library to enforce and control the code style across the project.
6. **Gradle convention plugins**: Create Gradle convention plugins to reuse common build logic across different modules in the project.
