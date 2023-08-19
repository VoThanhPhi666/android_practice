package com.mvc.phi_practice.view_model

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID


class PhotoViewModel : ViewModel() {
  var uncompressedUri: Uri? by mutableStateOf(null)
    private set
  var compressedBitMap: Bitmap? by mutableStateOf(null)
    private set
  var workId: UUID? by mutableStateOf(null)
    private set

  fun updateUncompressUri(uri:Uri?){
      uncompressedUri = uri
  }

  fun updatecompressedBitmap(bmp:Bitmap?){
      compressedBitMap = bmp
    }
  fun updateWorkId(uuid:UUID?){
      workId = uuid
    }
}