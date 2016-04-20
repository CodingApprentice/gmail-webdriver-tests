package com.appsenseca.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by SunilM on 20/04/2016.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public interface Critical {

}

