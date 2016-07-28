/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.resources;

import com.whymenu.data.MenuItem;
import java.util.Collection;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.apache.commons.collections.CollectionUtils;


/**
 *
 * @author moscac
 */
public class MenuItemPagedResources extends PagedResources<MenuItem> {

    @SuppressWarnings("unchecked")
    public MenuItemPagedResources(final Collection<MenuItem> content, final PageMetadata metadata) {
        super(content, metadata, CollectionUtils.EMPTY_COLLECTION);
    }

    public MenuItemPagedResources() {
        super();
    }
}
