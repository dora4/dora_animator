package dora.widget.animator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.view.View
import dora.widget.action.RotateAction

class RotateAnimator : Animator<RotateAction>() {

    override fun startAnimation(view: View, duration: Long) {
        super.startAnimation(view, duration)
        actionTree.add(0, RotateAction(0.0f))
        val animator = ObjectAnimator.ofObject(
            this, ROTATE, RotateEvaluator(),
            *actionTree.toTypedArray()
        )
        animator.duration = duration
        animator.start()
    }

    fun setRotate(action: RotateAction) {
        val rotate = action.rotate
        targetView.rotation = rotate
    }

    private class RotateEvaluator : TypeEvaluator<RotateAction> {
        override fun evaluate(
            fraction: Float,
            startValue: RotateAction,
            endValue: RotateAction
        ): RotateAction {
            val action: RotateAction
            val startRotate = startValue.rotate
            val endRotate = endValue.rotate
            action = if (endRotate > startRotate) {
                RotateAction(startRotate + fraction * (endRotate - startRotate))
            } else {
                RotateAction(startRotate - fraction * (startRotate - endRotate))
            }
            return action
        }
    }

    companion object {
        private const val ROTATE = "rotate"
    }

    override fun getAnimator(): Animator<RotateAction> {
        return this
    }
}