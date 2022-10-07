//package com.example.calackids;
//
//import android.app.Activity;
//import android.os.Build;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.objects.Action;
//import java.util.ArrayList;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Services {
//
//
//    public static ItemTouchHelper.SimpleCallback getSwiped(CalcKidsApplication app, ArrayList<Action> list, ListCardAdapter adapter) {
//
//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                callServer(app.actionService.deleteInvest(list.get(position)), list, adapter,position);
//            }
//        };
//        return simpleCallback;
//    }
//
//    private static void callServer(Call<String> deleteInvest, ArrayList<Action> list, ListCardAdapter adapter, int position) {
//        deleteInvest.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        response.body(),
//                        Toast.LENGTH_LONG).show();
//                list.remove(position);
//                adapter.notifyItemRemoved(position);
//                adapter.remove(position);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
//}
