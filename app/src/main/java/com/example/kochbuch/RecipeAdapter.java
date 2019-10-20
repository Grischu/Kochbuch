package com.example.kochbuch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    private Context context;
    private List<Recipe> recipeList;
    private OnItemClickListener mListener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new RecipeViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        Recipe recipe = recipeList.get(position);

        recipeViewHolder.textViewTitle.setText(recipe.getTitle());
        recipeViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(recipe.getImage()));

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewDescription;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textViewTitle = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }*/


                        Intent intent = new Intent(context, RecipeViewActivity.class);

                        //intent.putExtra("image_url", imageView.get(position));

                        context.startActivity(intent);

                   // }
                }
            });
        }
}


}
