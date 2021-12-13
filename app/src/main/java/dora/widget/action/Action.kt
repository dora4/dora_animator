package dora.widget.action

import android.view.View
import dora.widget.animator.Animator

/**
 * 组合的action可以直接交给view执行。
 */
interface Action<A : Action<A>> {
    fun add(action: A): A
    fun getAnimator(): Animator<A>
    fun startAnimation(view: View, duration: Long)
}