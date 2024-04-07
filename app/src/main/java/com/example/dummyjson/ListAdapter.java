package com.example.dummyjson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> arrayList;
    ImageView listImg;
    public ListAdapter(@NonNull Context context, ArrayList<Product> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
        this.arrayList = dataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Product product = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView listPrice = view.findViewById(R.id.listPrice);
        TextView listName = view.findViewById(R.id.listName);
        TextView listDesc = view.findViewById(R.id.listDesc);
        TextView listRating = view.findViewById(R.id.listRating);

        listImg = view.findViewById(R.id.listImage);

        listName.setText(product.title);
        listDesc.setText(product.desc);
        listPrice.setText("$" + product.price);
        listRating.setText(product.rating);
        new LoadImageInternet().execute(product.imageUrl);

        return view;
    }

    class LoadImageInternet extends AsyncTask<String, Void, Bitmap>{

        Bitmap bitmapHinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                InputStream inputStream = url.openConnection().getInputStream();

                bitmapHinh = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            listImg.setImageBitmap(bitmap);
        }
    }
}
