package truong.poly.firebasebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    String TAG = "ZZZZZZZZZZ";
    List<Book> bookList = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
            listView = findViewById(R.id.lv);
        db  = FirebaseFirestore.getInstance();
        getListAsync();
     //   UpdateListView();
    }
    void AddBook(){
        Book book = new Book();
        book.name = "Truyện Kiều";
        book.author = "Nguyễn Du";
        book.price = 50000;
        db.collection("Books")
                .add( book )
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("zzzz", "onSuccess: Thêm sách thành công");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("zzzz", "onFailure: lỗi thêm sách");
                        e.printStackTrace();
                    }
                });

    }

    void getListAsync(){


        db.collection("Books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.d(TAG, "onComplete: " + document.toString());

                                Book book = document.toObject(Book.class);
                                Log.d(TAG, "onComplete: objbook" + book.name + "===" + book.price );

                                bookList.add(book);

                            }
                            // add xong thi update list view
                            UpdateListView();

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });


        // return listBook;

    }

    void UpdateListView(){
        Adapter adapter=new Adapter(bookList,MainActivity.this);
        listView.setAdapter(adapter);
        Log.d(TAG, "onCreate: listbook = " + bookList.size());
    }


}