package com.kim.colosseum_kim.utilis

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Serverutil {
//    어떤 내용? = 서버 연결 전담.

companion object{

    interface  JsonReponseHandler{
        fun onResponse(jsonObj: JSONObject)

    }

//    모든 기능의 기본이 되는 주소.
    val BASE_URL = "http://54.180.52.26"

//    로그인 하는 기능

    fun postRequestLogin(email : String, pw : String, handler: JsonReponseHandler?){

//        서버에 입력받은 email, pw 전달 => 로그인 기능 POST/user => 요청 (Request) 실행
//        라이브러리(okHttp) 활용해 보자


//        http://54.180.52.26/user + POST + 파라미터들 첨부.
//        호스트 주소 + 기능주소 결합
        val urlString = "${BASE_URL}/user"

//        POST 방식 => 파라미터를 폼데이터에 담아주자

        val formData = FormBody.Builder()
            .add("email", email)
            .add("password", pw)
            .build()

//        어디로 어떻게 어떤 데이터를 들고 가는지를 모두 종합해둔 Request 변수 생성
        val request = Request.Builder()
            .url(urlString)
            .post(formData)
            .build()

//        클라이언트로써의 동작 : Request 요청 실행. =>okHttp라이브러리 지원.

        val client = OkHttpClient()

//        실제로 서버에 요청 날리기 => 갔다 와서는 뭘 한거지?
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//                서버에 연결 자체를 실패한 경우
//                서버 마비, 인터넷 단선.

            }

            override fun onResponse(call: Call, response: Response) {

//                로그인 성공, 로그인 실패(연결 성공 -> 검사 실패)
//                응답이 돌아온 경우

                val bodyString = response.body!!.string()

//                bodystring 변수에는 한글이 깨져있다. => JSONOBject로 변환하면, 한글 정상 처리

                val jsonObj = JSONObject(bodyString)

                Log.d("응답본문",jsonObj.toString())

//                handler 변수가 null이 아니라면,실체가 있다면
//                그 내부에 적힌 내용 실행

                handler?.onResponse(jsonObj)
            }


        })



    }
}

}