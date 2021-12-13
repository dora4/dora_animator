package dora.widget.action

import android.view.View
import dora.widget.animator.Animator
import dora.widget.animator.ScaleAnimator

class ScaleAction(val scaleX: Float, val scaleY: Float) : Action<ScaleAction> {

    private var animator = ScaleAnimator()

    override fun add(action: ScaleAction): ScaleAction {
        animator.add(action)
        return this
    }

    override fun startAnimation(view: View, duration: Long) {
        animator.startAnimation(view, duration)
    }

    override fun getAnimator(): Animator<ScaleAction> {
        return animator
    }

    operator fun plus(action: ScaleAction) = add(action)

    init {
        animator.add(this)
    }
}