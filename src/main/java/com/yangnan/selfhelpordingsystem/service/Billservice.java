package com.yangnan.selfhelpordingsystem.service;

import com.yangnan.selfhelpordingsystem.dto.BillDTO;

import java.math.BigDecimal;
import java.util.List;

public interface Billservice {
    /**
     * 新增账单
     *
     * @param billDTO
     * @return
     */
    int insertBill(BillDTO billDTO);

    /**
     * 更新账单状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateBillStatus(int id, int status);

    /**
     * 通过id查订单
     *
     * @param id
     * @return
     */
    BillDTO selectBillById(int id);

    /**
     * 通过用户id查订单
     *
     * @param userId
     * @return
     */
    List<BillDTO> selectBillByUserId(int userId);

    /**
     * 根据订单状态查订单
     *
     * @param status
     * @return
     */
    List<BillDTO> selectBillByStatus(int status);

    /**
     * 继续下单
     *
     * @param userId
     * @param billState
     * @return
     */
    BillDTO queryBillId(Integer userId, Integer billState);

    /**
     * 根据Id修改价格
     *
     * @param price
     * @param billId
     * @param billState
     * @return
     */
    int updatePrices(BigDecimal price,Integer billId, Integer billState);

    /**
     * 结账
     *
     * @param price
     * @param payType
     * @param billState
     * @param billId
     * @return
     */
    int settleAccounts(BigDecimal price, Integer payType, Integer billState, Integer billId);
}
