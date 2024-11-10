package ir.masoudkarimi.movies_list

import android.util.Log
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import ir.masoudkarimi.basket.AddItemToBasketUseCase
import ir.masoudkarimi.basket.ObserveBasketContentUseCase
import ir.masoudkarimi.basket.RemoveFromBasketUseCase
import ir.masoudkarimi.feature_flag.FeatureFlagRepository
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.GetMoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MoviesListViewModel
    private val getMoviesListUseCase = mockk<GetMoviesListUseCase>()

    private val basketRepository = FakeBasketRepository()
    @SpyK
    var addItemToBasketUseCase = AddItemToBasketUseCase(basketRepository)
    @SpyK
    var removeFromBasketUseCase = mockk<RemoveFromBasketUseCase>()
    @SpyK
    var observeBasketContentUseCase = ObserveBasketContentUseCase(basketRepository)
    private val featureFlagRepository = mockk<FeatureFlagRepository>()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 1
        coEvery { featureFlagRepository.isEnabled(any()) } returns true
    }

    private fun createMoviesListViewModel() = MoviesListViewModel(
        moviesList = getMoviesListUseCase,
        addToBasket = addItemToBasketUseCase,
        removeFromBasket = removeFromBasketUseCase,
        observeBasketContent = observeBasketContentUseCase,
        featureFlagRepository = featureFlagRepository
    )

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkStatic(Log::class)
    }

    @Test
    fun `loadMovies should update uiState with movies`() = runTest {
        // Mock the use case to return a successful result
        val mockMovies = listOf(Movie(1, "Movie 1", ""), Movie(2, "Movie 2", ""))

        coEvery { getMoviesListUseCase() } returns Result.success(mockMovies)
        viewModel = createMoviesListViewModel()
        viewModel.uiState.test {
            // Skip initial state
            awaitItem()

            // Check final state with loaded movies
            val state = awaitItem()
            println(state)
            assertThat(state.isLoading).isFalse()
            assertThat(state.movies).hasSize(2)
            assertThat(state.movies[0].movie.title).isEqualTo("Movie 1")
            assertThat(state.movies[1].movie.title).isEqualTo("Movie 2")

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadMovies should emit error on failure`() = runTest {
        // Arrange
        coEvery { getMoviesListUseCase() } returns Result.failure(Exception("Error"))
        viewModel = createMoviesListViewModel()
        // Act
        viewModel.uiState.test {
            // Skip initial state
            awaitItem()

            val errorState = awaitItem()
            assertThat(errorState.isLoading).isFalse()
            assertThat(errorState.error).isNotNull()
            assertThat(errorState.movies).isEmpty()

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `addMovieToBasket should update basket size correctly`() = runTest {
        basketRepository.clear()
        val mockMovies = listOf(Movie(1, "Movie 1", ""), Movie(2, "Movie 2", ""))
        coEvery { getMoviesListUseCase() } returns Result.success(mockMovies)

        // Mock add movie to basket
        val mockMovie = Movie(1, "Movie 1", "")
        viewModel = createMoviesListViewModel()

        // Start observing the UI state
        viewModel.uiState.test {
            // Check initial basket size (should be 0)
            assertThat(awaitItem().basketSize).isEqualTo(0)

            viewModel.addMovieToBasket(mockMovie)

            val updatedState = awaitItem()
            assertThat(updatedState.basketSize).isEqualTo(1)

            cancelAndIgnoreRemainingEvents()
        }
    }
}