package au.edu.swin.sdmd.l08_inafile.ui.longtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import au.edu.swin.sdmd.l08_inafile.ButtonViewModel
import au.edu.swin.sdmd.l08_inafile.R
import au.edu.swin.sdmd.l08_inafile.data.LoooooooongFile
import au.edu.swin.sdmd.l08_inafile.databinding.FragmentLongtaskBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter




class LongTaskFragment : Fragment() {

    private var _binding: FragmentLongtaskBinding? = null
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

        val buttonObserver = Observer<Boolean> { state ->
            binding.bLong.isEnabled = state
        }
        viewModel.buttonState.observe(viewLifecycleOwner, buttonObserver)

        binding.bLong.setOnClickListener {
            binding.bLong.isEnabled = false
            //viewModel.buttonState.value = false
            val listLength = when(binding.radioGroup.checkedRadioButtonId) {
                R.id.shortList -> 1000
                R.id.mediumList -> 10000
                R.id.longList -> 100000
                else -> 1
            }
            viewLifecycleOwner.lifecycleScope.launch {
                writeFile(context, listLength)
                binding.bLong.isEnabled = true
                //viewModel.buttonState.postValue(true)
            }
        }

        binding.bCache.setOnClickListener {
            binding.bCache.isEnabled = false
            val listLength = 100
            viewLifecycleOwner.lifecycleScope.launch {
                writeCache(context, listLength)
                binding.bCache.isEnabled = true
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun writeFile(context: Context?, listLength: Int) {
        withContext(Dispatchers.IO) {
            context?.let {
                LoooooooongFile.deleteFile(it)
                for (i in 1..listLength) {
                    val sBinary = Integer.toBinaryString(i)
                    LoooooooongFile.appendInput(it, "$i = $sBinary")
                }
                LoooooooongFile.closeFile(it)
            }
        }
    }

    private suspend fun writeCache(context: Context?, listLength: Int) {
        withContext(Dispatchers.IO) {
            context?.let {
                //File.createTempFile("temp_file", null, context.cacheDir)
                val cacheFile = File(context.cacheDir, "temp_file")
                val bw = BufferedWriter(FileWriter(cacheFile.absoluteFile))
                    for (i in 1..listLength) {
                    val sBinary = Integer.toBinaryString(i)
                    bw.write("$i = $sBinary\n")
                }
                bw.close()
            }
        }
    }
}