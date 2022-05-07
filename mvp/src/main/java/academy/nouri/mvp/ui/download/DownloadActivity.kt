package academy.nouri.mvp.ui.download

import academy.nouri.mvp.R
import academy.nouri.mvp.databinding.ActivityDownloadBinding
import academy.nouri.mvp.utils.Utility
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class DownloadActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityDownloadBinding

    //Other
    private val filepath = "https://www.dl.farsroid.com/game/Horror-Brawl-1.2.0(www.Farsroid.com).apk"
    private val fileName = "Horror-Brawl-1.2.0.apk"
    private var dirPath = ""
    private var downloadId = 0
    private var isPause = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //path
            dirPath = Utility.getRootDirPath(this@DownloadActivity)
            //Download
            startBtn.setOnClickListener {
                //Permission
                Dexter.withContext(this@DownloadActivity)
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                            startDownload()
                        }

                        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}

                        override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {

                        }
                    }).check()
            }
            //Cancel
            downloaderCancel.setOnClickListener { PRDownloader.cancel(downloadId) }
            //Pause / Resume
            downloaderPause.setOnClickListener {
                if (isPause) {
                    PRDownloader.pause(downloadId)
                    isPause = false
                    downloaderPause.text = "Resume"
                } else {
                    PRDownloader.resume(downloadId)
                    isPause = true
                    downloaderPause.text = "Pause"
                }
            }
        }
    }

    private fun startDownload() {
        binding.apply {
            downloadId = PRDownloader.download(filepath, dirPath, fileName)
                .build()
                .setOnStartOrResumeListener {
                    startBtn.visibility = View.GONE
                    downloadInfoLay.visibility = View.VISIBLE
                }
                .setOnPauseListener { }
                .setOnCancelListener {
                    downloadId = 0
                    startBtn.visibility = View.VISIBLE
                    downloadInfoLay.visibility = View.GONE
                }
                .setOnProgressListener {
                    val percent = it.currentBytes * 100 / it.totalBytes
                    downloaderProgress.progress = percent.toInt()
                    downloaderSize.text = Utility.getProgressDisplay(it.currentBytes, it.totalBytes)
                }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        Toast.makeText(this@DownloadActivity, "Completed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: Error?) {
                        Toast.makeText(this@DownloadActivity, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}