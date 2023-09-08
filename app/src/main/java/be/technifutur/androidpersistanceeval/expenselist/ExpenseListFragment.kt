package be.technifutur.androidpersistanceeval.expenselist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.androidpersistanceeval.MainActivity
import be.technifutur.androidpersistanceeval.R
import be.technifutur.androidpersistanceeval.addexpense.AddExpenseFragment
import be.technifutur.androidpersistanceeval.databinding.FragmentExpenseListBinding
import be.technifutur.androidpersistanceeval.expenses.ExpenseWithType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseListFragment : Fragment() {
    private lateinit var binding : FragmentExpenseListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = (activity as MainActivity)
        lifecycle.coroutineScope.launch {
            mainActivity.db.expenseDao().getExpensesWithType().collect(){
                Log.d("print", " from lifecycle: $it")
                setupRecyclerView(it.toMutableList())
            }
        }
        binding.toAddButton.setOnClickListener {
            findNavController().navigate(R.id.addExpenseFragment)
        }
    }

    private fun setupRecyclerView(list :MutableList<ExpenseWithType>){
        Log.d("print", "I do my thing")
        val expenseRecycler = binding.expensesRecyclerView

        expenseRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        expenseRecycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        expenseRecycler.adapter = ExpensesAdapter(list)
    }
}