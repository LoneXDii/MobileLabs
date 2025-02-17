package com.example.calculator.Infrastructure.Persistence

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.ui.theme.Colors
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirebaseRepository(private val androidId: String) {
    private val db = Firebase.firestore

    fun saveOperation(expression: String, result: String){
        val data = hashMapOf(
            "expression" to expression,
            "result" to result,
            "androidId" to androidId
        )

        db.collection("operations")
            .add(data)
    }

    fun clearHistory() {
        db.collection("operations")
            .whereEqualTo("androidId", androidId)
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot.documents){
                    db.collection("operations")
                        .document(document.id)
                        .delete()
                }
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveUiTheme(){
        val data = hashMapOf(
            "androidId" to androidId,
            "DefaultTextColor" to colorToString(Colors.DefaultTextColor),
            "SecondaryTextColor" to colorToString(Colors.SecondaryTextColor),
            "ScientificOperationsButtonColor" to colorToString(Colors.ScientificOperationsButtonColor),
            "OperationButtonColor" to colorToString(Colors.OperationButtonColor),
            "HelpActionButtonColor" to colorToString(Colors.HelpActionButtonColor),
            "NumericButtonColor" to colorToString(Colors.NumericButtonColor),
            "MainBackground" to colorToString(Colors.MainBackground),
        )

        db.collection("configuration")
            .whereEqualTo("androidId", androidId)
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot.documents){
                    db.collection("configuration")
                        .document(document.id)
                        .delete()
                }

                db.collection("configuration")
                    .add(data)
            }
    }

    suspend fun loadUiTheme(){
        try {
            val snapshot = db.collection("configuration")
                .whereEqualTo("androidId", androidId)
                .get()
                .await()

            val document = snapshot.documents.firstOrNull()

            if (document != null){
                Colors.DefaultTextColor = hexToColor(document.getString("DefaultTextColor").toString())
                Colors.SecondaryTextColor = hexToColor(document.getString("SecondaryTextColor").toString())
                Colors.ScientificOperationsButtonColor = hexToColor(document.getString("ScientificOperationsButtonColor").toString())
                Colors.OperationButtonColor = hexToColor(document.getString("OperationButtonColor").toString())
                Colors.HelpActionButtonColor = hexToColor(document.getString("HelpActionButtonColor").toString())
                Colors.NumericButtonColor = hexToColor(document.getString("NumericButtonColor").toString())
                Colors.MainBackground = hexToColor(document.getString("MainBackground").toString())
            }
        } catch (e: Exception) {
            Log.d("TEST", e.message.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun colorToString(color: Color): String{
        val alpha = (color.alpha * 255).toInt().toString(16).padStart(2, '0')
        val red = (color.red * 255).toInt().toString(16).padStart(2, '0')
        val green = (color.green * 255).toInt().toString(16).padStart(2, '0')
        val blue = (color.blue * 255).toInt().toString(16).padStart(2, '0')

        return "#$alpha$red$green$blue"
    }

    private fun hexToColor(hex: String): Color {
        val col = hex.drop(1)
        val color = if (col.length == 6) "FF$col" else col

        return Color(color.toLong(16))
    }
}