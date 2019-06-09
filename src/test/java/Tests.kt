import org.junit.Test

class Tests {

    @Test
    fun step() {
        val desk = Desk(550.0, 400.0, 150.0)
        val ball = Ball(590.0,390.0, 3.0, 4.0, 3.0)

        ball.step(700.0, desk)
        println("x = ${ball.x}; y = ${ball.y};")
        ball.step(700.0, desk)
        println("x = ${ball.x}; y = ${ball.y};")
        ball.step(700.0, desk)
        println("x = ${ball.x}; y = ${ball.y};")
    }
}