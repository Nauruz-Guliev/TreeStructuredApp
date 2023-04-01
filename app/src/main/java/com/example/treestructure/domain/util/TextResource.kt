package com.example.treestructure.domain.util

import android.content.Context
import androidx.annotation.StringRes

data class TextResource(
    @StringRes
    val id: Int
) {
    fun toString(context: Context, vararg args: String) {
        context.getString(id, args)
    }
}
