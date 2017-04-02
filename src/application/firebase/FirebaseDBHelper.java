package application.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseDBHelper {

	public void init() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("google-services.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setServiceAccount(serviceAccount)
		  .setDatabaseUrl("https://copypaste-f6869.firebaseio.com/")
		  .build();

		FirebaseApp.initializeApp(options);
	}
}
