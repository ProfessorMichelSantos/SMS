package com.aula.sms

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scheduleSms(this, "41991335793", "mensagem enviada por background")

        val botao: Button = findViewById(R.id.send)
        val mensagem: EditText = findViewById(R.id.mensagem)
        val num: EditText = findViewById(R.id.telefone)

        val smsmanager: SmsManager? = this.getSystemService(SmsManager::class.java)

        botao.setOnClickListener {
            val texto = mensagem.text.toString()
            Toast.makeText(this.baseContext, "Prestes a Enviar", Toast.LENGTH_LONG).show()

            smsmanager?.sendTextMessage(num.text.toString(), null, texto, null, null)

            Toast.makeText(this.baseContext, "Mensagem Enviada", Toast.LENGTH_LONG).show()
        }
    }

    private fun scheduleSms(context: Context, telefone: String, mensagem: String) {
        val data = Data.Builder()
            .putString("telefone", telefone)
            .putString("mensagem", mensagem)
            .build()

        val smsWorkRequest = OneTimeWorkRequestBuilder<SmsWorker>()
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(smsWorkRequest)
    }
}
