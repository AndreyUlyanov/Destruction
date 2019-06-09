import java.util.*

class Model(private val ball: Ball, private val desk: Desk) {

    private var digits = 0

    val gameField = mutableListOf<Cube>()

    fun fieldGeneration() {
        val random = Random()
        for (j in 0..5) {
            var x = 0
            var g = random.nextInt(1023)
            while (g > 0) {
                if (g % 2 == 1) {
                    val digit = random.nextInt(15)
                    gameField.add(Cube(x * 50.0, j * 50.0, 50.0, digit))
                    digits += digit
                }
                g /= 2; x++
            }
        }
    }

    fun step(stepDesk: StepDesk, limitX: Double) {
        desk.stepDesk(limitX, stepDesk)


        for (i in 0 until gameField.size) {
            val cube = gameField[i]
            if (cube.digit <= 0) continue
            with(ball) {
                when {
                    y - radius <= cube.y + cube.side && y > cube.y && x in cube.x..cube.x + cube.side -> {
                        y = cube.y + cube.side + radius + 1
                        gameField[i].digit--; digits--
                        dy = -dy
                    }
                    y + radius >= cube.y && y < cube.y + cube.side && x in cube.x..cube.x + cube.side -> {
                        y = cube.y - radius - 1
                        gameField[i].digit--; digits--
                        dy = -dy
                    }
                    x + radius >= cube.x && x < cube.x + cube.side && y in cube.y..cube.y + cube.side -> {
                        x = cube.x - radius - 1
                        gameField[i].digit--; digits--
                        dx = -dx
                    }
                    x - radius <= cube.x + cube.side && x > cube.x && y in cube.y..cube.y + cube.side -> {
                        x = cube.x + cube.side + radius + 1
                        gameField[i].digit--; digits--
                        dx = -dx
                    }
                }
            }
        }
        ball.step(limitX, desk)
    }

    fun gameStatus(): Status {
        if (ball.y >= desk.y + desk.length / 2) return Status.LOSE
        if (digits <= 0) return Status.WIN
        return Status.PLAY
    }
}

enum class Status {
    START,
    WIN,
    LOSE,
    PLAY;
}