package au.edu.swin.sdmd.l08_inafile.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l08_inafile.data.HistoryFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentConverterBinding

class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val root: View = binding.root

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