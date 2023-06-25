import galaxyraiders.core.score.LeaderboardData
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import kotlin.test.assertEquals

@DisplayName("Given a LeaderboardData instance")
class LeaderboardDataTest {

  private val leaderboardData = LeaderboardData(
    gameId = 1,
    timestamp = Timestamp(1624908813000),
    score = 50.0
  )

  @Test
  fun `it has correct gameId`() {
    assertEquals(1, leaderboardData.gameId)
  }

  @Test
  fun `it has correct timestamp`() {
    assertEquals(Timestamp(1624908813000), leaderboardData.timestamp)
  }

  @Test
  fun `it has correct score`() {
    assertEquals(50.0, leaderboardData.score)
  }
}
