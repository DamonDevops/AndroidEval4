package be.technifutur.androidpersistanceeval.addexpense

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import be.technifutur.androidpersistanceeval.MainActivity
import be.technifutur.androidpersistanceeval.R
import be.technifutur.androidpersistanceeval.databinding.FragmentAddExpenseBinding
import be.technifutur.androidpersistanceeval.expenses.Expense
import be.technifutur.androidpersistanceeval.expenses.ExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddExpenseFragment : Fragment() {
    private lateinit var binding :FragmentAddExpenseBinding
    private lateinit var expense :Expense
    private var typeId = 0
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
        var types :List<ExpenseType>
        binding.typeField.inputType = InputType.TYPE_NULL
        binding.typeField.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                types = mainActivity.db.expenseDao().getAllType()
                CoroutineScope(Dispatchers.Main).launch {
                    buildAlertType(types)
                }
            }
        }

        binding.saveButton.setOnClickListener {

            if(!binding.nameField.text.isNullOrBlank() && !binding.ammountField.text.isNullOrBlank() && !binding.dateField.text.isNullOrBlank() && !binding.typeField.text.isNullOrBlank()){
                expense = Expense(name = binding.nameField.text.toString(),
                    value = binding.ammountField.text.toString().toFloat(),
                    date = LocalDate.parse(binding.dateField.text.toString()),
                    typeId = typeId.toLong())
                CoroutineScope(Dispatchers.IO).launch {
                    mainActivity.db.expenseDao().addExpense(expense)
                }
            }else{
                Toast.makeText(
                    requireContext(),
                    "Données invalides, retour à la liste",
                    Toast.LENGTH_LONG
                ).show()
            }
            findNavController().popBackStack(R.id.expenseListFragment, false)
        }
    }

    private fun buildAlertType(types :List<ExpenseType>){
        val charSequence = types.map {it.name}
        val checkedItem  = 0
        AlertDialog.Builder(requireContext())
            .setTitle("Choose type of expense:")
            .setSingleChoiceItems(charSequence.toTypedArray(), checkedItem){ dialog, which ->
                binding.typeField.setText(types[which].name)
                typeId = which
                dialog.dismiss()
            }.create().show()
    }
}