package dora.widget.action

import android.view.View
import dora.widget.animator.AlphaAnimator
import dora.widget.animator.Animator

class AlphaAction(val alpha: Float) : Action<AlphaAction> {

    private var animator = AlphaAnimator()

    override fun add(action: AlphaAction): AlphaAction {
        animator.add(action)
        return this
    }

    override fun startAnimation(view: View, duration: Long) {
        animator.startAnimation(view, duration)
    }

    override fun getAnimator(): Animator<AlphaAction> {
        return animator
    }

    operator fun plus(action: AlphaAction) = add(action)

    init {
        animator.add(this)
    }
}