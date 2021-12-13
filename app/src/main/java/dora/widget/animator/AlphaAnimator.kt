package dora.widget.animator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.view.View
import dora.widget.action.AlphaAction

class AlphaAnimator : Animator<AlphaAction>() {

    override fun startAnimation(view: View, duration: Long) {
        super.startAnimation(view, duration)
        actionTree.add(0, AlphaAction(1.0f))
        val animator = ObjectAnimator.ofObject(
            this, ALPHA, AlphaEvaluator(),
            *actionTree.toTypedArray()
        )
        animator.duration = duration
        animator.start()
    }

    fun setAlpha(action: AlphaAction) {
        val alpha = action.alpha
        targetView.alpha = alpha
    }

    private class AlphaEvaluator : TypeEvaluator<AlphaAction> {
        override fun evaluate(
            fraction: Float,
            startValue: AlphaAction,
            endValue: AlphaAction
        ): AlphaAction {
            val action: AlphaAction
            val startAlpha = startValue.alpha
            val endAlpha = endValue.alpha
            action = if (endAlpha > startAlpha) {
                AlphaAction(startAlpha + fraction * (endAlpha - startAlpha))
            } else {
                AlphaAction(startAlpha - fraction * (startAlpha - endAlpha))
            }
            return action
        }
    }

    companion object {
        private const val ALPHA = "alpha"
    }

    override fun getAnimator(): Animator<AlphaAction> {
        return this
    }
}