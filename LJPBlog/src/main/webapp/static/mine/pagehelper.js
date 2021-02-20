/* 工厂模式的创建分页助手对象 */
function createPageHelper(limit, data) {
	var obj = new Object();
	if (data instanceof Array) {
		obj.data = data;
		obj.page = 1;
		obj.limit = limit;
		obj.totalCount = data.length;

		/** 获取总页数 */
		obj.getTotalPage = function() {
			var totalPage = Math.ceil(data.length / limit);
			return totalPage;
		}

		/** 删除数据里的一个元素 */
		obj.deleteElementByIndex = function(index) {
			data.splice(index, 1);
			this.totalCount = data.length;
			return totalCount;
		}

		/** 添加一个元素到数据里面 */
		obj.insertElementByEle = function(ele) {
			this.totalCount = data.push(ele);
			return totalCount;
		}

		/** 获取分页数据的方法 */
		obj.getDataByPage = function(nowPage) {
			var totalPage = Math.ceil(data.length / limit);
			/** 超过最大页数的当做最后一页处理*/
			if (nowPage > totalPage) {
				nowPage = totalPage;
			}
			/** 页数为负数的当做第一页处理 */
			if (nowPage <= 0) {
				nowPage = 1;
			}

			//处理最后一页小于单页数量时的数据
			var array;
			if (nowPage == totalPage && this.data.length % limit != 0) {
				array = new Array(this.data.length  % limit);
			} else {
				/** 如果没有数据 */
				if(this.totalCount == 0){
					array = [];
				}else{
					array = new Array(limit);
				}
			}

			//填充数组
			var first = (nowPage - 1) * limit;
			var count = 0;
			for (var i = 0; i < array.length; i++) {
				array[i] = data[first++];
			}
			//返回数组内容
			return array;
		}
		return obj;
	} else {
		//data不为数组时,创建对象失败,返回null
		return null;
	}
}

function PageHelper(limit, data) {
	this.data = null;
	this.page = 1;
	this.limit = limit;
	this.totalCount = data.length;
	if (data instanceof Array) {
			this.data = data;
	}
	
	
	/** 获取总页数 */
	this.getTotalPage = function() {
		var totalPage = Math.ceil(data.length / limit);
		return totalPage;
	}
	
	/** 删除数据里的一个元素 */
	this.deleteElementByIndex = function(index) {
		data.splice(index, 1);
		totalCount = data.length;
		return totalCount;
	}
	
	/** 添加一个元素到数据里面 */
	this.insertElementByEle = function(ele) {
		totalCount = data.push(ele);
		return totalCount;
	}
	
	/** 获取分页数据的方法 */
	this.getDataByPage = function(nowPage) {
		var totalPage = Math.ceil(data.length / limit);
		/** 超过最大页数的当做最后一页处理*/
		if (nowPage > totalPage) {
			nowPage = totalPage;
		}
		/** 页数为负数的当做第一页处理 */
		if (nowPage <= 0) {
			nowPage = 1;
		}
	
		//处理最后一页小于单页数量时的数据
		var array;
		if (nowPage == totalPage && this.totalCount % limit != 0) {
			array = new Array(this.totalCount % limit);
		} else {
			array = new Array(limit);
		}
	
		//填充数组
		var first = (nowPage - 1) * limit;
		var count = 0;
		for (var i = 0; i < array.length; i++) {
			array[i] = data[first++];
		}
		//返回数组内容
		return array;
	}
}