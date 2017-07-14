package com.daiqile.test.utils;

import com.daiqile.test.model.Invest;

/**
 * Created by Administrator on 2017/2/7.
 */

public class InvestUtil {
    public static String getStatusStr(String status,Invest invest){
        String str="";
        if(invest.getStatus().equals("3")){
            if(invest.getRepayment_account()==invest.getRepayment_yesaccount()){
                return "已完成";
            }else{
                return "正在还款";
            }
        }
        if(invest.getStatus().equals("1")){
            if(invest.getAccount()==invest.getAccount_yes()){
                return "已满标";
            }else{
                return "立即投标";
            }
        }
        return str;
    }

    public static String getStyle(Integer style){
        String str="";
        switch(style){
            case 0:
                str="还款方式：一次性还本付息";
                break;
            case 1:
                str="还款方式：等额本息,按月还款";
                break;
            case 3:
                str="还款方式：按月付息，到期还本";
                break;
            default:
                break;
        }
        return str;
    }
}
