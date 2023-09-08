package be.technifutur.androidpersistanceeval.expenses

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate


@Entity(foreignKeys = [
    ForeignKey(
        ExpenseType::class,
        parentColumns = ["typeId"],
        childColumns = ["typeId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Expense (
    @PrimaryKey (autoGenerate = true) val expenseId :Long = 0,
    val name :String?,
    val date : LocalDate?,
    val value :Float?,
    val typeId :Long?
    )