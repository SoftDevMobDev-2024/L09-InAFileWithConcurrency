package au.edu.swin.sdmd.l08_inafile.ui.converter

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresExtension
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import au.edu.swin.sdmd.l08_inafile.data.HistoryFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentConverterBinding
import au.edu.swin.sdmd.l08_inafile.model.NumberFact

class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val viewModel: NumberViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factObserver = Observer<NumberFact?> { state ->
            binding.tvFact.text = state?.text
        }
        viewModel.fact.observe(viewLifecycleOwner, factObserver)

        binding.bConvert.setOnClickListener {
            val sDecimal = binding.etDecimal.text.toString()
            if (sDecimal != "") {
                val iDecimal = sDecimal.toInt()
                val sBinary = Integer.toBinaryString(iDecimal)
                binding.tvBinary.text = sBinary

                /**
                 * This is where we update our files.
                 */
                updateHistory(sDecimal, sBinary)

                /**
                 * This is where the fact is retrieved.
                 */
                viewModel.getNumberFact(iDecimal)
            } else {
                binding.tvBinary.text = "No number entered"
            }
        }

        return root
    }

    private fun updateHistory(sDecimal: String, sBinary: String) {
        context?.let {
            HistoryFile.appendInput(it, "$sDecimal = $sBinary")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}