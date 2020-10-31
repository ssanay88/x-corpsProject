package com.example.posturecorrection

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
//AppWidgetProvider 이라고 하는 브로드캐스트 리시버 클래스를 상속 받는다
class PostureWidget : AppWidgetProvider() {

    //onUpdate 메서드는 위젯이 업데이트되어야 할 때 호출된다.
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // 위젯이 여러개일 경우 모두 업데이트
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // 위젯이 처음 생성될 때 호출
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    //여러 개일 경우 마지막 위젯이 제거 될 때 호출
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        //위젯을 업데이트할 때 수행되는 코드
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.posture_widget)
            //views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}