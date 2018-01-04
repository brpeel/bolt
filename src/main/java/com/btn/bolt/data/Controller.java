package com.btn.bolt.data;

import com.btn.bolt.data.transfer.Transfer;
import com.btn.bolt.data.user.User;

public interface Controller {
    User getUser(long id);

    long createUser(User obj);

    void deleteUser(long id);

    Transfer getTransfer(long id);

    long createTransfer(Transfer transfer);

}
