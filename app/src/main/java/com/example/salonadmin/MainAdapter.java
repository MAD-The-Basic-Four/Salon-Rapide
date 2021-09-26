package com.example.salonadmin;

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

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options

     */
    //construtor
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {

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
        //
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1700)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText SalonName = view.findViewById(R.id.txtSalonName);
                EditText address = view.findViewById(R.id.txtAddress);
                EditText OwnerName = view.findViewById(R.id.txtOwnerName);
                EditText PhoneNo = view.findViewById(R.id.txtPhoneNo);
                EditText Email = view.findViewById(R.id.txtEmail);
                EditText s_url = view.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                SalonName.setText(model.getSalon_name());
                address.setText(model.getAddress());
                OwnerName.setText(model.getOwner_name());
                PhoneNo.setText(model.getPhone_no());
                Email.setText(model.getEmail());
                s_url.setText(model.getS_url());

                dialogPlus.show();

                //click operation on update
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map =new HashMap<>();
                        map.put("salon_name",SalonName.getText().toString());
                        map.put("owner_name", OwnerName.getText().toString());
                        map.put("address",address.getText().toString());
                        map.put("phone_no", PhoneNo.getText().toString());
                        map.put("email", Email.getText().toString());
                        map.put("s_url", s_url.getText().toString());

                        //Insatnce of firebase database needed

                        FirebaseDatabase.getInstance().getReference().child("Saloon")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.salon_name.getContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.salon_name.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.salon_name.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Data will be permanently deleted");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Saloon")//accesing database
                                .child(getRef(position).getKey()).removeValue();//using the Saloon key number as value the records will be deleted.(s1)
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.salon_name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView salon_name,address,owner_name,phone_no,email;

        Button btnEdit, btnDelete;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img =(CircleImageView)itemView.findViewById(R.id.img1);
            salon_name = (TextView)itemView.findViewById(R.id.salon_nametext);
            address= (TextView)itemView.findViewById(R.id.addresstext);
            owner_name = (TextView)itemView.findViewById(R.id.owner_nametext);
            phone_no = (TextView)itemView.findViewById(R.id.phone_notext);
            email = (TextView)itemView.findViewById(R.id.emailtext);

            btnEdit =(Button)itemView.findViewById(R.id.btnEdit);
            btnDelete =(Button)itemView.findViewById(R.id.btnDelete);


        }
    }
}
