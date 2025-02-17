package com.example.calculator.Infrastructure.Persistence

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.example.calculator.Domain.Entities.CalculatorState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirebaseRepository(private val androidId: String) {
    private val db = Firebase.firestore

    fun saveOperation(expression: String, result: String){
        try {
            val data = hashMapOf(
                "expression" to expression,
                "result" to result,
                "androidId" to androidId
            )

            db.collection("operations")
                .add(data)
        }
        catch (e: Exception){
            Log.e("FirebaseRepository.SaveOperation", e.message.toString())
        }
    }

    suspend fun loadOperations(): List<CalculatorState>{
        return try {
            val snapshot = db.collection("operations")
                .whereEqualTo("androidId", androidId)
                .get()
                .await()

            snapshot.documents.map { document ->
                CalculatorState(
                    expression = document.getString("expression") ?: "",
                    tempResult = document.getString("result") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}