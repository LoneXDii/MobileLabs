package com.example.calculator.Infrastructure.Persistence

import com.example.calculator.Domain.Entities.CalculatorState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val db = Firebase.firestore

    fun saveOperation(expression: String, result: String){
        val data = hashMapOf(
            "expression" to expression,
            "result" to result
        )

        db.collection("operations")
            .add(data)
    }

    suspend fun loadOperations(): List<CalculatorState>{
        return try {
            val snapshot = db.collection("operations").get().await()

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