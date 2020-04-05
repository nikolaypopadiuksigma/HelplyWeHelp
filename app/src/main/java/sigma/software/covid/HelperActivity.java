package sigma.software.covid;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HelperActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Context cth = this;
        db.collection("orders")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<TaskHelper> result = new ArrayList<TaskHelper>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("TAGtest", document.getId() + " => " + document.getData());

                                Geocoder geocoder;
                                List<Address> addresses;
                                geocoder = new Geocoder(cth, Locale.US);
                                String address = null;
                                try {
                                    addresses = geocoder.getFromLocation(Double.valueOf(document.getData().get("Latitude").toString()), Double.valueOf(document.getData().get("Longitude").toString()), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                result.add(new TaskHelper(Boolean.valueOf(document.getData().get("isClose").toString()),
                                        document.getData().get("phone").toString(),
                                        address,
                                        document.getData().get("message").toString(),
                                        document.getData().get("helperId").toString(),
                                        document.getData().get("taskId").toString()));


                            }

                            TaskHelperAdapter adapter = new TaskHelperAdapter(result, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.w("TAGtest", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
