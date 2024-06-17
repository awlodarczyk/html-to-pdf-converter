import * as React from 'react';

import { HtmlToPdfConverterViewProps } from './HtmlToPdfConverter.types';

export default function HtmlToPdfConverterView(props: HtmlToPdfConverterViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
