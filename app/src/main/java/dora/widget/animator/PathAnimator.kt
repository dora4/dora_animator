package dora.widget.animator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.view.View
import dora.widget.action.*

class PathAnimator : Animator<PathAction>() {
    private val PATH = "path"

    override fun startAnimation(view: View, duration: Long) {
        super.startAnimation(view, duration)
        actionTree.add(0, MoveTo(0f, 0f))
        val animator = ObjectAnimator.ofObject(
            this, PATH, PathEvaluator(),
            *actionTree.toTypedArray()
        )
        animator.duration = duration
        animator.start()
    }

    fun setPath(action: MoveTo) {
        val x = action.x
        val y = action.y
        targetView.translationX = x
        targetView.translationY = y
    }

    private inner class PathEvaluator : TypeEvaluator<PathAction> {
        override fun evaluate(fraction: Float, startValue: PathAction, endValue: PathAction): PathAction {
            var x = 0f
            var y = 0f
            if (endValue is MoveTo) {
                x = endValue.x
                y = endValue.y
            }
            if (endValue is LineTo) {
                x = startValue.x + fraction * (endValue.x - startValue.x)
                y = startValue.y + fraction * (endValue.y - startValue.y)
            }
            val ratio = 1 - fraction
            if (endValue is QuadTo) {
                x = Math.pow(ratio.toDouble(), 2.0)
                    .toFloat() * startValue.x + (2 * fraction * ratio
                        * (endValue).inflectionX) + (Math.pow(
                    endValue.x.toDouble(),
                    2.0
                )
                    .toFloat()
                        * Math.pow(fraction.toDouble(), 2.0).toFloat())
                y = Math.pow(ratio.toDouble(), 2.0)
                    .toFloat() * startValue.y + (2 * fraction * ratio
                        * (endValue).inflectionY) + (Math.pow(
                    endValue.y.toDouble(),
                    2.0
                )
                    .toFloat()
                        * Math.pow(fraction.toDouble(), 2.0).toFloat())
            }
            if (endValue is CubicTo) {
                x = Math.pow(ratio.toDouble(), 3.0).toFloat() * startValue.x + (3 * Math.pow(
                    ratio.toDouble(),
                    2.0
                ).toFloat() * fraction
                        * (endValue).inflectionX1) + (3 * ratio *
                        Math.pow(fraction.toDouble(), 2.0).toFloat()
                        * (endValue).inflectionX2) + Math.pow(fraction.toDouble(), 3.0)
                    .toFloat() * endValue.x
                y = Math.pow(ratio.toDouble(), 3.0).toFloat() * startValue.y + (3 * Math.pow(
                    ratio.toDouble(),
                    2.0
                ).toFloat() * fraction
                        * (endValue).inflectionY1) + (3 * ratio *
                        Math.pow(fraction.toDouble(), 2.0).toFloat()
                        * (endValue).inflectionY2) + Math.pow(fraction.toDouble(), 3.0)
                    .toFloat() * endValue.y
            }
            return MoveTo(x, y)
        }
    }

    override fun getAnimator(): Animator<PathAction> {
        return this
    }
}