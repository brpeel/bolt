package com.btn.bolt.data;

import com.btn.bolt.data.transfer.Transfer;
import com.btn.bolt.data.transfer.TransferDAO;
import com.btn.bolt.data.user.User;
import com.btn.bolt.data.user.UserDAO;
import org.skife.jdbi.v2.sqlobject.Transaction;

import java.util.Optional;

public class DBController implements Controller {

    private final UserDAO userDAO;
    private final TransferDAO transferDAO;

    public DBController(UserDAO userDAO, TransferDAO transferDAO) {
        this.userDAO = userDAO;
        this.transferDAO = transferDAO;
    }

    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    public long createUser(User user) {
        return userDAO.insert(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.delete(id);
    }

    @Override
    public Transfer getTransfer(long id) {
        return transferDAO.get(id);
    }

    @Override
    @Transaction
    public long createTransfer(Transfer transfer) {
        userDAO.updatePoints(transfer);
        return transferDAO.insert(transfer);
    }
}
