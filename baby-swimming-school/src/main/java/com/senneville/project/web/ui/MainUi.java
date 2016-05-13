package com.senneville.project.web.ui;

import java.util.Spliterator;
import java.util.function.Consumer;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senneville.project.core.baby.ClientCrudRepository;
import com.senneville.project.web.command.line.BabyDataGenerator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Component
public class MainUi extends UI {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClientCrudRepository clientCrudRepository;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
		BabyDataGenerator.create(this.clientCrudRepository);
        this.setContent(new BabyLayout(this.entityManager));
    }

	@Override
	public void forEach(Consumer<? super com.vaadin.ui.Component> action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spliterator<com.vaadin.ui.Component> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
