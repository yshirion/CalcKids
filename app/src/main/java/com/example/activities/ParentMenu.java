package com.example.activities;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calackids.CalcKidsApplication;
import com.example.calackids.CardAdapter;
import com.example.calackids.MenuCard;
import com.example.calackids.MyCardView;
import com.example.calackids.R;
import com.example.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ParentMenu extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button cancel, delete, toParent;
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
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

    //set long press for delete user or define user as parent
    public void onLongPress(View v){
        MyCardView mcv = (MyCardView) v;
        MenuCard mc = (MenuCard) mcv.getTag();
        User user;
        if (mc.getImage_id() == R.drawable.portrait) {
            int location = cardsList.indexOf(mc);
            user = childUsers.get(location);
            createDialogSetUser(user);
        }
    }

    //Set the title of app to show the name, username etc.
    public String setUserTitle(){
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
        app.currentParentUser = null;
        finish();
    }

    //Remove child from the list (not from DB) for delete user and define as parent.
    private void removeChild(User user) {
        int position;
        for (position =0; position< childUsers.size();position++){
            if (childUsers.get(position).getId() == user.getId())
                break;
        }
        cardsList.remove(position);
        mAdapter.remove(position);
        //"refresh" the page
        finish();
        Intent intent = new Intent(ParentMenu.this, ParentMenu.class);
        startActivity(intent);
    }

    //The dialog that open while long press on some child
    public void createDialogSetUser(User user){
        builder = new AlertDialog.Builder(this);
        final View setUser = getLayoutInflater().inflate(R.layout.set_child, null);
        cancel = (Button) setUser.findViewById(R.id.cancel);
        delete = (Button) setUser.findViewById(R.id.delete);
        toParent = (Button) setUser.findViewById(R.id.setParent);

        builder.setView(setUser);
        dialog = builder.create();
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.userService.deleteUser(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(
                                    getApplicationContext(),
                                    response.body(),
                                    Toast.LENGTH_LONG).show();
                            removeChild(user);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        toParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.userService.changeToParent(user.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(
                                    getApplicationContext(),
                                    response.body(),
                                    Toast.LENGTH_LONG).show();
                            removeChild(user);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }
}