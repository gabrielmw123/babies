package com.senneville.project.web.ui;

import javax.persistence.EntityManager;

import com.senneville.project.core.baby.Client;
import com.senneville.project.core.baby.Course;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;

public class HierarchicalCourseContainer extends JPAContainer<Course> {

    public HierarchicalCourseContainer(EntityManager entityManager) {
        super(Course.class);
        setEntityProvider(new CachingLocalEntityProvider<Course>(Course.class, entityManager));
        setParentProperty("parent");
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId);
                //&& getItem(itemId).getEntity().isSuperDepartment();
    }
}
