import { StyleSheet, Text, View } from 'react-native';

import * as HtmlToPdfConverter from 'html-to-pdf-converter';
import { useEffect } from "react";

export default function App() {

    const html = `
        <html>
        <head>
            <title>PDF</title>
        </head>
        <body>
            <h1>PDF</h1>
            <p>PDF</p>
        </body>
        </html>
    `;
    useEffect(() => {
        HtmlToPdfConverter.convert(html).then((path)=>{
            console.log(path);

        }).catch((error)=>{
          console.log(error);
        });
    }, []);
  return (
    <View style={styles.container}>
      <Text>Text</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
