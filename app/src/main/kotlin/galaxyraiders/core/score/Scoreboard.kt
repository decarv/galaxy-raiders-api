package galaxyraiders.core.score
import galaxyraiders.core.game.Asteroid
import java.io.File
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.sql.Timestamp

class Scoreboard {
  private val gameId : Int
  private val timestamp: Timestamp
  private var score: Double = 0.0
  private var explodedAsteroidsNumber: Int = 0

  init {
    val mapper = jacksonObjectMapper()
    val file = File("Scoreboard.json")
    val scoreboards: List<ScoreboardData> = if(file.length() > 0) mapper.readValue(file) else listOf()
    val lastScoreboard: ScoreboardData? = scoreboards.lastOrNull()
    gameId = if (lastScoreboard == null) 1 else lastScoreboard.gameId + 1
    timestamp = Timestamp(System.currentTimeMillis())
  }

  fun computePoints(asteroid : Asteroid) {
    score += asteroid.mass * asteroid.radius
    explodedAsteroidsNumber += 1
  }

  fun getLeaderboardData() : LeaderboardData {
    return LeaderboardData(this.gameId, this.timestamp, this.score)
  }

  fun save() {
    val mapper = jacksonObjectMapper()
    val scoreboards: MutableList<ScoreboardData> = mapper.readValue(File("Scoreboard.json"))
    scoreboards.add(ScoreboardData(gameId, timestamp, explodedAsteroidsNumber, score))
    mapper.writeValue(File("Scoreboard.json"), scoreboards)
  }

  override fun toString() : String {
    return "Score: $score"
  }

}