package com.mvc.phi_practice
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.mvc.phi_practice.foreground_service.RunningService
import com.mvc.phi_practice.ui.theme.Phi_practiceTheme
import com.mvc.phi_practice.view_model.PhotoViewModel
import com.mvc.phi_practice.work_manager.PhotoCompressionWorker



class MainActivity : ComponentActivity() {
    private lateinit var workManager : WorkManager
    private val viewModel by viewModels<PhotoViewModel> ()
   override fun onNewIntent(
    intent:Intent?
    ){
       super.onNewIntent(intent)
       val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
           intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
       }else{
           intent?.getParcelableExtra(Intent.EXTRA_STREAM)
       }?: return
       val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>().setInputData(
           workDataOf(
           PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
           PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L

       )).setConstraints(Constraints.Builder()
           .setRequiresStorageNotLow(true)
           .build())
           .build()
       workManager.enqueue(request)
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workManager = WorkManager.getInstance(applicationContext)
        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)

        setContent{
            val workResult = viewModel.workId?.let {

            }
            Phi_practiceTheme{

                Column (modifier=Modifier.fillMaxSize(),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){
                    Button(onClick={
                        Intent(applicationContext, RunningService::class.java).also{

                                it.action = RunningService.ACTIONS.START.toString()
                                startService(it)
                            }

                    } ){
                        Text("Start")
                    }
                    Button(onClick={
                        Intent(applicationContext, RunningService::class.java).also{
                            it.action = RunningService.ACTIONS.STOP.toString()
                            startService(it)
                        }

                    } ){
                        Text("Stop")
                    }

                }

            }


        }

    }
}





