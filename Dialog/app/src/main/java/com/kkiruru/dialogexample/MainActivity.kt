package com.kkiruru.dialogexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentResultListener
import com.kkiruru.dialogexample.ui.theme.DialogExampleTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")

        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White)
                            .imePadding()
                            .windowInsetsPadding(
                                WindowInsets.systemBars.only(
                                    WindowInsetsSides.Bottom
                                )
                            )
                    ) { innerPadding ->
                        MainApp(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            onUpdateDialog = {
                                Log.e("MainActivity", "CommonDialog 을 생성하다 ")
                                CommonDialog.showDialog(
                                    this,
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
                                    )
                                )
                            },
                            onTestDialog = {
                                CommonDialog.showDialog(
                                    this,
                                    popupDialogState = CommonDialogState(
                                        tag = "test",
                                        title = "테스트 Dialog",
                                        cancelable = true,
                                        popupButton = PopupButton.Single.Default(
                                            name = "취소",
                                        ),
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")

        FragmentResultListener { _, bundle ->
            when(bundle.customSerializable<PopupButton>("result")) {
                is PopupButton.Pair.Left -> {
                    Toast.makeText(this, "왼쪽", Toast.LENGTH_SHORT).show()
                }
                is PopupButton.Pair.Right -> {
                    Toast.makeText(this, "오른쪽", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }.also {
            Log.e("CommonDialog", "setFragmentResultListener를 설정하다 ")
            supportFragmentManager.setFragmentResultListener(
                "update",
                this,
                it
            )
        }

        FragmentResultListener { _, bundle ->
            when(bundle.customSerializable<PopupButton>("result")) {
                is PopupButton.Single.Default -> {
                    Toast.makeText(this, "테스트 완료", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }.also {
            Log.e("CommonDialog", "setFragmentResultListener를 설정하다 ")
            supportFragmentManager.setFragmentResultListener(
                "test",
                this,
                it
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("MainActivity", "onSaveInstanceState ${outState}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("MainActivity", "onRestoreInstanceState ${savedInstanceState}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy")
    }
}


@Composable
fun MainApp(
    modifier: Modifier,
    onUpdateDialog: () -> Unit,
    onTestDialog: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting(
                name = "Android",
                modifier = Modifier
            )

            Button(onClick = {
                onUpdateDialog.invoke()
            }) {
                Text(text = "update Dialog")
            }

            Button(onClick = {
                onTestDialog.invoke()
            }) {
                Text(text = "test Dialog")
            }

        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
private fun MainPreview() {
    DialogExampleTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .imePadding()
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.systemBars.only(
                        WindowInsetsSides.Bottom
                    )
                )
        ) { innerPadding ->
            MainApp(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onUpdateDialog = {},
                onTestDialog = {},
            )
        }
    }
}