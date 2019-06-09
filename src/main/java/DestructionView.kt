import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import tornadofx.*
import kotlin.concurrent.timer

object MoveEvent : FXEvent()

class DestructionView : View() {

    override var root = Pane()

    private val canvasWidth = 500.0
    private val canvasHeight = 600.0

    private val deskWidth = 70.0

    private var canvas: Canvas = Canvas(canvasWidth, canvasHeight)

    private var backGround: Canvas = Canvas(canvasWidth, canvasHeight)

    private val desk = Desk((canvasWidth  - deskWidth) / 2, canvasHeight - 10, deskWidth)

    private val radius = 5.0
    private val ball = Ball(canvas.width / 2, desk.y - radius, -1.0, -2.0, radius)

    private var model = Model(ball, desk)

    private var status = Status.START
    private lateinit var paint: Paint

    private var startGameFlag = false
    private var stepDesk = StepDesk.Nothing

    private val buttons = mutableMapOf(
            Pair(KeyCode.LEFT, false),
            Pair(KeyCode.RIGHT, false)
    )

    init {
        title = "Destruction"
        with(root) {
            style = "-fx-background-color: gray"

            canvas = canvas {
                canvas = this
                isFocusTraversable = true

                setOnKeyReleased {
                    buttons[it.code] = false
                }

                setOnKeyPressed {
                    if (it.code == KeyCode.F) startGameFlag = true
                    else buttons[it.code] = true
                }
            }
            add(backGround)
            canvas.widthProperty().bind((canvas.parent as Pane).widthProperty())
            canvas.heightProperty().bind((canvas.parent as Pane).heightProperty())
        }

        timer(daemon = true, period = 10) {
            fire(MoveEvent)
        }

        subscribe<MoveEvent> {
            when (status) {
                Status.START -> {
                    paint = Paint(ball, desk, canvas, backGround)
                    paint.start()
                    if (!startGameFlag) return@subscribe

                    canvas.graphicsContext2D.clearRect(0.0, 0.0, canvasWidth, canvasHeight)
                    paint.paintAll()
                    model.fieldGeneration()
                    model.gameField.forEach { if (it.digit != 0) paint.paintCube(it) }
                    status = Status.PLAY
                }
                Status.WIN -> {
                    paint.playerWin()
                }
                Status.LOSE -> {
                    paint.playerLose()
                }
                else -> {
                    paint.deleteAll()
                    stepDesk = when {
                        buttons[KeyCode.LEFT]!! -> StepDesk.Left
                        buttons[KeyCode.RIGHT]!! -> StepDesk.Right
                        else -> StepDesk.Nothing
                    }
                    model.step(stepDesk, canvasWidth)
                    status = model.gameStatus()
                    model.gameField.forEach { if (it.digit > 0) paint.paintCube(it) else paint.deleteCube(it) }

                    paint.paintAll()
                }
            }
        }
    }
}