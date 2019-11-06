package itp341.verduzco.salvador.usclassifieds;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {
    private FirebaseFirestore firebaseFirestore;

    public FirestoreHelper() {
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public CollectionReference getItemsRef() {
        return this.firebaseFirestore.collection("Items");
    }
}
