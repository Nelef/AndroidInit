package com.example.androidinit.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.androidinit.databinding.ActivityMainBinding
import com.example.androidinit.view.ui.theme.AndroidInitTheme

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    // 뷰모델
    val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    // 퍼미션 체크
    private val PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.CAMERA,
//        Manifest.permission.RECORD_AUDIO,
//        // SIM 전화번호 추출 : 사용자의 안드로이드 버전에 따라 권한을 다르게 요청
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            Manifest.permission.READ_PHONE_NUMBERS
//        } else {
//            Manifest.permission.READ_PHONE_STATE
//        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AndroidInitTheme {
                    // uiState 팝업 넣을만함.
                }
            }
        }

        setContentView(binding.root)
        checkPermissions(PERMISSIONS, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            // 안드로이드 13이상일 시, 그 권한이 WRITE_EXTERNAL_STORAGE 이라면 스킵.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                for (permissionIdx in permissions.indices) {
                    if (permissions[permissionIdx] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                        grantResults[permissionIdx] = PackageManager.PERMISSION_GRANTED
                    }
                }
            }
            val isGranted: Boolean = checkUserAcceptPermissions(grantResults)
            if (!isGranted) {
                Log.e(TAG, "PermissionError")
            }
        }
    }

    private fun checkPermissions(
        permissions: Array<String>,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var result: Int
            val permissionList: MutableList<String> = ArrayList()
            for (pm in permissions) {
                result = ContextCompat.checkSelfPermission(this, pm)
                if (result != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(pm)
                }
            }
            if (permissionList.isNotEmpty()) {
                requestPermissions(permissionList.toTypedArray(), requestCode)
            }
        }
    }

    private fun checkUserAcceptPermissions(grantResults: IntArray): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (grantResults.isEmpty()) {
            return false
        }
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }
}