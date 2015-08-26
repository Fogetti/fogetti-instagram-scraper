package com.fogetti.webscraping.result;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.fogetti.webscraping.service.IInstagram;
import com.fogetti.webscraping.start.Selection;

public class ResultPage extends WebPage {

	private static final long serialVersionUID = -2190317550425626679L;
	private final List<Selection> mediaIds;
	private final IInstagram instagram;

	public ResultPage(List<Selection> mediaIds, IInstagram instagram) {
		this.mediaIds = mediaIds;
		this.instagram = instagram;
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		
		List<ScrapedItem> results = buildResults();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		@SuppressWarnings({ "rawtypes" })
		final ListView<ScrapedItem> repeater = new ListView<ScrapedItem>("results", Model.ofList(results)) {

			private static final long serialVersionUID = -1539128785828449605L;

			@Override
			protected void populateItem(ListItem<ScrapedItem> item) {
				ScrapedItem scrapedItem = item.getModelObject();
				item.add(new Label("url", new PropertyModel(scrapedItem, "url")));
				item.add(new Label("content", new PropertyModel(scrapedItem, "content")));
				item.add(new Label("likeCount", new PropertyModel(scrapedItem, "likeCount")));
				item.add(new Label("commentCount", new PropertyModel(scrapedItem, "commentCount")));
				item.add(new Label("mentionedUrl", new PropertyModel(scrapedItem, "mentionedUrl")));
				item.add(new Label("mentionedBio", new PropertyModel(scrapedItem, "mentionedBio")));
				item.add(new Label("followerCount", new PropertyModel(scrapedItem, "followerCount")));
			}
		};
		repeater.setReuseItems(true);
		final WebMarkupContainer panel = new WebMarkupContainer("resultPanel") {
			private static final long serialVersionUID = 9166543361873756969L;
			
			@Override
			public void onInitialize() {
				super.onInitialize();
				add(repeater);
				add(feedback);
			}
		};
		panel.setOutputMarkupId(true);
		add(panel);
	}

	List<ScrapedItem> buildResults() {
		ArrayList<ScrapedItem> results = new ArrayList<>();
		results.add(new ScrapedItem());
		return results;
	}

}
