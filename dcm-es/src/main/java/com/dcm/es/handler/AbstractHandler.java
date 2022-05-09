package com.dcm.es.handler;

import com.dcm.es.entity.BinlogDataDto;
import com.dcm.es.entity.ChangeTypeEnum;
import java.util.Objects;

/**
 *
 **/
public abstract class AbstractHandler implements IHandler {

    @Override
    public final void handle(BinlogDataDto data) {
        ChangeTypeEnum changeTypeEnum = ChangeTypeEnum.getByType(data.getType());
        if (Objects.isNull(changeTypeEnum)) {
            return;
        }
        switch (changeTypeEnum) {
            case INSERT:
            case UPDATE:
            case BOOT_INSERT:
                save(data);
                break;
            case DELETE:
                delete(data);
                break;
            default:
                break;
        }
    }

    protected void delete(BinlogDataDto data) {

    }

    protected abstract void save(BinlogDataDto data);

}
