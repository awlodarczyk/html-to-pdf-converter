package io.hindbrain.pdf.converter

import expo.modules.kotlin.exception.CodedException

internal class HtmlToPdfException :
  CodedException("Activity which was provided during module initialization is no longer available")

internal class HtmlToPdfTaskException :
  CodedException("Android PDF conversion  task failed")

internal class HtmlToPdfUnsuccessfulTaskException :
  CodedException("Android PDF conversion task was not successful")
