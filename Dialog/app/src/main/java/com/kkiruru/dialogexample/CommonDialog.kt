package com.kkiruru.dialogexample

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import com.kkiruru.dialogexample.databinding.DialogCommonBinding
import java.io.Serializable

class CommonDialog : DialogFragment() {
    private lateinit var binding: DialogCommonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.e("CommonDialog", "onCreateView  ${savedInstanceState}")

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.dialog_common,
            container,
            false
        )

        this.parentFragmentManager
        val foo = this.requireActivity() as FragmentActivity
        foo.supportFragmentManager.toString()





        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle("state", arguments)
        super.onSaveInstanceState(outState)

        Log.e("CommonDialog", "onSaveInstanceState  ${outState}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("CommonDialog", "onViewCreated  savedInstanceState ${savedInstanceState}")
        Log.e("CommonDialog", "___ arguments  ${arguments}")

        val bundle = arguments ?: savedInstanceState

        val state = bundle?.customSerializable<CommonDialogState>("state") ?:  CommonDialogState(
            tag = "",
            title = "에러",
            popupButton = PopupButton.Single.Default(
                "닫기"
            )
        )

        if (state.title.isNullOrEmpty()) {
            binding.title.visibility = View.GONE
        } else {
            binding.title.visibility = View.VISIBLE
            binding.title.text = state.title
        }

        if (state.content.isNullOrEmpty()) {
            binding.content.visibility = View.GONE
        } else {
            binding.content.visibility = View.VISIBLE
            binding.content.text = state.content
        }

        if (state.popupButton is PopupButton.Single) {
            binding.singleButton.visibility = View.VISIBLE
            binding.multiButtonContainer.visibility = View.GONE

            binding.singleButton.text = (state.popupButton as PopupButton.Single.Default).name

            binding.singleButton.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to state.popupButton
                    )
                )
                dismiss()
            }
        } else {
            binding.singleButton.visibility = View.GONE
            binding.multiButtonContainer.visibility = View.VISIBLE

            binding.multiButtonLeft.text = (state.popupButton as PopupButton.Pair).left.name
            binding.multiButtonLeft.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to state.popupButton.left
                    )
                )
                dismiss()
            }

            binding.multiButtonRight.text = (state.popupButton as PopupButton.Pair).right.name
            binding.multiButtonRight.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to state.popupButton.right
                    )
                )
                dismiss()
            }
        }

        this.isCancelable = state.cancelable
    }

    override fun onResume() {
        super.onResume()
        Log.e("CommonDialog", "onResume")

        setLayoutWidth()
    }

    override fun onPause() {
        super.onPause()
        Log.e("CommonDialog", "onPause")
        if (this.isCancelable) {
            dialog?.cancel()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e("CommonDialog", "onViewStateRestored ${savedInstanceState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("CommonDialog", "onDestroy")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.e("CommonDialog", "onCancel")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.e("CommonDialog", "onDismiss")
    }

    private fun setLayoutWidth() {
        val deviceWidth = activity?.let { getScreenWidth(it) }
        if (deviceWidth != null) {
            val params = dialog?.window?.attributes
            params?.width = 300.dpToPixel.coerceAtMost((deviceWidth * 0.86).toInt())
            dialog?.window?.attributes = params as WindowManager.LayoutParams
        }
    }

    private fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics =
                (activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics
            val windowInsets: WindowInsets = windowMetrics.windowInsets
            val insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
            )
            val insetsWidth = insets.right + insets.left
            val bound = windowMetrics.bounds
            bound.width() - insetsWidth
        } else {
            @Suppress("DEPRECATION")
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            @Suppress("DEPRECATION")
            display.getSize(size)
            size.x
        }
    }

    companion object {
        fun newInstance(
            popupDialogState: CommonDialogState,
        ): CommonDialog {
            val dialog = CommonDialog().apply {
                try {
                    arguments = Bundle().apply {
                        this.putSerializable("state", popupDialogState)
                    }
                } catch (e: Exception) { }
            }
            return dialog
        }

        fun showDialog(
            fragmentActivity: FragmentActivity,
            popupDialogState: CommonDialogState,
        ): CommonDialog {
            val dialog = newInstance(popupDialogState)
            dialog.show(fragmentActivity.supportFragmentManager, popupDialogState.tag)
            return dialog
        }
    }
}


data class CommonDialogState(
    val tag: String,
    val title: String? = null,
    val content: String? = null,
    val popupButton: PopupButton,
    val cancelable: Boolean = true,
) : Serializable

sealed class PopupButton : Serializable {
    sealed class Single : PopupButton(), Serializable {
        data class Default(
            val name: String,
        ) : Single(), Serializable
    }

    data class Pair(
        val left: Left,
        val right: Right,
    ) : PopupButton(), Serializable {
        data class Left(
            val name: String,
        ) : Single(), Serializable
        data class Right(
            val name: String,
        ) : Single(), Serializable
    }
}


val Int.dpToPixel: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()



