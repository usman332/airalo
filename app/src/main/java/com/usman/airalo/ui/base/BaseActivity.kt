package com.usman.airalo.ui.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {


    val binding: VB by lazy { inflateViewBinding(layoutInflater) }

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    /*   fun isDarkModeEnabled() = appSettings.getBoolean(DARK_MODE, false)

       fun enableDarkMode(enable: Boolean) = appSettings.edit().putBoolean(DARK_MODE, enable).commit()
   */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // WindowCompat.setDecorFitsSystemWindows(window, false)
        /*
                window.apply {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
        */
        //setTheme(if (isDarkModeEnabled()) R.style.DarkTheme else R.style.LightTheme)
        setContentView(binding.root)
        //hideSystemUI()


    }


    protected fun <T> LiveData<T>.observe(observer: Observer<in T>) =
        observe(this@BaseActivity, observer)

    /*  companion object {
          const val DARK_MODE = "dark_mode"
      }*/
    fun errorMessageSnackBar(error: String) {
        Snackbar.make(
            binding.root,
            error,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    private fun hideSystemUI() {
        /* WindowCompat.setDecorFitsSystemWindows(window, false)
         binding?.root?.let {
             WindowInsetsControllerCompat(window, it).let { controller ->
                 controller.hide(WindowInsetsCompat.Type.systemBars())
                 controller.systemBarsBehavior =
                     WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
             }
         }*/
        /*     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                 window.setDecorFitsSystemWindows(false)
                 if (window.insetsController != null) {
                     window.insetsController!!.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                     window.insetsController!!.systemBarsBehavior =
                         WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                 }
             } else {
                 window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                         or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
             }*/

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

    }

}