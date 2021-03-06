package com.yangnan.selfhelpordingsystem.impl;

import com.yangnan.selfhelpordingsystem.constant.BillPayType;
import com.yangnan.selfhelpordingsystem.constant.BillStatus;
import com.yangnan.selfhelpordingsystem.dao.BillDao;
import com.yangnan.selfhelpordingsystem.dto.BillDTO;
import com.yangnan.selfhelpordingsystem.entity.BillEntity;
import com.yangnan.selfhelpordingsystem.service.Billservice;
import com.yangnan.selfhelpordingsystem.util.BeansListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements Billservice {
    @Resource
    private BillDao billDao;

    @Override
    public int insertBill(BillDTO billDTO) {
        if (billDTO == null ||
                billDTO.getPayType() == null || billDTO.getPayType() != BillPayType.UNDER_LINE && billDTO.getPayType() != BillPayType.ON_LINE ||
                billDTO.getPrice() == null || billDTO.getPrice().compareTo(BigDecimal.ZERO) < 0 ||
                billDTO.getUserId() == null || billDTO.getUserId() < 1) {
            return 0;
        }
        BillEntity billEntity = new BillEntity();
        BeanUtils.copyProperties(billDTO, billEntity);
        billDao.insertBill(billEntity);
        return billEntity.getId();
    }

    @Override
    public int updateBillStatus(int id, int status) {
        if (id < 1 || status < BillStatus.CANCEL || status > BillStatus.PAYED) {
            return 0;
        }
        return billDao.updateBillStatus(id, status);
    }

    @Override
    public BillDTO selectBillById(int id) {
        if (id < 0) {
            return new BillDTO();
        }
        BillEntity billEntity = billDao.selectBillById(id);
        if (billEntity == null) {
            return null;
        }
        BillDTO billDTO = new BillDTO();
        BeanUtils.copyProperties(billEntity, billDTO);
        return billDTO;
    }

    @Override
    public List<BillDTO> selectBillByUserId(int userId) {
        if (userId < 0) {
            return new ArrayList<>();
        }
        List<BillEntity> billEntities = billDao.selectBillByUserId(userId);
        if (CollectionUtils.isEmpty(billEntities)) {
            return new ArrayList<>();
        }
        return BeansListUtils.copyListProperties(billEntities, BillDTO.class);
    }

    @Override
    public List<BillDTO> selectBillByStatus(int status) {
        if (status < BillStatus.CANCEL || status > BillStatus.PAYED) {
            return new ArrayList<>();
        }
        List<BillEntity> billEntities = billDao.selectBillByStatus(status);
        if (CollectionUtils.isEmpty(billEntities)) {
            return new ArrayList<>();
        }
        return BeansListUtils.copyListProperties(billEntities, BillDTO.class);
    }

    @Override
    public BillDTO queryBillId(Integer userId, Integer billState) {
        if (userId == null || billState == null){
            return null;
        }
        BillEntity billEntity = billDao.queryBillId(userId,billState);
        if (billEntity == null){
            return null;
        }
        BillDTO billDTO = new BillDTO();
        BeanUtils.copyProperties(billEntity,billDTO);
        return billDTO;
    }

    @Override
    public int updatePrices(BigDecimal price, Integer billId, Integer billState) {
        if (billId == null || billState == null){
            return 0;
        }
        return billDao.updatePrices(price,billId,billState);
    }

    @Override
    public int settleAccounts(BigDecimal price, Integer payType, Integer billState, Integer billId) {
        if (payType == null || billState == null || billId == null){
            return 0;
        }
        return billDao.settleAccounts(price,payType,billState,billId);
    }
}
