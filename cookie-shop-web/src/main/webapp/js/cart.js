/**
 * 加入购物车
 */
function buy(goodsid){
    $.post(ctx + "/cart_add", {goods_id:goodsid}, function(data){
        if(data.success)
        {
            layer.msg("添加到购物车!", {time:800});
            if(data.cartCount !== undefined){
                jQuery(".card_num").text(data.cartCount);
            }
        }
        else
        {
            layer.msg(data.message || "添加失败!", {time:800}, function(){
            });
        }
    });
}

/**
 * 减少购物车数量
 */
function lessen(cartid){
    $.post(ctx + "/cart_list", {action:"update", cart_id:cartid, amount:0}, function(data){
        if(data.success){
            layer.msg("操作成功!", {time:800}, function(){
                location.reload();
            });
        }
    });
}

/**
 * 购物车删除
 */
function deletes(cartid){
    $.post(ctx + "/cart_list", {action:"delete", cart_id:cartid}, function(data){
        if(data.success){
            layer.msg("删除成功!", {time:800}, function(){
                location.reload();
            });
        }
    });
}