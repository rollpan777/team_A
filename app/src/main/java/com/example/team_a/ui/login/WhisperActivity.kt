package com.example.team_a.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.team_a.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class WhisperActivity : AppCompatActivity() {
    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whisper)

        val myapp  = application as Myapplication
        val whisperEdit = findViewById<EditText>(R.id.WhisperEdit)
        val whisperButton = findViewById<Button>(R.id.Whisperbutton)
        val CancelButton = findViewById<Button>(R.id.CancelButton)



        whisperButton.setOnClickListener {
            //入力項目が空白の時、エラーメッセージをトースト表示して処理を終了させる
            if (whisperEdit.getText().toString().equals("")) {
                Toast.makeText(applicationContext, "ささやきがありません", Toast.LENGTH_SHORT).show();
            }
            //ささやき登録処理APIをリクエストして入力したささやきの登録処理を行う
            val url = myapp.apiUrl + "whisperAdd.php?content=" + whisperEdit.text
                .toString() + "&userId=" + myapp.loginUserId
            val request = Request.Builder().url(url).build()
            //非同期通信での処理実行
            client.newCall(request).enqueue(object : Callback {
                //正常終了した時
                override fun onResponse(call: Call, response: Response) {

                    val csvStr = response.body()!!.string()
                    val resjson = JSONObject(csvStr)

                    //JSONデータがエラーの場合　トースト表示して処理を終了させる
                    if (resjson.getString("result").equals("error")) {
                        this@WhisperActivity.runOnUiThread {
                            Toast.makeText(
                                applicationContext,
                                resjson.getString("errMsg"),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return
                    }
                    //

                    //ユーザー情報画面に遷移する
                    val intent = Intent(applicationContext, UserInfoActivity::class.java)
                    //インテントにログインユーザIDをセットする
                    intent.putExtra("loginUserId", myapp.loginUserId)
                    startActivity(intent)
                    finish()//自分の画面を閉じる
                }

                //通信に失敗した時
                override fun onFailure(call: Call, e: IOException) {
                    //エラーメッセージをトースト表示する
                    this@WhisperActivity.runOnUiThread {
                        Toast.makeText(applicationContext, "通信失敗", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        CancelButton.setOnClickListener({
            val intent = Intent(this, WhisperActivity::class.java)
            startActivity(intent)
        })

    }
    //オプションメニュー生成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflowmenu,menu)
        return true
    }
    //オプションメニューアイテム選択時
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val returnVal = true

        when(item.itemId){
            //それぞれのメニュー選択処理
            R.id.timeline ->{
                val intent = Intent(applicationContext , TimelineActivity::class.java)
                startActivity(intent)}
            //R.id.search ->{}
            R.id.whisper ->{
                val intent = Intent(applicationContext , WhisperActivity::class.java)
                startActivity(intent)}
            //R.id.profile ->{}
            //R.id.useredit ->{}
            //R.id.logout ->{}
        }
        return  returnVal
    }

//    override fun onCreateOptionsMenu(item: Menu?): Boolean {
//        menuInflater.inflate(R.menu.overflowmenu,item)
//        //return true
//        var returnVal = true
//        when(item.itemId){
//            R.id.timeline -> {
//                intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        return returnVal
//    }
}