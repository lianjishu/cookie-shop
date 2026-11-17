/**
 * 加入购物车
 */
function buy(goodsid){
    $.post(ctx + "/cart_add", {goods_id:goodsid}, function(res){
        if(res.code === 200)
        {
            layer.msg("添加到购物车!", {time:800});
            if(res.data && res.data.cartCount !== undefined){
                jQuery(".card_num").text(res.data.cartCount);
            }
        }
        else
        {
            layer.msg(res.message || "添加失败!", {time:800}, function(){
            });
        }
    });
}

/**
 * 减少购物车数量
 */
function lessen(cartid){
    $.post(ctx + "/cart_list", {action:"update", cart_id:cartid, amount:0}, function(res){
        if(res.code === 200){
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
    $.post(ctx + "/cart_list", {action:"delete", cart_id:cartid}, function(res){
        if(res.code === 200){
            layer.msg("删除成功!", {time:800}, function(){
                location.reload();
            });
        }
    });
}