package com.usman.airalo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

/**
 * An abstract base activity that provides common functionality for all activities in the application.
 *
 * @param VB The type of the ViewBinding used in the activity.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    /**
     * Lazily initializes the [ViewBinding] for the activity using the provided [layoutInflater].
     * The [ViewBinding] instance will be accessible via the [binding] property.
     *
     * @param inflater The [LayoutInflater] to inflate the view using the ViewBinding.
     * @return An instance of the specified [ViewBinding] for the activity.
     */
    val binding: VB by lazy { inflateViewBinding(layoutInflater) }

    /**
     * Method that must be implemented by subclasses to provide the appropriate [ViewBinding] instance for the activity.
     *
     * @param inflater The [LayoutInflater] to inflate the view using the ViewBinding.
     * @return An instance of the specified [ViewBinding] for the activity.
     */
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    /**
     * Called when the activity is being created. It sets the content view to the root view of the [ViewBinding].
     *
     * @param savedInstanceState The saved state of the activity if it was previously destroyed.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    /**
     * Observes a [LiveData] with a given [observer] in the context of this activity.
     *
     * @param observer The observer for the [LiveData] instance.
     */
    protected fun <T> LiveData<T>.observe(observer: Observer<in T>) =
        observe(this@BaseActivity, observer)

    //region UI functions

    /**
     * Shows a snackbar with the provided [error] message.
     *
     * @param error The error message to display in the snackbar.
     */
    fun errorMessageSnackBar(error: String) {
        Snackbar.make(
            binding.root,
            error,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    /**
     * Shows the provided [view] by setting its visibility to [View.VISIBLE].
     *
     * @param view The view to show.
     */
    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    /**
     * Hides the provided [view] by setting its visibility to [View.GONE].
     *
     * @param view The view to hide.
     */
    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    /**
     * Hides the system UI elements, such as the navigation bar and status bar, to provide an immersive experience.
     *
     * Note: This method uses deprecated APIs and is commented out. Use the appropriate methods based on the target API level.
     */
    private fun hideSystemUI() {
        // Method implementation can be uncommented if using the appropriate APIs for target API level.
        // For example, if using Android 12 and higher, use WindowInsetsController API.
        // If using older API versions, use systemUiVisibility API as shown in the commented code.
    }
    //endregion
}
