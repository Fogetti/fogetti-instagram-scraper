package com.fogetti.webscraping.start;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import com.fogetti.webscraping.result.ResultPage;
import com.fogetti.webscraping.service.IInstagram;

public class DynamicForm extends Form<Object> {
	
	private static final long serialVersionUID = 4062269917574515002L;
	private final List<Selection> mediaIds = new ArrayList<>();
	private final IInstagram instagram;

	public DynamicForm(String id, IInstagram instagram) {
		super(id);
		this.instagram = instagram;
		setOutputMarkupId(true);
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final ListView<Selection> repeater = new ListView<Selection>("mediaIds", new PropertyModel(this, "mediaIds")) {

			private static final long serialVersionUID = -1539128785828449605L;

			@Override
			protected void populateItem(ListItem<Selection> item) {
				Selection selection = item.getModelObject();
				item.add(new RequiredTextField<String>("mediaId", new PropertyModel(selection, "mediaId")));
			}
		};
		repeater.setReuseItems(true);
		final WebMarkupContainer panel = new WebMarkupContainer("mediaPanel") {
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
		add(new AjaxButton("addButton", this) {
			private static final long serialVersionUID = -2385148703477677749L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
			    // repaint the forml so that it is hidden
				mediaIds.add(new Selection());
			    target.add(panel);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			    // repaint the form so errors are shown
			    target.add(panel);
			}
		});
		add(new Button("sendButton") {

			private static final long serialVersionUID = 5836170808658354768L;

			@Override
			public void onSubmit()
			{
				setResponsePage(new ResultPage(mediaIds, instagram));
			}
		});
	}
	
}
