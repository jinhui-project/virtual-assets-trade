package jinhui;


import com.jinhui.api.task.ConfirmFundTransfer;
import com.jinhui.api.task.QueryMarketTask;
import com.jinhui.api.task.QueryWallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 集成测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.jinhui.*")//测试类要指定扫描路径才能把其他模块的配置类找出来
@SpringBootApplication
public class RootTest {

    @Autowired
    private QueryMarketTask task;

    @Autowired
    private QueryWallet queryWallet;

    @Autowired
    private ConfirmFundTransfer confirmFundTransfer;

    @Test
    public void test1() {

        task.queryMerged();
    }

    @Test
    public void test2() {
        queryWallet.queryWalletBalance();
        queryWallet.queryWalletTx();
    }


    @Test
    public void test3() {
        confirmFundTransfer.confirmFundTransfer();
    }

}