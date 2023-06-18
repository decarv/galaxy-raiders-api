package galaxyraiders.core.score

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class StoreLeaderboard (private var scoreboard: Scoreboard)  {
  private val gameScore: LeaderboardData = scoreboard.getLeaderboardData()
  private val leaderboard: MutableList<LeaderboardData>

  init {
    val mapper = jacksonObjectMapper()
    val file = File("Leaderboard.json")
    leaderboard = if(file.length() > 0) mapper.readValue(file) else mutableListOf()

    var tmp: LeaderboardData
    var next: LeaderboardData = this.gameScore
    for (i in leaderboard.indices) {
      if (leaderboard[i].score < next.score) {
        tmp = leaderboard[i]
        leaderboard[i] = next
        next = tmp
      }
    }
    if (leaderboard.size < 3) {
      leaderboard.add(next)
    }

    mapper.writeValue(file, leaderboard)
  }
}