package com.senneville.project.web.ui;

import lombok.Getter;

import com.senneville.project.web.ui.listeners.CancelButtonClickListener;
import com.senneville.project.web.ui.listeners.SaveButtonClickListener;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;

public class BabyEditor extends VerticalLayout {

	@Getter private FieldGroup fieldGroup;
	private FormLayout formLayout;
	private Button saveButton;
	private Button cancelButton;
	private Button refreshButton;

	public BabyEditor(Item babyItem) {
		/*
		FieldGroupFieldFactory fieldGroupFieldFactory = new FieldGroupFieldFactory() {
			@Override
			public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
				return fieldType.cast(new TextField());
			}
		};*/

		this.fieldGroup = new FieldGroup(babyItem);
		
		this.formLayout = new FormLayout();
		this.formLayout.addComponent(fieldGroup.buildAndBind("babyFirstName"));
		this.formLayout.addComponent(fieldGroup.buildAndBind("babyLastName"));
		this.formLayout.addComponent(fieldGroup.buildAndBind("parentFirstName"));
		this.formLayout.addComponent(fieldGroup.buildAndBind("parentLastName"));
		
		this.addComponent(this.formLayout);
		
		this.saveButton = new Button("Save", new SaveButtonClickListener(this));
		this.cancelButton = new Button("Cancel", new CancelButtonClickListener(this));
		this.refreshButton = new Button("Refresh");
		
		this.addComponent(this.saveButton);
		this.addComponent(this.cancelButton);
		this.addComponent(this.refreshButton);
	}
}
