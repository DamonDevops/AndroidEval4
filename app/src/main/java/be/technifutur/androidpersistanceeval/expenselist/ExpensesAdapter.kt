package be.technifutur.androidpersistanceeval.expenselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.androidpersistanceeval.databinding.ExpenseCellBinding
import be.technifutur.androidpersistanceeval.expenses.ExpenseWithType

class ExpensesAdapter(private val expensesList :MutableList<ExpenseWithType>) : RecyclerView.Adapter<ExpensesHolder>(){
    private lateinit var binding : ExpenseCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesHolder {
        binding = ExpenseCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpensesHolder(binding)
    }

    override fun getItemCount(): Int {
        return expensesList.size
    }

    override fun onBindViewHolder(holder: ExpensesHolder, position: Int) {
        holder.bind(expensesList[position])
    }
}