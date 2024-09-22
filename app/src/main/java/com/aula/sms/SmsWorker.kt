package com.aula.sms

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.telephony.SmsManager
import androidx.work.Data

class SmsWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val telefone = inputData.getString("telefone")
        val mensagem = inputData.getString("mensagem")

        return try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(telefone, null, mensagem, null, null)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}

