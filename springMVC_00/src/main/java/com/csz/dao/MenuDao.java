package com.csz.dao;

import com.csz.model.Menu;

import java.util.List;

public interface MenuDao {
    public List<Menu> SelectAllMenu();
    public List<Menu> SelectSonMenu();
    public Boolean AddMenu(Menu menu);
    public Boolean deleteMenuById(int id);
    public Boolean UpdateMenuById(Menu menu);
}
