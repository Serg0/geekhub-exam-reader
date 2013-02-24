package com.geekhub.nadolinskyi.serhii.simlpereader.utils;

import java.util.List;

import com.geekhub.nadolinskyi.serhii.simlpereader.R;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.Article;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticlesArrayAdapter extends ArrayAdapter<Article> {
	private static final String LOG_TAG = "ROW ADAPTER";
	private final Context context;
	private View convertView;
/*	private TextView title,subTitle;
	private ImageView icon;*/
	private static List<Article> articles;
	
	public ArticlesArrayAdapter(Context context,
			List<Article> OuterArticles) {
		super(context, R.layout.article_row, OuterArticles);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.articles = OuterArticles;
		
		/*title =  (TextView) convertView.findViewById(R.id.title);
		subTitle =(TextView) convertView.findViewById(R.id.subTitle);
		icon = (ImageView) convertView.findViewById(R.id.icon);*/
		
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Article article = articles.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.article_row, parent, false);
		}
//		this.convertView = inflater.inflate(R.layout.article_row, parent);
		
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView subTitle = (TextView) convertView.findViewById(R.id.subTitle);
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
		
		title.setText(article.getTitle());
//		subTitle.setText(article.getSubtitle());
		
		return convertView /*super.getView(position, convertView, parent)*/;
	}
	


}
