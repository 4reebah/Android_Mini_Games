package android.zoo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List <AnimalItem> animals;

    public CustomAdapter(Context context, List animals) {
        this.context = context;
        this.animals = animals;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtHeader;
        public View layout;
        private ImageView mImg;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.textView);
            mImg = (ImageView) itemView.findViewById(R.id.imageAnimal);
            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v)
        {
            int position = getAdapterPosition();
            AnimalItem item = animals.get(position);

            if (item.getAnimal().equals("Lion")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("This animal is scary! Would you like to continue?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, AnimalInformationActivity.class);
                        intent.putExtra("title", item.getAnimal());
                        intent.putExtra("image", item.getImageUrl());
                        intent.putExtra("description", item.getDescription());
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }

            else
            {
                Intent intent = new Intent(context, AnimalInformationActivity.class);
                intent.putExtra("title", item.getAnimal());
                intent.putExtra("image", item.getImageUrl());
                intent.putExtra("description", item.getDescription());
                context.startActivity(intent);

            }

        }

        public TextView getTextHeader() {
            return txtHeader;
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getTextHeader().setText(animals.get(position).getAnimal());
        viewHolder.mImg.setImageResource(animals.get(position).getImageUrl());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return animals.size();
    }
}




