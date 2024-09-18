package au.edu.swin.sdmd.l08_inafile.ui.process

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l08_inafile.R
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentProcessBinding

class ProcessFragment : Fragment() {

    private var _binding: FragmentProcessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProcessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val process = resources.openRawResource(R.raw.binary_process)
            .bufferedReader().readText()
        binding.textProcess.text = HtmlCompat.fromHtml(process, HtmlCompat.FROM_HTML_MODE_COMPACT)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}