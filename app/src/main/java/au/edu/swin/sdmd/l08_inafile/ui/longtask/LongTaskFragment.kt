package au.edu.swin.sdmd.l08_inafile.ui.longtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l08_inafile.R
import au.edu.swin.sdmd.l08_inafile.data.LoooooooongFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentLongtaskBinding


class LongTaskFragment : Fragment() {

    private var _binding: FragmentLongtaskBinding? = null

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

        binding.bLong.setOnClickListener {
            val listLength = when(binding.radioGroup.checkedRadioButtonId) {
                R.id.shortList -> 1000
                R.id.mediumList -> 10000
                R.id.longList -> 100000
                else -> 1
            }
            context?.let {
                LoooooooongFile.deleteFile(it)
                for (i in 1..listLength) {
                    val sBinary = Integer.toBinaryString(i)
                    LoooooooongFile.appendInput(it, "$i = $sBinary")
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}