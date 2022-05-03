package com.toby.suntory.user.service;

import com.toby.suntory.user.domain.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);

    void upgradeLevel(User user);
}
