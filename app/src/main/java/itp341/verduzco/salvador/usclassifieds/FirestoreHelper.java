package itp341.verduzco.salvador.usclassifieds;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirestoreHelper {
    private FirebaseFirestore firebaseFirestore;

    public FirestoreHelper() {
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public Query getItemsRef() {
        return this.firebaseFirestore.collection("Items");
    }

    public DocumentReference getUserByUserIdRef(String userId) {
        return this.firebaseFirestore.collection("Users").document(userId);
    }

    public Query getUserByEmailRef(String email) {
        return this.firebaseFirestore.collection("Users")
                .whereEqualTo("email", email);
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

}
