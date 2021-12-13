package dora.widget.action

class CubicTo(
    val inflectionX1: Float,
    val inflectionX2: Float,
    val inflectionY1: Float,
    val inflectionY2: Float,
    x: Float,
    y: Float
) : PathAction(x, y)