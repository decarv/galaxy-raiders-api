package galaxyraiders.core.score
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema
import java.sql.Timestamp

@JsonSerializableSchema
data class LeaderboardData(
  val gameId: Int,
  val timestamp: Timestamp,
  val score: Double
)
