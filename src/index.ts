import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to HtmlToPdfConverter.web.ts
// and on native platforms to HtmlToPdfConverter.ts
import HtmlToPdfConverterModule from './HtmlToPdfConverterModule';
import { ChangeEventPayload } from './HtmlToPdfConverter.types';



export async function convert(value: string) {
  return await HtmlToPdfConverterModule.convert(value);
}

const emitter = new EventEmitter(HtmlToPdfConverterModule ?? NativeModulesProxy.HtmlToPdfConverter);

export function addOnPdfCreatedListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onPdfCreated', listener);
}
export function addOnPdfFailedListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onPdfFailed', listener);
}

export { ChangeEventPayload };
