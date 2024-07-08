package com.kkiruru.dialogexample

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable

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
            val foo = CommonDialog.newInstance(
                popupDialogState = CommonDialogState(
                    tag = "update",
                    title = "새로운 버전이 출시되었습니다",
                    cancelable = false,
                    popupButton = PopupButton.Pair(
                        left = PopupButton.Pair.Left(
                            "취소",
                        ),
                        right = PopupButton.Pair.Right(
                            "바로 업데이트",
                        )
                    )
                ),
//                listener = { _, bundle ->
//                    when(bundle.customSerializable<PopupButton>("result")) {
//                        is PopupButton.Pair.Left -> {
//                            Toast(this).setText(">>> ")
//                        }
//                        is PopupButton.Pair.Right -> {
//
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
                )
            foo.show(this.supportFragmentManager, "")
        }
    }
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}