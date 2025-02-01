package com.kkiruru.android.ui

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kkiruru.android.ui.databinding.FragmentCustomBottomSheetDialogBinding


// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCustomBottomSheetDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 100 / 100
        // 기기 높이 대비 비율 설정 부분!!
        // 위 수치는 기기 높이 대비 80%로 다이얼로그 높이를 설정
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCustomBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (dialog as? BottomSheetDialog)?.behavior?.apply {
            skipCollapsed = true

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomsheet: View, newState: Int) {
                    // BottomSheetBehavior의 5가지 상태
                    Log.d(TAG, "onStateChanged $newState")
                    when(newState) {
                        // 사용자가 BottomSheet를 위나 아래로 드래그 중인 상태
                        BottomSheetBehavior.STATE_DRAGGING -> {
//                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        // 드래그 동작 후 BottomSheet가 특정 높이로 고정될 때의 상태
                        // SETTLING 후 EXPANDED, SETTLING 후 COLLAPSED, SETTLING 후 HIDDEN
                        BottomSheetBehavior.STATE_SETTLING -> {
//                        if (isOver) {
//                            behavior.state = BottomSheetBehavior.STATE_HIDDEN
//                        }
                        }

                        // 최대 높이로 보이는 상태
                        BottomSheetBehavior.STATE_EXPANDED -> { }

                        // peek 높이 만큼 보이는 상태
                        BottomSheetBehavior.STATE_COLLAPSED -> {
//                            state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        // 숨김 상태
                        BottomSheetBehavior.STATE_HIDDEN -> {

                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            state = BottomSheetBehavior.STATE_EXPANDED
                        }

                    }
                }

                override fun onSlide(p0: View, slideOffset: Float) {
                    // slideOffset 범위: -1.0 ~ 1.0
                    // -1.0 HIDDEN, 0.0 COLLAPSED, 1.0 EXPANDED
                    Log.d(TAG, "onSlide $slideOffset")
                }
            })
        }
    }

    companion object {
        const val TAG = "CustomBottomSheet"

        fun newInstance(itemCount: Int): CustomBottomSheetDialogFragment =
            CustomBottomSheetDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
            }
    }
}