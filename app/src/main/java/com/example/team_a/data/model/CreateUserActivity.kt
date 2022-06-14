package com.example.team_a.data.model


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.team_a.R
import com.example.team_a.ui.login.TimelineActivity
import com.example.team_a.ui.login.LoginActivity
import com.example.team_a.ui.login.Myapplication
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class CreateUserActivity : AppCompatActivity() {

    //okhttpの追加？
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        //Myapplication
        val myapp  = application as Myapplication
        //クリエイトボタンの取得
        val button = findViewById<Button>(R.id.crbt)
        //1.画面遷移用ボタンの取得。
        val btnIntent = findViewById<Button>(R.id.nobt)

        val userId = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val password = findViewById<EditText>(R.id.editPassword)
        val Repass = findViewById<EditText>(R.id.editPass)



        //ｔｅｓｔボタン
        val testbt = findViewById<Button>(R.id.button)

        //クリエイトボタンが押されたときの処理
        button.setOnClickListener{
            //UserNameが空白の時エラー出す
            if(userId.getText().toString().equals("") ){
                Toast.makeText(applicationContext, "UserNameが空白", Toast.LENGTH_SHORT).show();
            }
            //e-mailが未入力の場合エラー
            else if(editEmail.getText().toString().equals("") ){
                Toast.makeText(applicationContext, "E-mailが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //パスワードが入力されていない場合エラー
            else if(password.getText().toString().equals("") ){
                Toast.makeText(applicationContext, "パスワードが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //確認パスワードが入力されていない場合エラー
            else if(Repass.getText().toString().equals("") ){
                Toast.makeText(applicationContext, "確認パスワードが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //パスワードが違いｍス
            else if(password.getText().toString() != Repass.getText().toString()){
                Toast.makeText(applicationContext, "パスワードが違います", Toast.LENGTH_SHORT).show();
            }
            else{
                // phpのAPIを取得
                val url = myapp.apiUrl + "userAdd.php?userId=" + editEmail.text.toString()+ "&name=" + userId.text.toString() +"&password=" + password.text.toString()
                val request = Request.Builder().url(url).build()

                //非同期通信での処理実行
                client.newCall(request).enqueue(object : Callback {
                    //正常終了した時
                    override fun onResponse(call: Call, response: Response) {

                        val csvStr = response.body()!!.string()
                        val resjson = JSONObject(csvStr)

                        //JSONデータがエラーの場合　トースト表示して処理を終了させる
                        if (resjson.getString("result").equals("error")) {
                            //ユーザIDが指定されていません
                            this@CreateUserActivity.runOnUiThread {
                                Toast.makeText(applicationContext , resjson.getString("errMsg") , Toast.LENGTH_LONG).show()
                            }
                            return
                        }
                        //グローバル変数loginUserIdに作成したユーザIDを格納する
                        myapp.loginUserId = editEmail.toString()
                        //タイムライン画面に遷移する
                        val intent = Intent(applicationContext , TimelineActivity::class.java)
                        startActivity(intent)
                        finish()//自分の画面を閉じる
                    }

                    //通信に失敗した時
                    override fun onFailure(call: Call, e: IOException) {
                        //エラーメッセージをトースト表示する
                        this@CreateUserActivity.runOnUiThread {
                            Toast.makeText(applicationContext , "通信失敗" , Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }


        //2.画面遷移用ボタンにリスナを登録。
        btnIntent.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(applicationContext, "キャンセルします", Toast.LENGTH_SHORT).show();
            startActivity(intent)
        }

    }
}



