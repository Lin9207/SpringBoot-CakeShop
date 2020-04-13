/**
 * 加入购物车
 */
function buy(id){
	$.post("/good/buy", {id:id}, function(data){
		if(data=="ok"){
			layer.msg("添加到购物车!", {time:800}, function(){
				location.reload();
			});
		}else if(data=="fail"){
			alert("库存不足!");
			location.reload();
		}
	});
}
/**
 * 购物车减去
 */
function lessen(id){
	$.post("/good/lessen", {id:id}, function(data){
		if(data=="ok"){
			layer.msg("操作成功!", {time:800}, function(){
				location.reload();
			});
		}
	});
}
/**
 * 购物车删除
 */
function deletes(id){
	$.post("/good/deletes", {id:id}, function(data){
		if(data=="ok"){
			layer.msg("删除成功!", {time:800}, function(){
				location.reload();
			});
		}
	});
}