package com.senneville.project.web.ui;

import javax.persistence.EntityManager;

import com.senneville.project.core.baby.Client;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class BabyLayout extends HorizontalSplitPanel {

	private Table babyTable;
	private BabyEditor currentBabyEditor;
	private JPAContainer<Client> babyJpaContainer;
	
	public BabyLayout(EntityManager entityManager) {
		
		this.babyJpaContainer = JPAContainerFactory.make(Client.class, entityManager);
		this.babyTable = new Table(null, this.babyJpaContainer);
		this.babyTable.setSelectable(true);

		Button neww = new Button("New");
		neww.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if (currentBabyEditor != null) {
					removeComponent(currentBabyEditor);
				}
				currentBabyEditor = new BabyEditor(new BeanItem<Client>(new Client())); 
				addComponent(currentBabyEditor);
				
			}
		});

		Button edit = new Button("Edit");
		edit.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if (currentBabyEditor != null) {
					removeComponent(currentBabyEditor);
				}
				currentBabyEditor = new BabyEditor(babyTable.getItem(babyTable.getValue())); 
				addComponent(currentBabyEditor);
				
			}
		});
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponent(this.babyTable);
		verticalLayout.addComponent(neww);
		verticalLayout.addComponent(edit);
		
		this.addComponent(verticalLayout);
	}
}
