package com.example.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The {@link NewsAdapter} creates a list item layout for each news story in the
 * data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context     of the app.
     * @param newsStories is the list of news stories, which is the data source of the adapter.
     */
    public NewsAdapter(Activity context, ArrayList<News> newsStories) {
        super(context, 0, newsStories);
    }

    /**
     * @return a list item view that displays information about the news story at the given position
     * in the list of news stories
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news story at the given position in the list of news stories.
        News currentNews = getItem(position);

        // Find the TextView with the view ID news_section.
        TextView newsSectionView = listItemView.findViewById(R.id.news_section);
        // Get the news section of the news story.
        String newsSection = currentNews.getNewsSection();
        // Display the section name of the current news in that TextView.
        newsSectionView.setText(newsSection);

        // Find the TextView with the view ID news_title.
        TextView newsTitleView = listItemView.findViewById(R.id.news_title);
        // Get the Title of the news story.
        String newsTitle = currentNews.getNewsTitle();
        // Display the Title of the current news in that TextView.
        newsTitleView.setText(newsTitle);

        // Create a new Date object from the time of the news.
        Date dateObject = new Date(currentNews.getTime());

        // Find the TextView with the view ID date.
        TextView dateView = listItemView.findViewById(R.id.date);
        // Format the date string (i.e "Mar 6, 2010").
        String formattedDate = formatDate(dateObject);
        // Display the date of the current news in that TextView.
        dateView.setText(formattedDate);

        // Find the TextView with the view ID time.
        TextView timeView = listItemView.findViewById(R.id.time);
        // Format the time string (i.e "3.00PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current news in that TextView.
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data.
        return listItemView;
    }

    /**
     * @return the formatted date string (i.e. "Mar 6, 2010") from the Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * @return the formatted date string (i.e. "3.00PM") from the Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
