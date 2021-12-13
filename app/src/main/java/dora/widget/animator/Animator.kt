package dora.widget.animator

import android.view.View
import dora.widget.action.Action
import java.util.*

abstract class Animator<A : Action<A>>: Action<A> {

    protected lateinit var targetView: View
    protected var actionTree:  MutableList<A> = ArrayList()

    override fun add(action: A): A {
        actionTree.add(action)
        return actionTree[actionTree.size - 1]
    }

    override fun startAnimation(view: View, duration: Long) {
        targetView = view
    }

    override fun getAnimator(): Animator<A> {
        return this
    }
}