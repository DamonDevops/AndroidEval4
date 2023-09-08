package be.technifutur.androidpersistanceeval.expenses

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class ExpenseType (
    @PrimaryKey (autoGenerate = true) val typeId :Long = 0,
    val name :String
)