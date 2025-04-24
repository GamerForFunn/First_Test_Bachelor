import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.MockitoAnnotations
import com.example.cognitiveexercisesapp.ui.games.game2.Game2ViewModel
import com.example.cognitiveexercisesapp.ui.games.game2.GameState
import com.example.testforbachelor.ui.game_2.Game2ViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class Game2ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: Game2ViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = Game2ViewModel() // Initialize your ViewModel
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when initialised then game state is correct`() = runTest {
        // Arrange

        // Act
        val gameState = viewModel.gameState.first() // Collect the state

        // Assert
        assertNotNull(gameState)
        assertEquals(0, gameState.score)
        assertEquals(1, gameState.currentLevel)
        assertFalse(gameState.isGameOver)
    }

    @Test
    fun `when correctAnswer then score increases and level increases`() = runTest {
        // Arrange

        // Act
        viewModel.correctAnswer()
        val gameState = viewModel.gameState.first() // Collect the new state

        // Assert
        assertEquals(1, gameState.score)
        assertEquals(2, gameState.currentLevel)
        assertFalse(gameState.isGameOver)
    }
    @Test
    fun `when level is 10 then the game is over`()= runTest{
        //Arrange
        //set the current level to 10, the max level
        viewModel.setState(GameState(score = 0, currentLevel = 10, isGameOver = false))
        // Act
        viewModel.correctAnswer()
        val gameState = viewModel.gameState.first()
        //Assert
        assertTrue(gameState.isGameOver)
    }
    @Test
    fun `when wrong answer then game over`()= runTest{
        //Arrange
        // Act
        viewModel.wrongAnswer()
        val gameState = viewModel.gameState.first()
        //Assert
        assertTrue(gameState.isGameOver)
    }
    @Test
    fun `when game over restart then the game state is correct`()= runTest{
        //Arrange
        viewModel.correctAnswer()
        viewModel.correctAnswer()
        viewModel.wrongAnswer()
        // Act
        viewModel.restartGame()
        val gameState = viewModel.gameState.first()
        //Assert
        assertFalse(gameState.isGameOver)
        assertEquals(1,gameState.currentLevel)
        assertEquals(0,gameState.score)
    }
}