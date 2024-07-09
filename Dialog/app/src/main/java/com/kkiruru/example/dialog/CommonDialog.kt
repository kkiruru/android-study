package com.kkiruru.example.dialog

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
import com.kkiruru.example.dialog.databinding.DialogCommonBinding
import java.io.Serializable

class CommonDialog : DialogFragment() {
    private lateinit var binding: DialogCommonBinding
    private lateinit var state : CommonDialogState

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("CommonDialog", "onViewCreated  savedInstanceState ${savedInstanceState}")
        Log.e("CommonDialog", "___ savedInstanceState  ${savedInstanceState?.customSerializable<CommonDialogState>("state")}")
        Log.e("CommonDialog", "___ arguments  ${arguments}")

        val bar = savedInstanceState?.customSerializable<CommonDialogState>("state")
        Log.e("CommonDialog", "___ bar  ${bar}")

        val foo = arguments?.customSerializable<CommonDialogState>("state")
        Log.e("CommonDialog", "___ foo  ${foo}")

        state = (arguments?.customSerializable<CommonDialogState>("state") ?: savedInstanceState?.getBundle("state") ?: CommonDialogState(
            tag = "",
            title = "에러",
            button = DialogButton.Single.Default(
                "닫기"
            )
        )) as CommonDialogState

        if (savedInstanceState != null && state.recovery.not()) {
            dismiss()
            return
        }

        if (state.recovery) {
            if (this.requireActivity() !is FragmentResultListener) {
                throw RuntimeException("recovery need FragmentResultListener implement")
            }
        }

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

        if (state.button is DialogButton.Single) {
            binding.singleButton.visibility = View.VISIBLE
            binding.multiButtonContainer.visibility = View.GONE

            binding.singleButton.text = (state.button as DialogButton.Single.Default).name

            binding.singleButton.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to DialogResult.Default
                    )
                )
                dismiss()
            }
        } else if (state.button is DialogButton.Pair) {
            binding.singleButton.visibility = View.GONE
            binding.multiButtonContainer.visibility = View.VISIBLE

            binding.multiButtonLeft.text = (state.button as DialogButton.Pair).left.name
            binding.multiButtonLeft.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to DialogResult.Left
                    )
                )
                dismiss()
            }

            binding.multiButtonRight.text = (state.button as DialogButton.Pair).right.name
            binding.multiButtonRight.setOnClickListener {
                setFragmentResult(state.tag,
                    bundleOf(
                        "result" to DialogResult.Right
                    )
                )
                dismiss()
            }
        }

        this.isCancelable = state.cancelable
        setLayoutWidth()
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e("CommonDialog", "onViewStateRestored ${savedInstanceState}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("CommonDialog", "onResume")

    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.e("CommonDialog", "onCancel")

        setFragmentResult(state.tag,
            bundleOf(
                "result" to DialogResult.Cancel
            )
        )
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.e("CommonDialog", "onDismiss")
    }

    override fun onPause() {
        super.onPause()
        Log.e("CommonDialog", "onPause")
        if (this.isCancelable) {
            dialog?.cancel()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("state", state)
        super.onSaveInstanceState(outState)

        Log.e("CommonDialog", "onSaveInstanceState  ${outState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("CommonDialog", "onDestroy")
    }

    private fun setLayoutWidth() {
        val deviceWidth = activity?.let { getScreenWidth(it) }

        if (deviceWidth != null) {
            val params = dialog?.window?.attributes
            params?.width = 320.dpToPixel.coerceAtMost((deviceWidth * 0.86).toInt())
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
        const val RESULT = "result"

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
            resultListener: FragmentResultListener? = null,
        ): CommonDialog {
            val dialog = newInstance(popupDialogState)
            resultListener?.also {
                fragmentActivity?.supportFragmentManager?.setFragmentResultListener(
                    popupDialogState.tag,
                    fragmentActivity,
                    resultListener
                )
            }
            dialog.show(fragmentActivity.supportFragmentManager, popupDialogState.tag)
            return dialog
        }
    }
}



data class CommonDialogState(
    val tag: String,
    val title: String? = null,
    val content: String? = null,
    val button: DialogButton,
    val cancelable: Boolean = true,
    val recovery: Boolean = false,
) : Serializable

sealed class DialogButton : Serializable {
    sealed class Single : DialogButton(), Serializable {
        data class Default(
            val name: String,
        ) : Single(), Serializable
    }

    data class Pair(
        val left: Left,
        val right: Right,
    ) : DialogButton(), Serializable {
        data class Left(
            val name: String,
        ) : Single(), Serializable
        data class Right(
            val name: String,
        ) : Single(), Serializable
    }
}


sealed class DialogResult : Serializable {
    data object Default : DialogResult(), Serializable {
        private fun readResolve(): Any = Default
    }

    data object Left : DialogResult(), Serializable {
        private fun readResolve(): Any = Left
    }

    data object Right : DialogResult(), Serializable {
        private fun readResolve(): Any = Right
    }

    data object Cancel : DialogResult(), Serializable {
        private fun readResolve(): Any = Cancel
    }

    data object Dismiss : DialogResult(), Serializable {
        private fun readResolve(): Any = Dismiss
    }
}

val Int.dpToPixel: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
