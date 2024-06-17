import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { HtmlToPdfConverterViewProps } from './HtmlToPdfConverter.types';

const NativeView: React.ComponentType<HtmlToPdfConverterViewProps> =
  requireNativeViewManager('HtmlToPdfConverter');

export default function HtmlToPdfConverterView(props: HtmlToPdfConverterViewProps) {
  return <NativeView {...props} />;
}
