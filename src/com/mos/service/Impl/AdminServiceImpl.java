package com.mos.service.Impl;

import com.mos.dao.AdminDAO;
import com.mos.dao.Impl.AdminDAOImpl;
import com.mos.domain.Admin;
import com.mos.service.AdminService;

public class AdminServiceImpl implements AdminService {

    AdminDAO adao = new AdminDAOImpl();
    @Override
    public int changeAdminPassword(String aacount, String newPassword) {
        return adao.UpdatePassword(aacount, newPassword);
    }

    @Override
    public String verifyPassword(String aacount) {
        return adao.selectPasswordByAccount(aacount);
    }

    @Override
    public String login(String aacount) {
        return adao.selectPasswordByAccount(aacount);
    }

    @Override
    public int register(Admin admin) {
        return adao.add(admin);
    }

    @Override
    public boolean verifyAacount(String aacount) {
        return adao.verifyAacount(aacount);
    }

    @Override
    public Admin selectByAacount(String aacount) {
        return adao.selectByAacount(aacount);
    }

    @Override
    public int modifyPassword(String aacount, String newPassword) {
        return adao.updatePassword(aacount, newPassword);
    }
}
