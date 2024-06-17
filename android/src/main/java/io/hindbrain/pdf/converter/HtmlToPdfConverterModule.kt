package io.hindbrain.pdf.converter

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class HtmlToPdfConverterModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  val context: Context
    get() = appContext.reactContext ?: throw Exceptions.ReactContextLost()
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('HtmlToPdfConverter')` in JavaScript.
    Name("HtmlToPdfConverter")


    // Defines event names that the module can send to JavaScript.
    Events("onPdfCreated")
    Events("onPdfFailed")



    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("convert") { value: String ->
      // Send an event to JavaScript.
      //todo
      val converter = HtmlToPdfConvertor(context)
      convertHtmlToPdf(value, converter, { file ->
        sendEvent("onPdfCreated", mapOf(
          "path" to file.absolutePath
        ))
      },{ex->
        sendEvent("onPdfFailed", mapOf(
          "exception" to ex.message
        ))
      })

    }


  }
  private fun convertHtmlToPdf(htmlString:String, htmlToPdfConvertor: HtmlToPdfConvertor, openPdf: (File) -> Unit, failedPdf:(Exception)->Unit){
    // start loading


    // define output file location and html content
    val pdfLocation = File(getPdfFilePath(), "${System.currentTimeMillis()}.pdf")


    // start conversion
    htmlToPdfConvertor.convert(
      pdfLocation = pdfLocation,
      htmlString = htmlString,
      onPdfGenerationFailed = { exception ->
        // something went wrong, stop loading and log the exception

        exception.printStackTrace()
        failedPdf(exception)

      },
      onPdfGenerated = { pdfFile ->
        // pdf was generated, stop the loading and open it

        openPdf(pdfFile)

      })
  }

}
