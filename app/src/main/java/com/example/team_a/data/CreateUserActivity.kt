package com.example.team_a.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.team_a.R
import com.example.team_a.ui.login.LoginActivity

class CreateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        //クリエイトボタンの取得
        val button = findViewById<Button>(R.id.crbt)
        //1.画面遷移用ボタンの取得。
        val btnIntent = findViewById<Button>(R.id.nobt)

        val editText = findViewById<EditText>(R.id.editName)
        val fistpass = findViewById<EditText>(R.id.editPass)
        val Repass = findViewById<EditText>(R.id.editPassword)


        //クリエイトボタンが押されたときの処理
        button.setOnClickListener({
            //UserNameが空白の時エラー出す
            if(editText.getText().toString().equals("") != false){
                Toast.makeText(applicationContext, "UserNameが空白", Toast.LENGTH_SHORT).show();
            }
            if(fistpass != Repass){
                Toast.makeText(applicationContext, "パスワードが違います", Toast.LENGTH_SHORT).show();
            }
        })

        //2.画面遷移用ボタンにリスナを登録。
        btnIntent.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(applicationContext, "キャンセルします", Toast.LENGTH_SHORT).show();
            startActivity(intent)
        }
    }
}



