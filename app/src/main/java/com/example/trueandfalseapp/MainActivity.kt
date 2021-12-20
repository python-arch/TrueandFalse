package com.example.trueandfalseapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var JSON_url:String= "https://api.thingspeak.com/channels/1606761/fields/1.json?results=2"
    lateinit var btn : Button
    lateinit var text:TextView
    var status = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn2 = findViewById<Button>(R.id.button_true2)
        btn = findViewById<Button>(R.id.button_true)
        text = findViewById(R.id.textView)
        val back = findViewById<View>(R.id.back)

        if (status ==false){
            btn.setBackgroundColor(Color.RED)
            btn2.setBackgroundColor(Color.RED)
            back.setBackgroundColor(Color.RED)
            btn.setText("false");
            btn2.setText("false");
        }
// the pulse animation

        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(btn ,
            PropertyValuesHolder.ofFloat("scaleX" , 1.1f) ,
            PropertyValuesHolder.ofFloat("scaleY", 1.1f))

        scaleDown.setDuration(650)
        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE

        scaleDown.start()

        val scaleDown2: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(btn2 ,
            PropertyValuesHolder.ofFloat("scaleX" , 1.1f) ,
            PropertyValuesHolder.ofFloat("scaleY", 1.1f))

        scaleDown2.setDuration(650)
        scaleDown2.repeatCount = ObjectAnimator.INFINITE
        scaleDown2.repeatMode = ObjectAnimator.REVERSE

        scaleDown2.start()


        load()

    }


   fun load(){
       var stringRequest = StringRequest(Request.Method.GET , JSON_url, Response.Listener { response ->
           var obj = JSONObject(response)
           var objec2 = obj.getJSONObject("channel");
           var value = objec2.getString("id");

           text.setText(value)

//           var array= JSONArray("feeds")
//           var valueArray = array.getJSONObject(0);
//           var value = valueArray.getString("value")
       } , Response.ErrorListener {
            Toast.makeText(this , "couldn't fetch the data" , Toast.LENGTH_SHORT)
       })
   }
}