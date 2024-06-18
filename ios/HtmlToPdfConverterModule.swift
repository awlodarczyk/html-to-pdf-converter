import ExpoModulesCore

public class HtmlToPdfConverterModule: Module {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  public func definition() -> ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('HtmlToPdfConverter')` in JavaScript.
    Name("HtmlToPdfConverter")


    // Defines event names that the module can send to JavaScript.
    Events("onPdfCreated","onPdfFailed")



    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("convert") { (value: String, promise: Promise) in
      // Send an event to JavaScript.
      self.sendEvent("onPdfCreated", [
        "value": "null"
      ])
      promise.resolve("null")
    }

  }
}
