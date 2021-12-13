package dora.widget.action

import android.view.View
import dora.widget.animator.Animator
import dora.widget.animator.PathAnimator

abstract class PathAction internal constructor(
    val x: Float,
    val y: Float
) : Action<PathAction> {

    private var animator = PathAnimator()

    override fun add(action: PathAction): PathAction {
        animator.add(action)
        return this
    }

    override fun startAnimation(view: View, duration: Long) {
        animator.startAnimation(view, duration)
    }

    override fun getAnimator(): Animator<PathAction> {
        return animator
    }

    operator fun plus(action: PathAction) = add(action)

    init {
        animator.add(this)
    }
}