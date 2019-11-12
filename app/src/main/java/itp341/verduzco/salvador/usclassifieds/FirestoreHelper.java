package itp341.verduzco.salvador.usclassifieds;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FirestoreHelper {
    public static final String TAG = FirestoreHelper.class.getSimpleName();
    private FirebaseFirestore firebaseFirestore;

    public FirestoreHelper() {
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void doLogin(String userId, User user) {
        // Do this to ignore certain fields
        HashMap<String, String> user_map = new HashMap<>();
        user_map.put("name", user.getName());
        user_map.put("location", user.getLocation());
        user_map.put("description", user.getDescription());
        user_map.put("email", user.getEmail());
        user_map.put("phone", user.getPhone());

        this.firebaseFirestore.collection("Users")
                .document(userId)
                .set(user_map, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User login success for " + userId);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "User login failed for " + userId);
                    }
                });
    }

    public Query getItemsRef() {
        return this.firebaseFirestore.collection("Items");
    }

    public DocumentReference getItemByItemIdRef(String itemId) {
        return this.firebaseFirestore.collection("Items").document(itemId);
    }

    public DocumentReference getUserByUserIdRef(String userId) {
        return this.firebaseFirestore.collection("Users").document(userId);
    }

    public Query getUserByEmailRef(String email) {
        return this.firebaseFirestore.collection("Users")
                .whereEqualTo("email", email);
    }

    public Query getItemsBySearchRef(String search) {
        List<String> keywords = Arrays.asList(search.toLowerCase().split(" "));
        return this.getItemsRef()
                .whereArrayContains(
                    "searchable_keywords",
                    keywords
                );
    }

    public Query getSellingItemsByUserIdRef(String userId) {
        return this.getItemsRef()
                .whereEqualTo("userId", userId)
                .whereEqualTo("is_available", true);
    }

    public Query getSoldItemsByUserIdRef(String userId) {
        return this.getItemsRef()
                .whereEqualTo("userId", userId)
                .whereEqualTo("is_available", false);
    }

    public Query getItemByCategoryRef(String category) {
        return this.getItemsRef().whereEqualTo("category", category);
    }

    public void addItem(Item item, FirestoreHelperListener listener) {
        this.firebaseFirestore.collection("Items")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Item created ID: " + documentReference.getId());
                        listener.success();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Item create failed.");
                        listener.failure();
                    }
                });
    }

    public interface FirestoreHelperListener {
        void success();
        void failure();
    }

}
