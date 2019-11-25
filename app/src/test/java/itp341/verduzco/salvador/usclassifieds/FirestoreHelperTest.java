package itp341.verduzco.salvador.usclassifieds;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import androidx.annotation.NonNull;

import static org.junit.Assert.*;

public class FirestoreHelperTest {
    public static final String TAG = FirestoreHelper.class.getSimpleName();
    private FirebaseFirestore firebaseFirestore;
    public FirestoreHelper.FirestoreHelperListener listener;
    public FirestoreHelper firestoreHelper;

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
        listener = new FirestoreHelper.FirestoreHelperListener() {
            @Override
            public void success() {
                System.out.println("success");
            }

            @Override
            public void failure() {
                System.out.println("success");
            }
        };
        firestoreHelper = new FirestoreHelper();
    }

    @Test
    public void getItemByItemIDRefTest() {
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

        DocumentSnapshot document = firestoreHelper.getItemByItemIdRef(id).get().getResult();
        Item myItem = document.toObject(Item.class);

        assertEquals(myItem, item);
    }

    @Test
    public void addItemTest() {
        firestoreHelper.addItem(item, listener);

        DocumentSnapshot document = firestoreHelper.getItemByItemIdRef(id).get().getResult();
        Item myItem = document.toObject(Item.class);

        assertEquals(myItem, item);
    }

    @Test
    public void markItemAsSoldTest() {
        firestoreHelper.markItemAsSold(id, listener);

        DocumentSnapshot document = this.firebaseFirestore.collection("Items").document(id).get().getResult();
        Item myItem = document.toObject(Item.class);

        assertFalse(myItem.isIs_available());
    }

    @Test
    public void getUserByUserIDRef() {
        User user = new User("hubba", "hubba@hubba.com","Los Angeles", "1234567890");
        String userID = "3o827t84gufn3iyr";
        firestoreHelper.doLogin(userID, user);


        DocumentSnapshot document = firestoreHelper.getUserByUserIdRef(userID).get().getResult();
        User myUser = document.toObject(User.class);

        assertEquals(myUser, user);
    }
}
