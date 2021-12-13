package dora.widget.animator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.view.View
import dora.widget.action.ScaleAction

class ScaleAnimator : Animator<ScaleAction>() {
    private val SCALE = "scale"

    override fun startAnimation(view: View, duration: Long) {
        super.startAnimation(view, duration)
        actionTree.add(0, ScaleAction(1.0f, 1.0f))
        val animator = ObjectAnimator.ofObject(
            this, SCALE, ScaleEvaluator(),
            *actionTree.toTypedArray()
        )
        animator.duration = duration
        animator.start()
    }

    fun setScale(action: ScaleAction) {
        val scaleX = action.scaleX
        val scaleY = action.scaleY
        targetView.scaleX = scaleX
        targetView.scaleY = scaleY
    }

    private inner class ScaleEvaluator : TypeEvaluator<ScaleAction> {
        override fun evaluate(
            fraction: Float,
            startValue: ScaleAction,
            endValue: ScaleAction
        ): ScaleAction {
            val startScaleX = startValue.scaleX
            val startScaleY = startValue.scaleY
            val endScaleX = endValue.scaleX
            val endScaleY = endValue.scaleY
            val scaleX: Float = if (endScaleX > startScaleX) {
                startScaleX + fraction * (endScaleX - startScaleX)
            } else {
                startScaleX - fraction * (startScaleX - endScaleX)
            }
            val scaleY: Float = if (endScaleY > startScaleY) {
                startScaleY + fraction * (endScaleY - startScaleY)
            } else {
                startScaleY - fraction * (startScaleY - endScaleY)
            }
            return ScaleAction(scaleX, scaleY)
        }
    }

    override fun getAnimator(): Animator<ScaleAction> {
        return this
    }
}