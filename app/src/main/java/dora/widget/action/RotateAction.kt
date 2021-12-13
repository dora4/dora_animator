package dora.widget.action

import android.view.View
import dora.widget.animator.Animator
import dora.widget.animator.RotateAnimator

class RotateAction(val rotate: Float) : Action<RotateAction> {

    private var animator = RotateAnimator()

    override fun add(action: RotateAction): RotateAction {
        animator.add(action)
        return this
    }

    override fun startAnimation(view: View, duration: Long) {
        animator.startAnimation(view, duration)
    }

    override fun getAnimator(): Animator<RotateAction> {
        return animator
    }

    operator fun plus(action: RotateAction) = add(action)

    init {
        animator.add(this)
    }
}