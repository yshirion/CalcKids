package com.example.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.CardAdapter;
import com.example.calackids.MenuCard;
import com.example.calackids.R;
import com.example.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ParentMenu extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    CalcKidsApplication app;
    ArrayList<User> childUsers;
    ArrayList<MenuCard> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        title = findViewById(R.id.userTitle);
        title.setText(setUserTitle());

        initializeMenu();
    }

    private void initializeMenu() {
        cardsList = new ArrayList<MenuCard>();

        //Add default cards in parent's menu
        cardsList.add(new MenuCard(getString(R.string.message), R.drawable.message, Messages.class));
        cardsList.add(new MenuCard(getString(R.string.settings), R.drawable.control, SettingsActivity.class));
        cardsList.add(new MenuCard(getString(R.string.send), R.drawable.send_message, CreateMessage.class));

        getChildren();
    }

    //Get from server the list of children to show in the father menu.
    private void getChildren() {
        app = (CalcKidsApplication) getApplication();
        //Get all children base on the family id.
        app.userService.findByFamily(app.currentParentUser.getFamily_id())
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        childUsers = (ArrayList<User>) response.body();
                        if (!childUsers.isEmpty()) {
                            String name;
                            for (User child : childUsers) {
                                name = child.getFirstName();
                                cardsList.add(0, new MenuCard(name, R.drawable.portrait, ChildMenu.class));
                            }
                            Collections.reverse(childUsers);
                        }
                        //Load cardViews and put on activity.
                        mRecyclerView = findViewById(R.id.idGVcard);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(ParentMenu.this);
                        mAdapter = new CardAdapter(cardsList);
                        //
                        mRecyclerView.setLayoutManager(new GridLayoutManager(ParentMenu.this, 2));
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(
                                getApplicationContext(),
                                getString(R.string.networkerror),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    //Unique result for press on each card.
    public void onClick(View view){
        app = (CalcKidsApplication) getApplication();
        CardView c = (CardView) view;
        MenuCard mc = (MenuCard) c.getTag();
        //Define child corresponding the chosen card.
        if (mc.getImage_id() == R.drawable.portrait) {
            int location = cardsList.indexOf(mc);
            app.currentChildUser = childUsers.get(location);
        }

        Intent intent = new Intent(ParentMenu.this, mc.getActivity());
        intent.putExtra("childrenList", childUsers);
        startActivity(intent);
    }

    //Set the title of app to show the name, username etc.
    public String setUserTitle(){
        app = (CalcKidsApplication) getApplication();
        String parent = getString(R.string.hello,
                app.currentParentUser.getFirstName(),
                app.currentParentUser.getUser_name(),
                app.currentParentUser.getId(),
                app.currentParentUser.getFamily_id());
        return parent;
    }

    @Override
    //Redefine the 'back pressed' to reset the user of app.
    public void onBackPressed(){
        app = (CalcKidsApplication) getApplication();
        app.currentParentUser = null;
        finish();
    }

}