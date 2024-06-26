package android.print

import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PrintDocumentAdapter.LayoutResultCallback
import android.print.PrintDocumentAdapter.WriteResultCallback
import java.io.File
import java.io.IOException

class PdfPrinter(private val printAttributes: PrintAttributes) {
    fun generate(
        printAdapter: PrintDocumentAdapter,
        path: File,
        onPdfGenerated: (File) -> Unit,
        onPdfFailed: (Exception) -> Unit
    ) {

        if (path.exists()) {
            path.delete()
        }
        try {
            printAdapter.onLayout(null, printAttributes, null, object : LayoutResultCallback() {
                override fun onLayoutFinished(info: PrintDocumentInfo, changed: Boolean) {
                    printAdapter.onWrite(
                        arrayOf(PageRange.ALL_PAGES),
                        getOutputFile(path),
                        CancellationSignal(),
                        object : WriteResultCallback() {
                            override fun onWriteFinished(pages: Array<PageRange>) {
                                super.onWriteFinished(pages)
                                onPdfGenerated(path)
                            }

                            override fun onWriteFailed(error: CharSequence?) {
                                super.onWriteFailed(error)
                                onPdfFailed(IOException(error.toString()))
                            }
                        })
                }
            }, null)
        } catch (e: Exception) {
            onPdfFailed(e)
        }
    }

    private fun getOutputFile(file: File): ParcelFileDescriptor? {
        val fileDirectory = file.parentFile
        if (fileDirectory != null && !fileDirectory.exists()) {
            fileDirectory.mkdirs()
        }
        try {
            file.createNewFile()
            return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_WRITE)
        } catch (e: IOException) {
            throw e
        }
    }

}