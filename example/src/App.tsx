import { getAdvertisingId } from 'react-native-advertising-id';
import { Text, View, StyleSheet, Button, Alert } from 'react-native';
import { useState } from 'react';

export default function App() {
  const [advertisingId, setAdvertisingId] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(false);

  const fetchAdvertisingId = async () => {
    setLoading(true);
    try {
      const adId = await getAdvertisingId();
      setAdvertisingId(adId);

      if (adId) {
        Alert.alert('Success', `Advertising ID: ${adId}`);
      } else {
        Alert.alert(
          'Info',
          'Advertising ID not available (user denied permission or not supported)'
        );
      }
    } catch (error) {
      console.error('Error getting advertising ID:', error);
      Alert.alert('Error', 'Failed to get advertising ID');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Button
        title={loading ? 'Getting ID...' : 'Get Advertising ID'}
        onPress={fetchAdvertisingId}
        disabled={loading}
      />

      {advertisingId ? (
        <Text style={styles.result}>Advertising ID: {advertisingId}</Text>
      ) : null}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  result: {
    marginTop: 20,
    textAlign: 'center',
    fontSize: 12,
    fontFamily: 'monospace',
  },
});
