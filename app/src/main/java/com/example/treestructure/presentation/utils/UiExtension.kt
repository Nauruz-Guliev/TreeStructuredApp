package com.example.treestructure.presentation.utils

import android.content.Context
import android.widget.Toast

fun <T> Context.showToast(message: T) =
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()