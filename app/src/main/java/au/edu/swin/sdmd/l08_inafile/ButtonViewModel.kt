package au.edu.swin.sdmd.l08_inafile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonViewModel: ViewModel() {
    val buttonState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}