import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to HtmlToPdfConverter.web.ts
// and on native platforms to HtmlToPdfConverter.ts
import HtmlToPdfConverterModule from './HtmlToPdfConverterModule';
import HtmlToPdfConverterView from './HtmlToPdfConverterView';
import { ChangeEventPayload, HtmlToPdfConverterViewProps } from './HtmlToPdfConverter.types';

// Get the native constant value.
export const PI = HtmlToPdfConverterModule.PI;

export function hello(): string {
  return HtmlToPdfConverterModule.hello();
}

export async function setValueAsync(value: string) {
  return await HtmlToPdfConverterModule.setValueAsync(value);
}

const emitter = new EventEmitter(HtmlToPdfConverterModule ?? NativeModulesProxy.HtmlToPdfConverter);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { HtmlToPdfConverterView, HtmlToPdfConverterViewProps, ChangeEventPayload };
