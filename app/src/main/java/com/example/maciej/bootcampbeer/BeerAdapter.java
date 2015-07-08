package com.example.maciej.bootcampbeer;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BeerAdapter extends BaseAdapter {

    private BeerDatabase beerDatabase;
    private Context context;

    public BeerAdapter(Context context) {
        this.context = context;
        this.beerDatabase = new BeerDatabase(context);
    }

    @Override
    public int getCount() {
        return beerDatabase.getBeersNumber();
    }

    @Override
    public Beer getItem(int position) {
        return beerDatabase.getBeer(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View beerView;

        if (convertView == null) {
            beerView = LayoutInflater.from(context).inflate(R.layout.beer_row, parent, false);
        } else {
            beerView = convertView;
        }

        bindBeerToView(getItem(position), beerView, position);

        return beerView;
    }

    private void bindBeerToView(Beer beer, View beerView, int position) {
        ImageView beerPhoto = (ImageView) beerView.findViewById(R.id.beer_photo);
        beerPhoto.setImageBitmap(BitmapFactory.decodeFile(beer.getPhoto()));

        TextView beerName = (TextView) beerView.findViewById(R.id.beer_name);
        beerName.setText(beer.getName());

        TextView beerDescription = (TextView) beerView.findViewById(R.id.beer_description);
        beerDescription.setText(beer.getDesctiption());
    }
}
