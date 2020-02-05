package org.ez.vk.helpers.impl;

import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractHelper {
    @Autowired
    AccountService accountService;
}
