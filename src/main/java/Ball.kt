class Ball(var x: Double, var y: Double, var dx: Double, var dy: Double, val radius: Double) {

    fun step(xLimit: Double, desk: Desk) {
        x += dx
        y += dy

        if (x >= xLimit - radius) {
            x = xLimit - radius - 1
            dx = -dx
        }
        if (x < radius) {
            x = radius
            dx = -dx
        }
        if (y < radius) {
            y = radius
            dy = -dy
        }
        if (y + radius - 1 >= desk.y && x in desk.x..(desk.x + desk.length)) {
            y = desk.y - radius
            dy = -(dy + 0.05)
            dx += 0.03
        }
    }
}