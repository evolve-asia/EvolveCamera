package com.evolve.cameralib

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.evolve.cameralib.ui.EvolveCameraActivity

/**
 * Create EvolveImagePicker object
 * */
class EvolveImagePicker {

    companion object {
        const val KEY_FRONT_CAMERA = "frontCamera"
        const val KEY_CAMERA_CAPTURE_FORCE = "forceCameraCapture"
        const val KEY_FILENAME = "IMG_FILENAME"

        /**
         * Use this to use EvolveImagePicker in Activity Class
         *
         * @param activity AppCompatActivity Instance
         */
        @JvmStatic
        fun with(activity: Activity): Builder {
            return Builder(activity)
        }

        /**
         * Calling from fragment
         * */
        @JvmStatic
        fun with(fragment: Fragment): Builder {
            return Builder(fragment)
        }
    }

    class Builder(private val activity: Activity) {

        private var fragment: Fragment? = null

        constructor(fragment: Fragment) : this(fragment.requireActivity()) {
            this.fragment = fragment
        }

        fun start(
            launcher: ActivityResultLauncher<Intent>,
            forceImageCapture: Boolean = true,
            enabledFrontCamera: Boolean = true,
            fileName: String = ""
        ) {
            startActivity(
                launcher,
                forceImageCapture,
                enabledFrontCamera,
                fileName
            )
        }

        private fun startActivity(
            launcher: ActivityResultLauncher<Intent>,
            forceImageCapture: Boolean,
            enabledFrontCamera: Boolean,
            fileName: String,
        ) {
            val imagePickerIntent: Intent = if (fragment != null) {
                Intent(fragment?.requireActivity(), EvolveCameraActivity::class.java)
            } else {
                Intent(activity, EvolveCameraActivity::class.java)
            }
            imagePickerIntent.putExtra(KEY_CAMERA_CAPTURE_FORCE, forceImageCapture)
            imagePickerIntent.putExtra(KEY_FRONT_CAMERA, enabledFrontCamera)
            imagePickerIntent.putExtra(KEY_FILENAME, fileName)
            launcher.launch(imagePickerIntent)
        }
    }
}