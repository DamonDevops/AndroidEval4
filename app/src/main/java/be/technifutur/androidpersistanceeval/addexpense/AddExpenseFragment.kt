package be.technifutur.androidpersistanceeval.addexpense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import be.technifutur.androidpersistanceeval.MainActivity
import be.technifutur.androidpersistanceeval.databinding.FragmentAddExpenseBinding
import be.technifutur.androidpersistanceeval.expenses.Expense
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddExpenseFragment : Fragment() {
    private lateinit var binding :FragmentAddExpenseBinding
    lateinit var expense :Expense
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = (activity as MainActivity)

        binding.saveButton.setOnClickListener {

            if(!binding.nameField.text.isNullOrBlank() && !binding.ammountField.text.isNullOrBlank() && !binding.dateField.text.isNullOrBlank() && !binding.typeField.text.isNullOrBlank()){
                lifecycle.coroutineScope.launch {
                    val type = mainActivity.db.expenseDao().getTypeId(binding.typeField.text.toString())

                    expense = Expense(name = binding.nameField.text.toString(),
                        value = binding.ammountField.text.toString().toFloat(),
                        date = LocalDate.parse(binding.dateField.text.toString()),
                        typeId = type)
                }
            }
        }
    }
}