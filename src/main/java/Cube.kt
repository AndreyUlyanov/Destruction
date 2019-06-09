import javafx.scene.paint.Color
import java.util.*

class Cube(val x: Double, val y: Double, val side: Double, var digit: Int) {
    private val random = Random()
    private val i = random.nextInt(4)
    val color = when (i) {
        0 -> Color.HOTPINK
        1 -> Color.DARKVIOLET
        2 -> Color.LIGHTSKYBLUE
        else -> Color.ORANGE
    }
}