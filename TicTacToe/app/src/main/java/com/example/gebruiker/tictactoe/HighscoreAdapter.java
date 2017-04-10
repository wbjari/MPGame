package com.example.gebruiker.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.model.Player;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class HighscoreAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList list;

    public HighscoreAdapter(Context context, LayoutInflater inflater, ArrayList<Player> list) {
        this.context = context;
        this.inflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_highscores, null);

            viewHolder = new ViewHolder();
            viewHolder.playerPositionTextView = (TextView) convertView.findViewById(R.id.playerPositionTextView);
            viewHolder.playerNameTextView = (TextView) convertView.findViewById(R.id.playerNameTextView);
            viewHolder.playerScoreTextView = (TextView) convertView.findViewById(R.id.playerScoreTextView);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        Player player = (Player) list.get(position);
        viewHolder.playerPositionTextView.setText( Integer.toString(player.highscorePos) + "." );
        viewHolder.playerNameTextView.setText( player.name );
        viewHolder.playerScoreTextView.setText( Integer.toString(player.score) );

        return convertView;
    }

    private static class ViewHolder {
        public TextView playerPositionTextView;
        public TextView playerNameTextView;
        public TextView playerScoreTextView;
    }
}
