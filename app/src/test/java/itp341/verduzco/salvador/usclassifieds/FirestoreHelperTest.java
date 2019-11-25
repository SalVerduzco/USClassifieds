package itp341.verduzco.salvador.usclassifieds;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

import java.util.Arrays;

import androidx.annotation.NonNull;

import static org.junit.Assert.*;

public class FirestoreHelperTest {
    public static final String TAG = FirestoreHelper.class.getSimpleName();
    private FirebaseFirestore firebaseFirestore;

    Item item;
    String description;
    String location;
    String title;
    String price;
    String category;
    String userID;
    String id;


    public FirestoreHelperTest() {
        description = "a brand new item still in the box";
        location = "Los Angeles, CA 90007";
        title = "iPhone 15";
        price = "1100.00";
        category = "tech";
        userID = "aAIFBEasdfiayaisdg1235";

        item = new Item(description, location, title, price, category, userID);
        id = null;

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Test
    public void getItemsRefTest() {
        this.firebaseFirestore.collection("Items")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Item created ID: " + documentReference.getId());
                        id = documentReference.getId();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Item create failed.");
                    }
                });
        DocumentSnapshot document = this.firebaseFirestore.collection("Items").document(id).get().getResult();
        Item myItem = document.toObject(Item.class);

        assertEquals(item, myItem);
    }
}
