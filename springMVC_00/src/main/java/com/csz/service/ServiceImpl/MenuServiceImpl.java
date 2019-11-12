package com.csz.service.ServiceImpl;

import com.csz.dao.MenuDao;
import com.csz.model.Menu;
import com.csz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> SelectAllMenu() {
        return menuDao.SelectAllMenu();
    }

    @Override
    public List<Menu> SelectSonMenu() {
        return menuDao.SelectSonMenu();
    }

    @Override
    public Boolean AddMenu(Menu menu) {
        return menuDao.AddMenu(menu);
    }

    @Override
    public Boolean deleteMenuById(int id) {
        return menuDao.deleteMenuById(id);
    }

    @Override
    public Boolean UpdateMenuById(Menu menu) {
        return menuDao.UpdateMenuById(menu);
    }


}
