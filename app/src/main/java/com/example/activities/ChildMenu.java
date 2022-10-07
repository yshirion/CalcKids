package com.example.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

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
public class ChildMenu extends AppCompatActivity {
    private RecyclerView mRecyclerView;                 //All of those- the recyclerview objects, to show the list:
    private RecyclerView.Adapter mAdapter;              //Adapter- to load the list on RecyclerView.
    private RecyclerView.LayoutManager mLayoutManager;  //Manager to manage the  RecyclerView how to work.
    private ArrayList<MenuCard> cardsList;              //The list of objects behind the CardViews in RecyclerView.
    private TextView title;
    CalcKidsApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (CalcKidsApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        title = findViewById(R.id.userTitle);
        title.setText(setUserTitle());

        initializeMenu();
    }

    //Initialize the RecyclerView, and set the parts of menu in the list.
    private void initializeMenu() {
        cardsList = new ArrayList<MenuCard>();

        //Add all cards with their title, icon, and destination activity.
        cardsList.add(new MenuCard(getString(R.string.mission), R.drawable.mission, BlankforWhile.class));//Still not working until i will care of that.
        cardsList.add(new MenuCard(getString(R.string.request), R.drawable.request, CreateAction.class));
        cardsList.add(new MenuCard(getString(R.string.balance), R.drawable.balance, ActionPresent.class));
        cardsList.add(new MenuCard(getString(R.string.invest), R.drawable.invest,InvestPresent.class));
        cardsList.add(new MenuCard(getString(R.string.loan), R.drawable.loan,LoanPresent.class));
        cardsList.add(new MenuCard(getString(R.string.message), R.drawable.message, Messages.class));
        cardsList.add(new MenuCard(getString(R.string.send), R.drawable.send_message, CreateMessage.class));

        //Change this activity for view from parent.
        if (app.currentParentUser != null){
            cardsList.remove(cardsList.size()-1);
            cardsList.remove(cardsList.size()-1);
        }

        //Load CardViews and put on activity.
        mRecyclerView = findViewById(R.id.idGVcard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//Set the RecyclerView as grid.
        mAdapter = new CardAdapter(cardsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Unique result for press on each card.
    public void onClick(View view) {
        CardView c = (CardView) view;
        MenuCard mc = (MenuCard) c.getTag();//The object behind CardView, that save in his 'tag'.
        if (mc.getActivity() == CreateMessage.class)
            defineParent(mc);//Child can send the message only for his parents.
        else //Start activity that related to this card.
        {
            Intent intent = new Intent(this, mc.getActivity());
            intent.putExtra("whichActivity", mc.getCard_text());//For title.
            startActivity(intent);
        }
    }

    //Set the tool bar with name, username, id and family id. if the parent in his child page- his username too.
    public String setUserTitle(){
        app = (CalcKidsApplication) getApplication();
        String child = getString(R.string.hello,
                                app.currentChildUser.getFirstName(),
                                app.currentChildUser.getUser_name(),
                                app.currentChildUser.getId(),
                                app.currentChildUser.getFamily_id());
        String parent = "";
        if (app.currentParentUser != null)
            parent = getString(R.string.parent, app.currentParentUser.getUser_name());

        return child + parent + ".";
    }

    private void defineParent(MenuCard mc) {
        app.userService
                .getParent(app.currentChildUser.getFamily_id())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        //If the server find the parent load him in array list
                        // to move to message activity fo the destination of message.
                        if (response.isSuccessful()) {
                            ArrayList<User> array = new ArrayList<User>();
                            array.add(response.body());
                            Intent intent = new Intent(ChildMenu.this, mc.getActivity());
                            intent.putExtra("childrenList", array);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    getString(R.string.cantFind),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(
                                getApplicationContext(),
                                getString(R.string.networkerror),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    //Redefine the 'back' button, to reset the app and cancel the user in this entry.
    public void onBackPressed(){
        app = (CalcKidsApplication) getApplication();
        app.currentChildUser = null;
        finish();
    }
}