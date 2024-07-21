package com.kkiruru.example.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kkiruru.example.dialog.databinding.FragmentDialogBinding

class DialogFragment : Fragment() {

    private var _binding: FragmentDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("HomeFragment", "onCreateView")

        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.dialog1.setOnClickListener {
            CommonDialog.showDialog(
                fragmentActivity = requireActivity(),
                popupDialogState = CommonDialogState(
                    tag = "update",
                    cancelable = false,
                    title = "Fragment 에서 Dialog",
                    content = "팝업을 유지하지 않습니다.",
                    button = DialogButton.Pair(
                        left = DialogButton.Pair.Left(
                            "취소",
                        ),
                        right = DialogButton.Pair.Right(
                            "바로 업데이트",
                        )
                    ),
                ),
                resultListener = { _, bundle ->
                    when (bundle.customSerializable<DialogResult>(CommonDialog.RESULT)) {
                        is DialogResult.Left -> {
                            Toast.makeText(requireActivity(), "취소 버튼", Toast.LENGTH_SHORT).show()
                        }

                        is DialogResult.Right -> {
                            Toast.makeText(requireActivity(), "바로 업데이트 버튼", Toast.LENGTH_SHORT).show()
                        }

                        DialogResult.Cancel -> {
                            Toast.makeText(requireActivity(), "취소됨", Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }
                }
            )
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("DialogFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("DialogFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("DialogFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("DialogFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("DialogFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("DialogFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("DialogFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("DialogFragment", "onDetach")
    }
}