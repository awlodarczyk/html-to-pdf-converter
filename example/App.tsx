import { StyleSheet, Text, View } from 'react-native';

import * as HtmlToPdfConverter from 'html-to-pdf-converter';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{HtmlToPdfConverter.hello()}</Text>
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
