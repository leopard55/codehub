package com.jihai.tx;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 事务中调用mq。业务上，本地事务提交成功了，采希望去发送MQ。事务回滚了，但是中间消息已经发送出去了，没法撤回。
public class TransactionUtils {

    public static void doAfterTransaction(DoTransactionCompletion doTransactionCompletion) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(doTransactionCompletion);
        }
    }

    @Transactional
    public void doTx() {
        // start tx

        TransactionUtils.doAfterTransaction(new DoTransactionCompletion(() -> {
            // send MQ... RPC...
        }));
        
        TransactionUtils.doAfterTransaction(new DoTransactionCompletion(new Runnable() {
            @Override
            public void run() {

            }
        }));

        // end tx
    }
}

class DoTransactionCompletion implements TransactionSynchronization {

    private Runnable runnable;

    public DoTransactionCompletion(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void afterCompletion(int status) {
        if (status == TransactionSynchronization.STATUS_COMMITTED) {
            this.runnable.run();
        }
    }

    public static void main(String[] args) throws ParseException {
        //格式化(从 Date 到 String )
        Date d = new Date();
        //SimpleDateFormat()：构造一个SimpleDateFormat，使用默认模式和日期格式
//        SimpleDateFormat sdf = new SimpleDateFormat();
        //SimpleDateFormat(String pattern)：构造一个SimpleDateFormat使用给定的模式和默认的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String s = sdf.format(d);
        System.out.println(s);
        System.out.println("-----------------");

        //解析(从 String 到 Date )
        String ss = "2021-10-27 11:11:11";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dd = sdf2.parse(ss);
        System.out.println(dd);
    }
}
