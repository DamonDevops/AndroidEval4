package be.technifutur.androidpersistanceeval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import be.technifutur.androidpersistanceeval.databinding.ActivityMainBinding
import be.technifutur.androidpersistanceeval.expenses.Expense
import be.technifutur.androidpersistanceeval.expenses.ExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var db : AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, AppDataBase::class.java, "expenses.db").build()

        CoroutineScope(Dispatchers.IO).launch{
            populateDb()
        }
    }

    private suspend fun populateDb(){
        withContext(Dispatchers.IO){
            val expDao = db.expenseDao()
            if(expDao.getAllType().isEmpty()){
                expDao.addType(ExpenseType(name = "Tax"))
                expDao.addType(ExpenseType(name = "Necessities"))
                expDao.addType(ExpenseType(name = "Fraud"))
            }
            if(expDao.getAllExpenses().isEmpty()){
                expDao.addExpense(Expense(name = "Getting a big score for this Evaluation", date = LocalDate.parse("2023-09-08"), value = 26000.5f, typeId = 3))
                expDao.addExpense(Expense(name = "Orval 8° for personnal recreation", date = LocalDate.parse("2023-09-08"), value = 16.8f, typeId = 2))
                expDao.addExpense(Expense(name = "Contributions", date = LocalDate.parse("2023-09-08"), value = 0.2f, typeId = 1))
            }
        }
    }
}