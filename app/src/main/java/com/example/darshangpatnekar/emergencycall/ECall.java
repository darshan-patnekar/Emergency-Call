package com.example.darshangpatnekar.emergencycall;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ECallConfigureActivity ECallConfigureActivity}
 */
public class ECall extends AppWidgetProvider{

    static SharedPreferences pref;
    SharedPreferences.Editor editor;



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        //pref = context.getSharedPreferences("MyPref", 0);
        String name = "!";
        //Toast.makeText(context,name+" contact name", Toast.LENGTH_SHORT).show();

        // There may be multiple widgets active, so update all of them
        ComponentName thisWidget = new ComponentName(context,ECall.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds)
        {
            // create some random data
            int number1 = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.ecall);
            Log.w("WidgetExample", name);
            // Set the text
            remoteViews.setTextViewText(R.id.button, name);

            // Register an onClickListener
            Intent intent = new Intent(context,MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
/*
            Intent intent1 = getIntent();
            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");*/
        }
    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

        // When the user deletes the widget, delete the preference associated with it.
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            ECallConfigureActivity.deleteTitlePref(context, appWidgetIds[i]);
        }
        Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId)
    {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ecall);
        views.setTextViewText(R.id.button, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }


}

