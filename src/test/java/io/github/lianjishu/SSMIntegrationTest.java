package io.github.lianjishu;

import io.github.lianjishu.entity.Goods;
import io.github.lianjishu.entity.Page;
import io.github.lianjishu.entity.Type;
import io.github.lianjishu.service.GoodsService;
import io.github.lianjishu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * SSM 整合测试（对应教材文件 14-9）：验证 Spring + MyBatis 贯通
 * 运行前提：MySQL 中已导入 cookie-shop/sql/cookieshop_copied.sql
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml"})
public class SSMIntegrationTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllTypes() {
        List<Type> types = goodsService.getAllTypes();
        assertNotNull(types);
        assertFalse(types.isEmpty());
    }

    @Test
    public void testGetGoodsByPage() {
        Page page = goodsService.getGoodsByPage(1, 12);
        assertNotNull(page);
        assertNotNull(page.getList());
        assertFalse(page.getList().isEmpty());
    }

    @Test
    public void testGoodsAssociation() {
        List<Goods> goods = goodsService.getAllGoods();
        assertNotNull(goods);
        assertFalse(goods.isEmpty());
        // 验证 resultMap 关联映射：type_name 与 type 对象同时填充
        Goods first = goods.get(0);
        System.out.println("商品: " + first.getName() + ", 分类: " + first.getType_name()
                + " / " + first.getType().getName());
        assertEquals(first.getType_name(), first.getType().getName());
    }

    @Test
    public void testUserPage() {
        Page page = userService.getUserPage(1);
        assertNotNull(page);
        assertNotNull(page.getList());
    }
}
