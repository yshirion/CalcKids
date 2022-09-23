package com.example.calackids;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.RestServise.ActionService;
import com.example.objects.User;
import com.example.RestServise.FamilyService;
import com.example.RestServise.MessageService;
import com.example.RestServise.RetrofitService;
import com.example.RestServise.UserService;
import retrofit2.Retrofit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CalcKidsApplication extends Application {

    // Object of the networking responsibilities.
    private final Retrofit retrofit = new RetrofitService().getRetrofit();

    // All object for the communication with the server.
    public final UserService userService = retrofit.create(UserService.class);
    public final FamilyService familyService = retrofit.create(FamilyService.class);
    public final MessageService messageService = retrofit.create(MessageService.class);
    public final ActionService actionService = retrofit.create(ActionService.class);

    // Define the current user, each one for the type of current user.
    // If parent enter to his son page, both of them initialized.
    public User currentChildUser;
    public User currentParentUser;

}
