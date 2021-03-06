/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.arquillian.graphene.findby;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;

import org.jboss.arquillian.core.spi.Validate;
import org.jboss.arquillian.graphene.spi.ImplementedBy;
import org.jboss.arquillian.graphene.spi.TypeResolver;
import org.jboss.arquillian.graphene.spi.findby.LocationStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Allows to search for elements using jQuery selector location strategy.
 *
 * @author Juraj Huska
 */
@ImplementedBy(className = "org.jboss.arquillian.graphene.findby.ByJQueryImpl")
public class ByJQuery extends By {

    private By implementation;

    /**
     * Instantiates new locator with given jQuery selector
     *
     * @param selector the jQuery selector
     */
    public ByJQuery(String selector) {
        Validate.notNull(selector, "Cannot find elements when selector is null!");
        this.implementation = instantiate(selector);
    }

    /**
     * Instantiates new locator with given jQuery selector
     *
     * @param selector the jQuery selector
     */
    public static ByJQuery selector(String selector) {
        return new ByJQuery(selector);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.openqa.selenium.By#findElement(org.openqa.selenium.SearchContext)
     */
    @Override
    public WebElement findElement(SearchContext context) {
        return implementation.findElement(context);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.openqa.selenium.By#findElements(org.openqa.selenium.SearchContext)
     */
    @Override
    public List<WebElement> findElements(SearchContext context) {
        return implementation.findElements(context);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.openqa.selenium.By#toString()
     */
    @Override
    public String toString() {
        return implementation.toString();
    }

    private static By instantiate(String selector) {
        try {
            Class<? extends By> clazz = (Class<? extends By>) TypeResolver.resolveType(ByJQuery.class);

            Constructor<? extends By> constructor = clazz.getConstructor(String.class);

            return constructor.newInstance(selector);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot instantiate ByJQuery", e);
        }
    }

    /**
     * Location strategy for searching for elements using jQuery selectors
     *
     * @author Lukas Fryc
     */
    public static class JQueryLocationStrategy implements LocationStrategy {

        @Override
        public ByJQuery fromAnnotation(Annotation annotation) {
            FindByJQuery findBy = (FindByJQuery) annotation;
            return new ByJQuery(findBy.value());
        }
    }
}
