package com.example.englern.tabs

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

class FixedBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    init {
        // Disable shifting mode to prevent icon and label animation
        labelVisibilityMode = LABEL_VISIBILITY_UNLABELED
    }
}