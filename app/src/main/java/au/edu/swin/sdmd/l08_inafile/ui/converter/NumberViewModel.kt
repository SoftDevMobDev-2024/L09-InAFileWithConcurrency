package au.edu.swin.sdmd.l08_inafile.ui.converter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.sdmd.l08_inafile.model.NumberFact
import au.edu.swin.sdmd.l08_inafile.network.NumberApi
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class NumberViewModel : ViewModel() {
    val fact = MutableLiveData<NumberFact?>().apply {
        value = null
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getNumberFact(n: Int) {
        viewModelScope.launch {
            try {
                //val call = NumberApi.retrofitService.getFacts()
                val call = NumberApi.retrofitService.getFact(n)
                Log.i("GET", call.toString())
                fact.postValue(call)
            } catch (exception: Exception) {
                Log.i("NOPE", exception.toString())
            }
        }
    }

}