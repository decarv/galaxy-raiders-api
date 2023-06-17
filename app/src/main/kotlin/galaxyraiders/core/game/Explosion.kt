package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double,
  var isTriggered: Boolean = false
) : SpaceObject("Explosion", '#', initialPosition, initialVelocity, radius, mass) {

  fun triggerExplosion() {
    if (!this.isTriggered) {
      this.isTriggered = true
    }
  }
}