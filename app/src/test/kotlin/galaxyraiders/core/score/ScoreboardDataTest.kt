import galaxyraiders.core.score.ScoreboardData
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import kotlin.test.assertEquals

@DisplayName("Given a ScoreboardData instance")
class ScoreboardDataTest {

  private val scoreboardData = ScoreboardData(
    gameId = 1,
    timestamp = Timestamp(1624908813000),
    explodedAsteroidsNumber = 5,
    score = 50.0
  )

  @Test
  fun `it has correct gameId`() {
    assertEquals(1, scoreboardData.gameId)
  }

  @Test
  fun `it has correct timestamp`() {
    assertEquals(Timestamp(1624908813000), scoreboardData.timestamp)
  }

  @Test
  fun `it has correct explodedAsteroidsNumber`() {
    assertEquals(5, scoreboardData.explodedAsteroidsNumber)
  }

  @Test
  fun `it has correct score`() {
    assertEquals(50.0, scoreboardData.score)
  }
}
