package be.technifutur.androidpersistanceeval.expenselist

import androidx.recyclerview.widget.RecyclerView
import be.technifutur.androidpersistanceeval.databinding.ExpenseCellBinding
import be.technifutur.androidpersistanceeval.expenses.ExpenseWithType
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ExpensesHolder(private val viewBinding : ExpenseCellBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(expense :ExpenseWithType){
        viewBinding.typeLabel.text = expense.type.name
        viewBinding.nameLabel.text = expense.expense.name
        viewBinding.valueLabel.text = expense.expense.value.toString()
        viewBinding.dateLabel.text = expense.expense.date?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }
}