package com.example.aflamy.genrel

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.aflamy.R
import java.text.SimpleDateFormat
import java.util.Locale


fun navOptionsAnimation(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.from_right)
        .setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left)
        .setPopExitAnim(R.anim.to_right)
        .build()
}

fun navOptionsFromBottomAnimation(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.from_bottom)
        .setExitAnim(R.anim.to_top)
        .setPopEnterAnim(R.anim.from_top)
        .setPopExitAnim(R.anim.to_bottom)
        .build()

}

fun navOptionsFromTopAnimation(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.from_top)
        .setExitAnim(R.anim.to_bottom)
        .setPopEnterAnim(R.anim.from_bottom)
        .setPopExitAnim(R.anim.to_top)
        .build()

}

fun formatDate(dateString: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return date?.let { outputFormat.format(it) }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

// -------------------------------------------------------------- //