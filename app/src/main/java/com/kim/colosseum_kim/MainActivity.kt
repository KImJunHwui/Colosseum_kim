package com.kim.colosseum_kim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kim.colosseum_kim.utilis.Serverutil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

//            입력한 이메일 / 비밀번호가 뭔지 변수에 저장
            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()

//            서버에 실제 회원이 맞는지 확인 요청.(Request)??

            Serverutil.postRequestLogin(inputEmail,inputPw, object : Serverutil.Companion.JsonReponseHandler{
                override fun onResponse(jsonObj: JSONObject) {


                    // jsonObj : 서버에서 내려준 본문을 JSON 형태로 가공해준 결과물.
                    // 이 내부를 파싱(분석)해서, 상황에 따른 대응.
                    // ex. 로그인 실패시, 그 이유를 토스트로 띄운다던지.


                    val code = jsonObj.getInt("code", )

                    if(code == 200){
//                        로그인 성공
                    }
                    else{
//                        로그인 실패

                        val message = jsonObj.getString("message")

                        runOnUiThread {    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }


                    }
                }






            })

        }
   }

    override fun setValues() {

    }


}