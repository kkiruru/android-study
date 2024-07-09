package com.kkiruru.example.dialog

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dialog)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.showDialog).setOnClickListener {
            CommonDialog.showDialog(
                fragmentActivity = this,
                popupDialogState = CommonDialogState(
                    tag = "update",
                    title = "새로운 버전이 출시되었습니다",
                    cancelable = false,
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
                    when(bundle.customSerializable<DialogResult>(CommonDialog.RESULT)) {
                        is DialogResult.Left -> {
                            Toast.makeText(this, "취소 버튼", Toast.LENGTH_SHORT).show()
                        }
                        is DialogResult.Right -> {
                            Toast.makeText(this, "바로 업데이트 버튼", Toast.LENGTH_SHORT).show()
                        }

                        DialogResult.Cancel -> {
                            Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }
                }
            )
        }
    }
}
