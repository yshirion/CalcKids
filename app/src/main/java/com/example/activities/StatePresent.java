package com.example.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calackids.CalcKidsApplication;
import com.example.calackids.ListCard;
import com.example.calackids.ListCardAdapter;
import com.example.calackids.R;import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public abstract class StatePresent extends AppCompatActivity {

    protected CalcKidsApplication app;
//    SwipeRefreshLayout
    protected RecyclerView mRecyclerView;                 // RecyclerView and his tools:
    protected ListCardAdapter mAdapter;              // Adapter, manager, and list of objects
    protected RecyclerView.LayoutManager mLayoutManager;  // behind the cardviews(the parts of RecyclerView).
    protected ArrayList<ListCard> actionCardList;         //
    protected TextView total, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        total = (TextView) findViewById(R.id.total);
        title = (TextView) findViewById(R.id.title_list_activity);
        initializeRecycler();
    }


    //Initialize the part of RecyclerView, and load the list of message from the server.
    private void initializeRecycler() {

        actionCardList = new ArrayList<ListCard>();
        mRecyclerView = findViewById(R.id.action_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(StatePresent.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListCardAdapter(actionCardList);

    }

    abstract void loadList();

    protected void swipeCare() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(getSwipe());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private ItemTouchHelper.Callback getSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (position > 0)
                    remove(position);
            }
        };
        return simpleCallback;
    }

    protected void fixTotal(double toAdd, int sign){
        String cleanTotal = total.getText().toString().replace("$","").trim();
        double oldTotal = Double.parseDouble(cleanTotal);
        double newTotal = oldTotal + (toAdd * sign);
        total.setText( String.format("%.3f",newTotal) + " $");
    }
    abstract void remove(int position);

    //Set onClick to none, because we the card in those cases shouldn't be a button.
    public void onClick(View v){
    }
}