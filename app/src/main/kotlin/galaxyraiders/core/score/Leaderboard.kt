package galaxyraiders.core.score

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.io.FileNotFoundException

class Leaderboard {
  private var board: MutableList<LeaderboardData>
  private val mapper = jacksonObjectMapper()
  private val file: File = File("Leaderboard.json")

  init {
    if (!file.exists()) {
      file.createNewFile()
    }
    board = if (file.length() > 0) {
      mapper.readValue(file)
    } else {
      mutableListOf()
    }
  }

  fun save(leaderboardData: LeaderboardData) {
    var tmp: LeaderboardData
    var next: LeaderboardData = leaderboardData
    for (i in board.indices) {
      if (board[i].score < next.score) {
        tmp = board[i]
        board[i] = next
        next = tmp
      }
    }
    if (board.size < Companion.BOARD_MAX_SIZE) {
      board.add(next)
    }
    try {
      mapper.writeValue(file, board)
    } catch (e: FileNotFoundException) {
      println("Error writing leaderboard file: ${e.message}")
    }
  }

  fun getFileLength(): Long {
    return this.file.length()
  }

  companion object {
    private const val BOARD_MAX_SIZE: Int = 3
  }
}
