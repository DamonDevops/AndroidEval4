package be.technifutur.androidpersistanceeval.expenses

import androidx.room.Embedded
import androidx.room.Relation


data class ExpenseWithType (
    @Embedded val expense :Expense,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "typeId"
    )
    val type :ExpenseType
)