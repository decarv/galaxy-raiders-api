package galaxyraiders.core.score
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema
import java.sql.Timestamp

@JsonSerializableSchema
data class ScoreboardData (
  val gameId: Int,
  val timeStamp: Timestamp,
  val explodedAsteroidsNumber: Int,
  val score: Double
)