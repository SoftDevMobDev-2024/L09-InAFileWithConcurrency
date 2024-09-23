package au.edu.swin.sdmd.l08_inafile.ui.longtask

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import au.edu.swin.sdmd.l08_inafile.data.LoooooooongFile

class LongFileWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Do the work here
        LoooooooongFile.deleteFile(applicationContext)
        for (i in 1..20_000) {
            val sBinary = Integer.toBinaryString(i)
            LoooooooongFile.appendInput(this.applicationContext, "$i = $sBinary")
        }
        Log.i("FINISHED", "done")
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}