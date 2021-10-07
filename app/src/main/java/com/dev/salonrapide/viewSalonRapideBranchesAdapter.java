package com.dev.salonrapide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewSalonRapideBranchesAdapter extends FirebaseRecyclerAdapter<MainModel, viewSalonRapideBranchesAdapter.myViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options

     */
    //construtor
    public viewSalonRapideBranchesAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull MainModel model) {
        holder.salon_name.setText(model.getSalon_name());
        holder.address.setText(model.getAddress());
        holder.owner_name.setText(model.getOwner_name());
        holder.phone_no.setText(model.getPhone_no());
        holder.email.setText(model.getEmail());

        Glide.with(holder.img.getContext())
                .load(model.getS_url())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_normal)
                .centerCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_user, parent, false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView salon_name,address,owner_name,phone_no,email;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img =(CircleImageView)itemView.findViewById(R.id.img1);
            salon_name = (TextView)itemView.findViewById(R.id.salon_nametext);
            address= (TextView)itemView.findViewById(R.id.addresstext);
            owner_name = (TextView)itemView.findViewById(R.id.owner_nametext);
            phone_no = (TextView)itemView.findViewById(R.id.phone_notext);
            email = (TextView)itemView.findViewById(R.id.emailtext);

        }
    }
}
