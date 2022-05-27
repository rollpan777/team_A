package com.example.team_a.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.team_a.R
import com.example.team_a.TimelineActivity
import com.example.team_a.ui.login.LoginActivity

class CreateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        //クリエイトボタンの取得
        val button = findViewById<Button>(R.id.crbt)
        //1.画面遷移用ボタンの取得。
        val btnIntent = findViewById<Button>(R.id.nobt)

        val editText = findViewById<EditText>(R.id.userNameEdit)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val fistpass = findViewById<EditText>(R.id.editPassword)
        val Repass = findViewById<EditText>(R.id.editPass)


        //クリエイトボタンが押されたときの処理
        button.setOnClickListener{
            //UserNameが空白の時エラー出す
            if(editText.getText().toString().equals("") != false){
                Toast.makeText(applicationContext, "UserNameが空白", Toast.LENGTH_SHORT).show();
            }
            //e-mailが未入力の場合エラー
            else if(editEmail.getText().toString().equals("") != false){
                Toast.makeText(applicationContext, "E-mailが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //パスワードが入力されていない場合エラー
            else if(fistpass.getText().toString().equals("") != false){
                Toast.makeText(applicationContext, "パスワードが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //確認パスワードが入力されていない場合エラー
            else if(Repass.getText().toString().equals("") != false){
                Toast.makeText(applicationContext, "確認パスワードが入力されていません", Toast.LENGTH_SHORT).show();
            }
            //パスワードが違いｍス
            else if(fistpass.getText().toString() != Repass.getText().toString()){
                Toast.makeText(applicationContext, "パスワードが違います", Toast.LENGTH_SHORT).show();
            }
            else{
                //phpのほうができていないため、usernameとpassを入力したら路銀画面に飛ぶようにした　4/22
                val intent = Intent(this, TimelineActivity::class.java)
                startActivity(intent)
            }

        }

        //2.画面遷移用ボタンにリスナを登録。
        btnIntent.setOnClickListener {
            val intent = Intent(this, TimelineActivity::class.java)
            Toast.makeText(applicationContext, "キャンセルします", Toast.LENGTH_SHORT).show();
            startActivity(intent)
        }
    }
}



