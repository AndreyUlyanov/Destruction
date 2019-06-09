import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*

class Destruction : App(DestructionView::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        with(stage) {
            sizeToScene()
            minWidth = width; maxWidth = width
            minHeight = height; maxHeight = height
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(Destruction::class.java, *args)
}