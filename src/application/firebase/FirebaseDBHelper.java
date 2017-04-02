package application.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import application.bean.Item;

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
	
	public void readItems() {
		DatabaseReference ref = FirebaseDatabase
			    .getInstance()
			    .getReference()
			    .child("items");
			ref.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onCancelled(DatabaseError arg0) {
				}

				@Override
				public void onDataChange(DataSnapshot snap) {
					for(DataSnapshot snap1 : snap.getChildren()) {
						Item item = snap1.getValue(Item.class);
						System.out.println(item.getText());
					}
				}});
	}
}
