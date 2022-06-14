package com.example.team_a.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

import com.example.team_a.R
import com.example.team_a.ui.login.TimelineActivity
import com.example.team_a.data.model.CreateUserActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
class LoginActivity : AppCompatActivity() {
    //okhttpの追加？
    val client = OkHttpClient()

    //private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val myapp = application as Myapplication
        val userId = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        val login = findViewById<Button>(R.id.login)
        //val loading = findViewById<ProgressBar>(R.id.loading)

        //1.画面遷移用ボタンの取得。
        val btnIntent = findViewById<Button>(R.id.newbt)
        val intent = Intent(this, CreateUserActivity::class.java)

//        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
//                .get(LoginViewModel::class.java)
//
//        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
//            val loginState = it ?: return@Observer
//
//            // disable login button unless both username / password is valid
//            login.isEnabled = loginState.isDataValid
//
//            if (loginState.usernameError != null) {
//                userId.error = getString(loginState.usernameError)
//            }
//            if (loginState.passwordError != null) {
//                password.error = getString(loginState.passwordError)
//            }
//        })
//
//        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
//            val loginResult = it ?: return@Observer
//
//            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }
//            setResult(RESULT_OK)
//
//            //Complete and destroy login activity once successful
//            finish()
//        })
//
//        userId.afterTextChanged {
//            loginViewModel.loginDataChanged(
//                    userId.text.toString(),
//                    password.text.toString()
//            )
//        }
//
//        password.apply {
//            afterTextChanged {
//                loginViewModel.loginDataChanged(
//                        userId.text.toString(),
//                        password.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(
//                                userId.text.toString(),
//                                password.text.toString()
//                        )
//                }
//                false
//            }

            login.setOnClickListener {
//                loading.visibility = View.VISIBLE
//                loginViewModel.login(userId.text.toString(), password.text.toString())
                if(userId.getText().toString().equals("")){
                    Toast.makeText(applicationContext, "UserNameが空白", Toast.LENGTH_SHORT).show();
                    return@setOnClickListener
                }
                else if(password.getText().toString().equals("") ){
                    Toast.makeText(applicationContext, "パスワードが入力されていません", Toast.LENGTH_SHORT).show();
                    return@setOnClickListener
                }

                // phpのAPIを取得
                val url = myapp.apiUrl + "loginAuth.php?userId=" + userId.text.toString() +"&password=" + password.text.toString()
                val request = Request.Builder().url(url).build()

                //非同期通信での処理実行
                client.newCall(request).enqueue(object : Callback {
                    //正常終了した時
                    override fun onResponse(call: Call , response: Response) {

                        val csvStr = response.body()!!.string()
                        val resjson = JSONObject(csvStr)

                        //JSONデータがエラーの場合　トースト表示して処理を終了させる
                        if (resjson.getString("result").equals("error")) {
                            //ユーザIDが指定されていません
                            this@LoginActivity.runOnUiThread {
                                Toast.makeText(applicationContext , resjson.getString("errMsg") , Toast.LENGTH_LONG).show()
                            }
                            return
                        }
                        //グローバル変数loginUserIdに作成したユーザIDを格納する
                        myapp.loginUserId = userId.toString()
                        //タイムライン画面に遷移する
                        val intent = Intent(applicationContext , TimelineActivity::class.java)
                        startActivity(intent)
                        finish()//自分の画面を閉じる
                    }

                    //通信に失敗した時
                    override fun onFailure(call: Call , e: IOException) {
                        //エラーメッセージをトースト表示する
                        this@LoginActivity.runOnUiThread {
                            Toast.makeText(applicationContext , "通信失敗" , Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
            //クリエイト作成ボタンの作成
            btnIntent.setOnClickListener {
                startActivity(intent)
            }
        }
    }
//
//    private fun updateUiWithUser(model: LoggedInUserView) {
//        val welcome = getString(R.string.welcome)
//        val displayName = model.displayName
//        // TODO : initiate successful logged in experience
//        Toast.makeText(
//                applicationContext,
//                "$welcome $displayName",
//                Toast.LENGTH_LONG
//        ).show()
//    }
//
//    private fun showLoginFailed(@StringRes errorString: Int) {
//        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
//    }
//}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
//fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
//    this.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(editable: Editable?) {
//            afterTextChanged.invoke(editable.toString())
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//    })
//}