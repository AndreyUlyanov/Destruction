class Desk(var x: Double, val y: Double, val length: Double, private val speedDesk: Int = 5) {

    val height = 10.0

    private fun step(limitX: Double, dx: Int) {
        x += dx
        val limitMin = 0.0
        val limitMax = limitX - length
        if (x <= limitMin) x = limitMin
        if (x >= limitMax) x = limitMax
    }

    fun stepDesk(limitX: Double, stepDesk: StepDesk) = when (stepDesk) {
        StepDesk.Right -> step(limitX, speedDesk)
        StepDesk.Left -> step(limitX, -speedDesk)
        else -> {}
    }
}

enum class StepDesk {
    Right,
    Left,
    Nothing
}