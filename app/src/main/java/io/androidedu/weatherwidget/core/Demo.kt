package io.androidedu.weatherwidget.core

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import io.androidedu.weatherwidget.R

class Demo : Activity() {

    private val edtDemo by lazy { findViewById<EditText>(R.id.activity_demo_edtDemo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

                btnLogin.enable = edtDemo.text.trim().length > 0 && edtDemo2.text.trim().lenght > 0
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                Log.e("beforeTextChanged", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0!!.startsWith(" ")) {
                    edtDemo.text = edtDemo.text
                }
            }
        }

        edtDemo.addTextChangedListener(textWatcher)
    }
}
