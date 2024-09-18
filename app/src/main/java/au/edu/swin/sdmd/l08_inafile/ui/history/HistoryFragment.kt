package au.edu.swin.sdmd.l08_inafile.ui.history

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.sdmd.l08_inafile.data.HistoryFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /**
         * The detailed retrieval of data from the file has moved to another
         * class; this gets a list of the strings in the file and provides them
         * to the list adapter. This is just using the old style list widget for
         * simplicity.
         */
        context?.let { context ->
            val numbers = HistoryFile.getFileContents(context)
            binding.historyList.adapter = ArrayAdapter<Any?>(
                context,
                R.layout.simple_list_item_1, numbers
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}