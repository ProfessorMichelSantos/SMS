package com.aula.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.media3.common.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val botao : Button = findViewById<Button>(R.id.send)
        val mensagem : EditText = findViewById<EditText>(R.id.mensagem)
        val num : EditText = findViewById<EditText>(R.id.telefone)

        val smsmanager: SmsManager? =  this.getSystemService<SmsManager>(SmsManager::class.java)

        botao.setOnClickListener{
            val texto = mensagem.text.toString()
            Toast.makeText(this.baseContext, "Prestes a Enviar", Toast.LENGTH_LONG).show()
            Log.i("vai enviar", "vai enviar")
            smsmanager?.sendTextMessage(num.text.toString(), null, texto, null, null)
            Log.i("enviou", "enviou")
            Toast.makeText(this.baseContext, "Mensagem Enviada", Toast.LENGTH_LONG).show()


        }


    }
}