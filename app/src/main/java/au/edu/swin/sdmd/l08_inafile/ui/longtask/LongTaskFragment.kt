package au.edu.swin.sdmd.l08_inafile.ui.longtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import au.edu.swin.sdmd.l08_inafile.ButtonViewModel
import au.edu.swin.sdmd.l08_inafile.R
import au.edu.swin.sdmd.l08_inafile.data.KeyStore
import au.edu.swin.sdmd.l08_inafile.data.LoooooooongFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentLongtaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class LongTaskFragment : Fragment() {

    private var _binding: FragmentLongtaskBinding? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val scope2 = CoroutineScope(Dispatchers.Main + job)
    private val viewModel: ButtonViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentLongtaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val checkboxObserver = Observer<Int> { state ->
            binding.radioGroup.check(state)
        }
        viewModel.checkboxState.observe(viewLifecycleOwner, checkboxObserver)

        context?.let {
            val store = KeyStore(it)
            // if option saved, updated checkboxgroup
            val job = scope.launch {
                store.getOption.collect { option ->
                    viewModel.checkboxState.postValue(option)
                }
            }
            // if checkbox selected, save option id
            binding.radioGroup.setOnCheckedChangeListener { _, i ->
                val job = scope.launch {
                    store.saveOption(i)
                }
            }
        }

        val buttonObserver = Observer<Boolean> { state ->
            binding.bLong.isEnabled = state
        }
        viewModel.buttonState.observe(viewLifecycleOwner, buttonObserver)

        binding.bLong.setOnClickListener {
            //binding.bLong.isEnabled = false
            viewModel.buttonState.value = false
            val listLength = when(binding.radioGroup.checkedRadioButtonId) {
                R.id.shortList -> 1000
                R.id.mediumList -> 10000
                R.id.longList -> 100000
                else -> 1
            }
            scope.launch {
                writeFile(context, listLength)
                //binding.bLong.isEnabled = true
                viewModel.buttonState.postValue(true)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        scope.cancel()
    }

    private fun writeFile(context: Context?, listLength: Int) {
        context?.let {
            LoooooooongFile.deleteFile(it)
            for (i in 1..listLength) {
                val sBinary = Integer.toBinaryString(i)
                LoooooooongFile.appendInput(it, "$i = $sBinary")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}