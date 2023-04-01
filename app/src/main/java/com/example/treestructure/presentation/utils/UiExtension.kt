package com.example.treestructure.presentation.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun <T> Context.showToast(message: T) =
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()

fun <T> View.showSnackBar(message: T) =
    Snackbar.make(this, message.toString(), Snackbar.LENGTH_SHORT).show()
