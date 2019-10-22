package com.example.kochbuch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder>{

    private Context context;
    private List<Recipe> recipeList = new ArrayList<>();
    private OnItemClickListener mListener;

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        //private TextView textViewDescription;
        private ImageView imageView;

        public RecipeHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }



    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_layout, viewGroup, false);
        return new RecipeHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder recipeHolder, int i) {
        Recipe recipe = recipeList.get(i);

        recipeHolder.textViewTitle.setText(recipe.getTitle());




        recipeHolder.imageView.setImageResource(recipe.getImage());
    }



    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipe(List<Recipe> recipe) {
        this.recipeList = recipe;
        notifyDataSetChanged(); //TODO Maybe replace with more efficent
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
