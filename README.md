# DoraAnimator

描述：一个Android动画框架

复杂度：★★☆☆☆

分组：【Dora大控件组】

关系：暂无

技术要点：Path的使用、二三阶贝瑟尔曲线、属性动画

### 照片

![avatar](https://github.com/dora4/dora_animator/blob/main/art/dora_animator.jpg)

### 动图

![avatar](https://github.com/dora4/dora_animator/blob/main/art/dora_animator.gif)

### 软件包

https://github.com/dora4/dora_animator/blob/main/art/dora_animator.apk

### 用法

动画可以进行叠加，且所有节点都是以原控件为参考。

```kotlin
val tv = findViewById<TextView>(R.id.tv)
(LineTo(100f, 0f) + LineTo(0f, 100f)).startAnimation(tv, 3000)
(RotateAction(90f) + RotateAction(180f)).startAnimation(tv, 3000)
(AlphaAction(0.5f) + AlphaAction(1f)).startAnimation(tv, 3000)
(ScaleAction(2f, 2f) + ScaleAction(1f, 1f)).startAnimation(tv, 3000)
```

