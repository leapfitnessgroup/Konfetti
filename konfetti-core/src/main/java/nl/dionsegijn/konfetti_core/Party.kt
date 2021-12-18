package nl.dionsegijn.konfetti_core

import android.graphics.Color
import nl.dionsegijn.konfetti_core.NewEmitter.EmitterConfig
import nl.dionsegijn.konfetti_core.models.Shape
import nl.dionsegijn.konfetti_core.models.Size

/**
 * Configuration how to confetti should be rendered
 * @property angle the direction the confetti will shoot
 * @property spread how wide the confetti will shoot in degrees. Use 1 to shoot in a straight line
 * and 360 to form a circle
 * @property speed The start speed of the confetti at the time of creation. Also set [maxSpeed] to
 * apply a random speed between speed and maxSpeed.
 * @property maxSpeed when [maxSpeed] is set a random speed between [speed] and [maxSpeed] will be
 * chosen. Using randomness creates a more natural and realistic look to the confetti when animating.
 * @property damping The rate at which the speed will decrease right after shooting the confetti
 * @property size The size of the confetti. Use: Size.SMALL, MEDIUM or LARGE for default sizes or
 * create your custom size using a new instance of [Size].
 * @property shapes Set the shape of the confetti. Set multiple shapes and it will be randomly
 * assigned upon creation of the confetti. See [Shape] for possible shapes and custom drawables.
 * @property timeToLive the amount of time in milliseconds before a particle will stop rendering
 * or fading out if [fadeOutEnabled] is set to true.
 * @property position the point where the confetti will spawn relative to the canvas. Use absolute
 * coordinates with [Position.xy] or relative coordinates between 0.0 and 1.0 using [Position.relative].
 * Spawn confetti on random positions using [Position.between].
 * @property delay the amount of milliseconds to wait before the rendering of the confetti starts
 * @property accelerationEnabled Enable or disable the acceleration of the particle
 * @property rotation enable the 3D rotation of a Confetti. See [Rotation] class for the configuration
 * options. Easily enable or disable it using [Rotation].enabled() or [Rotation].disabled()
 * @property emitter instructions how many and often a confetti particle should spawn per tick (frame)
 * Use Emitter(duration, timeUnit).max(amount) or Emitter(duration, timeUnit).perSecond(amount) to
 * configure the Emitter.
 */
data class Party(
    val angle: Int = 0,
    val spread: Int = 20,
    val speed: Float = 45f,
    val maxSpeed: Float = -1f,
    val damping: Float = 0.9f,
    val size: List<Size> = listOf(Size.SMALL, Size.MEDIUM, Size(10)),
    val colors: List<Int> = listOf(Color.RED),
    val shapes: List<Shape> = listOf(Shape.Square, Shape.Circle),
    val timeToLive: Long = 2000, // milliseconds
    val fadeOutEnabled: Boolean = true,
    val position: Position = Position.xy(100f, 100f),
    val delay: Int = 0,
    val accelerationEnabled: Boolean = true,
    val maxAcceleration: Float = -1f, // TODO divide maxAcceleration by 10 in Confetti
    val rotation: Rotation = Rotation(),
    val emitter: EmitterConfig
)

class Angle {
    companion object {
        const val TOP: Int = 270
        const val RIGHT: Int = 0
        const val BOTTOM: Int = 90
        const val LEFT: Int = 180
    }
}

class Spread {
    companion object {
        const val SMALL: Int = 30
        const val WIDE: Int = 100
        const val ROUND: Int = 360
    }
}

sealed class Position {
    data class xy(val x: Float, val y: Float) : Position() {
        fun between(value: xy): Position = between(this, value)
    }

    data class relative(val x: Double, val y: Double) : Position() {
        fun between(value: relative): Position = between(this, value)
    }

    internal data class between(val min: Position, val max: Position) : Position()
}

/**
 * @property enabled by default true. Set to false to prevent the confetti from rotating
 * @property rotationSpeed the rate at which the confetti will rotate per frame
 * @property rotationVariance the margin in which the rotationSpeed can differ to add randomness
 * to the rotation speed of each confetti.
 */
data class Rotation(
    val enabled: Boolean = true,
    val rotationSpeed: Float = 1f,
    val rotationVariance: Float = 0.8f
) {
    companion object {
        fun enabled() = Rotation(enabled = true)
        fun disabled() = Rotation(enabled = false)
    }
}

