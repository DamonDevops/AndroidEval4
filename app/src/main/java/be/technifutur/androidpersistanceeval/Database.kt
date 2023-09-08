package be.technifutur.androidpersistanceeval

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import be.technifutur.androidpersistanceeval.expenses.Expense
import be.technifutur.androidpersistanceeval.expenses.ExpenseDao
import be.technifutur.androidpersistanceeval.expenses.ExpenseType

@Database(entities = [Expense::class, ExpenseType::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun expenseDao() :ExpenseDao
}