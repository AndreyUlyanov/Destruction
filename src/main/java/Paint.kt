import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.scene.text.Font

class Paint(private val ball: Ball, private val desk: Desk, private val canvas: Canvas, private val backGround: Canvas) {

    private fun paintBall() {
        canvas.graphicsContext2D.fill = Color.WHITE
        with(ball){
            canvas.graphicsContext2D.fillOval(
                    x - radius,
                    y - radius,
                    2 * radius,
                    2 * radius
            )
        }
    }

    private fun deleteBall() {
        with(ball) {
            canvas.graphicsContext2D.clearRect(
                    x - radius,
                    y - radius,
                    2 * radius,
                    2 * radius
            )
        }
    }

    fun paintCube(cube: Cube) {

        canvas.graphicsContext2D.fill = cube.color
        with(cube) {
            canvas.graphicsContext2D.fillRect(
                    x, y, side, side
            )
        }
        with(canvas) {
            graphicsContext2D.fill = Color.WHITE
            graphicsContext2D.font = Font("Arial", 20.0)
            graphicsContext2D.fillText("${cube.digit}", cube.x + 20, cube.y + cube.side / 2)
        }
    }

    fun deleteCube(cube: Cube) {
        with(cube) {
            canvas.graphicsContext2D.clearRect(
                    x, y, side, side
            )
        }
    }

    private fun paintDesk() {
        canvas.graphicsContext2D.fill = Color.BLACK
        with(desk) {
            canvas.graphicsContext2D.fillRect(
                    x, y, length, height
            )
        }
    }

    private fun deleteDesk() {
        with(desk) {
            canvas.graphicsContext2D.clearRect(
                    x, y, length, height
            )
        }
    }

    fun paintAll() {
        paintBall()
        paintDesk()
    }

    fun deleteAll() {
        deleteBall()
        deleteDesk()
    }

    fun start() {
        with(canvas) {
            graphicsContext2D.clearRect(0.0, 0.0, width, height)
            graphicsContext2D.fill = Color.BLACK
            graphicsContext2D.font = Font("Brush Script MT Italic", 40.0)
            graphicsContext2D.fillText("Press F, to start", 150.0, 200.0)
        }
    }

    fun playerWin() {
        with(canvas) {
            graphicsContext2D.clearRect(0.0, 0.0, width, height)
            graphicsContext2D.fill = Color.BLACK
            graphicsContext2D.font = Font("Brush Script MT Italic", 40.0)
            graphicsContext2D.fillText("Congratulation! You win!!!", 100.0, 200.0)
        }
    }

    fun playerLose() {
        with(canvas) {
            graphicsContext2D.clearRect(0.0, 0.0, width, height)
            graphicsContext2D.fill = Color.BLACK
            graphicsContext2D.font = Font("Brush Script MT Italic", 40.0)
            graphicsContext2D.fillText("Unfortunately you lost.", 100.0, 200.0)
        }
    }

}