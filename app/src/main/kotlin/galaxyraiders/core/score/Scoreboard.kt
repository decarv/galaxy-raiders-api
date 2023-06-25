package galaxyraiders.core.score
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import galaxyraiders.core.game.Asteroid
import java.io.File
import java.io.FileNotFoundException
import java.sql.Timestamp

class Scoreboard {
  private val gameId: Int
  private val timestamp: Timestamp
  private var score: Double = 0.0
  private var explodedAsteroidsNumber: Int = 0
  private val mapper = jacksonObjectMapper()
  private val file = File("Scoreboard.json")

  init {
    if (!file.exists()) {
      file.createNewFile()
      val scoreboards: List<ScoreboardData> = listOf()
      mapper.writeValue(File("Scoreboard.json"), scoreboards)
    }
    val scoreboards: List<ScoreboardData> = if (file.length() > 0) mapper.readValue(file) else listOf()
    val lastScoreboard: ScoreboardData? = scoreboards.lastOrNull()
    gameId = if (lastScoreboard == null) 1 else lastScoreboard.gameId + 1
    timestamp = Timestamp(System.currentTimeMillis())
  }

  fun computePoints(asteroid: Asteroid) {
    score += asteroid.mass * asteroid.radius
    explodedAsteroidsNumber += 1
  }

  fun exportLeaderboardData(): LeaderboardData {
    return LeaderboardData(this.gameId, this.timestamp, this.score)
  }

  fun getScore(): Double {
    return this.score
  }

  fun save(): Boolean {
    return try {
      val scoreboards: MutableList<ScoreboardData> = if (file.exists() && file.length() > 0) {
        mapper.readValue(file)
      } else {
        mutableListOf()
      }
      scoreboards.add(ScoreboardData(gameId, timestamp, explodedAsteroidsNumber, score))
      mapper.writeValue(File("Scoreboard.json"), scoreboards)
      true
    } catch (e: FileNotFoundException) {
      println("Error while saving scoreboard: ${e.message}")
      false
    }
  }

  override fun toString(): String {
    return "Score: $score"
  }
}
