package com.senneville.project.web.ui;

import javax.persistence.EntityManager;

import com.senneville.project.core.baby.Client;
import com.senneville.project.core.baby.Course;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class MainView extends HorizontalSplitPanel implements ComponentContainer {

	private JPAContainer<Client> clients;
	private JPAContainer<Course> courses;
	
	private Table clientsTable;
	
	private TextField searchField;
	
    private Button newButton;
    private Button deleteButton;
    private Button editButton;

    public MainView(EntityManager entityManager) {
    	courses = new HierarchicalCourseContainer(entityManager);
    	clients = JPAContainerFactory.make(Client.class, entityManager);
        buildTree();
        buildMainArea();

        setSplitPosition(30);
    }

    private void buildMainArea() {
        VerticalLayout verticalLayout = new VerticalLayout();
        setSecondComponent(verticalLayout);

        this.clientsTable = new Table(null, clients);
        this.clientsTable.setSelectable(true);
        this.clientsTable.setImmediate(true);
        this.clientsTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                setModificationsEnabled(event.getProperty().getValue() != null);
            }

            private void setModificationsEnabled(boolean b) {
                deleteButton.setEnabled(b);
                editButton.setEnabled(b);
            }
        });

        this.clientsTable.setSizeFull();
        // personTable.setSelectable(true);
        this.clientsTable.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                	clientsTable.select(event.getItemId());
                }
            }
        });

        clientsTable.setVisibleColumns(new Object[] { "firstName", "lastName",
                "department", "phoneNumber", "street", "city", "zipCode" });

        HorizontalLayout toolbar = new HorizontalLayout();
        newButton = new Button("Add");
        newButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                final BeanItem<Client> newPersonItem = new BeanItem<Client>(
                        new Client());
                ClientEditor personEditor = new ClientEditor(newPersonItem);
                personEditor.addListener(new EditorSavedListener() {
                    @Override
                    public void editorSaved(EditorSavedEvent event) {
                        clients.addEntity(newPersonItem.getBean());
                    }
                });
                UI.getCurrent().addWindow(personEditor);
            }
        });

        deleteButton = new Button("Delete");
        deleteButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                persons.removeItem(personTable.getValue());
            }
        });
        deleteButton.setEnabled(false);

        editButton = new Button("Edit");
        editButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().addWindow(
                        new PersonEditor(personTable.getItem(personTable
                                .getValue())));
            }
        });
        editButton.setEnabled(false);

        searchField = new TextField();
        searchField.setInputPrompt("Search by name");
        searchField.addTextChangeListener(new TextChangeListener() {

            @Override
            public void textChange(TextChangeEvent event) {
                textFilter = event.getText();
                updateFilters();
            }
        });

        toolbar.addComponent(newButton);
        toolbar.addComponent(deleteButton);
        toolbar.addComponent(editButton);
        toolbar.addComponent(searchField);
        toolbar.setWidth("100%");
        toolbar.setExpandRatio(searchField, 1);
        toolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);

        verticalLayout.addComponent(toolbar);
        verticalLayout.addComponent(personTable);
        verticalLayout.setExpandRatio(personTable, 1);
        verticalLayout.setSizeFull();

    }

    private void buildTree() {
        groupTree = new Tree(null, departments);
        groupTree.setItemCaptionPropertyId("name");

        groupTree.setImmediate(true);
        groupTree.setSelectable(true);
        groupTree.addListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object id = event.getProperty().getValue();
                if (id != null) {
                    Department entity = departments.getItem(id).getEntity();
                    departmentFilter = entity;
                } else if (departmentFilter != null) {
                    departmentFilter = null;
                }
                updateFilters();
            }

        });
        setFirstComponent(groupTree);
    }

    private void updateFilters() {
        persons.setApplyFiltersImmediately(false);
        persons.removeAllContainerFilters();
        if (departmentFilter != null) {
            // two level hierarchy at max in our demo
            if (departmentFilter.getParent() == null) {
                persons.addContainerFilter(new Equal("department.parent",
                        departmentFilter));
            } else {
                persons.addContainerFilter(new Equal("department",
                        departmentFilter));
            }
        }
        if (textFilter != null && !textFilter.equals("")) {
            Or or = new Or(new Like("firstName", textFilter + "%", false),
                    new Like("lastName", textFilter + "%", false));
            persons.addContainerFilter(or);
        }
        persons.applyFilters();
    }
}

