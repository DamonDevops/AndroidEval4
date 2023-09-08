package be.technifutur.androidpersistanceeval.expenses

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface ExpenseDao {
    @Transaction
    @Query("SELECT * FROM Expense")
    fun getExpensesWithType() : Flow<List<ExpenseWithType>>
    @Query("SELECT * FROM ExpenseType")
    fun getAllType() :List<ExpenseType>
    @Query("SELECT * FROM Expense")
    fun getAllExpenses() : List<Expense>
    @Query("SELECT typeId FROM ExpenseType WHERE name = :name LIMIT 1")
    fun getTypeId(name :String) :Long
    @Insert
    suspend fun addExpense(expense :Expense)
    @Insert
    suspend fun addType(type :ExpenseType)
}