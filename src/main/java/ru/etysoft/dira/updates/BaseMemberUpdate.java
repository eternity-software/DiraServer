package ru.etysoft.dira.updates;

import ru.etysoft.dira.requests.entities.BaseMember;

/**
 * Update with basic info about online member
 */
public class BaseMemberUpdate extends Update {

    private BaseMember baseMember;

    public BaseMemberUpdate(BaseMember baseMember) {
        super(0, UpdateType.BASE_MEMBER_UPDATE);
        this.baseMember = baseMember;
        setUpdateExpireSec(10);
    }

    public BaseMember getBaseMember() {
        return baseMember;
    }

    public void setBaseMember(BaseMember baseMember) {
        this.baseMember = baseMember;
    }
}
