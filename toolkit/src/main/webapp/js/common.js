//通用Form提交表单
$(function() {
	$("#inputForm").validate({
		// 验证部分
		rules : {
			name : {
				required : true
			},
		},
		// 验证的提示信息
		messages : {
			name : {
				required : "请填写产品名称 "
			},
		},
		submitHandler : function(form) {
			loading('正在提交，请稍等...');
			form.submit();
			/*
			 * debugger for ( instance in CKEDITOR.instances )
			 * CKEDITOR.instances[instance].updateElement();
			 */
		},
	});
});

// 联动级联加载列表
function getCity(destination) {
	$("#place").empty();// 清空下拉框
	if (null == destination || destination == "") {
		// 添加默认值
		var option = $("<option></option>").text("所有").val("");
		$("#place").append(option);
		return;
	}

	$.ajax({
		type : "get",
		url : "${ctx}/destination/listForPlaceJson",
		dataType : "JSON",
		data : {
			"parentId" : destination
		}, // 参数值
		success : function(list) {
			// 遍历
			$.each(list, function(i, p) {
				var option = $("<option></option>").text(p.name).val(p.id);
				$("#place").append(option);
			});
		}
	})
}

// 新增分类-弹出框
function save() {
	// 调父窗口请用 parent 或 top，如果是多层iframe，需要调用多个parent
	var html = "<div style='padding:10px;'>产品分类： <input id=\"category\" name=\"category\" type=\"text\" value=\"\" /></div>";
	var submit = function(v, h, f) {
		if (f.category == '') {
			// f.some 或 h.find('#some').val() 等于 top.$('#some').val()
			top.$.jBox.tip("请输入点什么。", 'error', {
				focusId : "category"
			}); // 关闭设置 some 为焦点
			return false;
		}

		saveCategory(f.category);

		return true;
	};

	top.$.jBox(html, {
		title : "新增产品分类",
		submit : submit
	});
}

function saveCategory(name) {

	var data = {
		name : name
	};

	var text = "确定要新增产品分类吗？";

	if (confirm(text)) {
		$.ajax({
			type : "post",
			url : "${ctx}/productCategory/save/",
			data : data,
			success : function(result) {
				if (result.msg == "success") {
					window.location.href = "${ctx}/productCategory/list/";
				}
			}
		});
	}
}

function updateProduct(shelveFlag) {

	var f = $("input[name='ids']").is(':checked');
	if (!f) {
		alert("请先选择记录！");
		return;
	}

	var id = $("input[name='ids']:checked").val();

	var data = {
		id : id,
		shelveFlag : shelveFlag
	};

	var text = "确定要上架？";

	if (confirm(text)) {
		$.ajax({
			type : "post",
			url : "${ctx}/product/update/",
			data : data,
			success : function(result) {
				if (result.msg == "success") {
					window.location.href = "${ctx}/product/list/";
				}
			}
		});
	}
}

function editProduct(type) {

	var title = "";
	var url = "";
	var id;
	if (type == 1) {
		title = "新增产品";
		url = "iframe:${ctx}/product/add/";
	} else {

		var f = $("input[name='ids']").is(':checked');
		if (!f) {
			alert("请先选择记录！");
			return;
		}

		id = $("input[name='ids']:checked").val();

		title = "编辑产品";
		url = "iframe:${ctx}/product/edit/" + id;
	}

	top.$.jBox.open(url, title, 800, $(top.document).height() - 200, {
		closeOnClick : false,
		buttons : {
			"保存" : "save"
		},
		bottomText : "",
		submit : function(v, h, f) {
			var iframeDom = $(h.find("iframe")[0].contentWindow.document);
			var inputForm = iframeDom.find("#inputForm");
			var category = iframeDom.find("#category").val();
			var name = iframeDom.find("#name").val();
			var imgUrl = iframeDom.find("#imgUrl").val();
			var productUrl = iframeDom.find("#productUrl").val();
			var originalPrice = iframeDom.find("#originalPrice").val();
			var currentPrice = iframeDom.find("#currentPrice").val();
			var destination = iframeDom.find("#destination").val();
			var place = iframeDom.find("#place").val();

			if (category == '') {
				iframeDom.find("#categoryError").html(
						"<font color='red'> * 产品类别不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#categoryError").html(
						"<font color='red'> * </font>");
			}

			if (name == '') {
				iframeDom.find("#nameError").html(
						"<font color='red'> * 产品名称不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#nameError").html(
						"<font color='red'> * </font>");
			}

			if (imgUrl == '') {
				iframeDom.find("#imgUrlError").html(
						"<font color='red'> * 图片URL不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#imgUrlError").html(
						"<font color='red'> * </font>");
			}

			if (productUrl == '') {
				iframeDom.find("#productUrlError").html(
						"<font color='red'> * 产品链接不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#productUrlError").html(
						"<font color='red'> * </font>");
			}

			if (originalPrice == '') {
				iframeDom.find("#originalPriceError").html(
						"<font color='red'> * 原价不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#originalPriceError").html(
						"<font color='red'> * </font>");
			}

			if (isNaN(originalPrice)) {
				iframeDom.find("#originalPriceError").html(
						"<font color='red'> * 请填写数字类型！</font>");
				return false;
			} else {
				iframeDom.find("#originalPriceError").html(
						"<font color='red'> * </font>");
			}

			if (currentPrice == '') {
				iframeDom.find("#currentPriceError").html(
						"<font color='red'> * 现价不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#currentPriceError").html(
						"<font color='red'> * </font>");
			}

			if (isNaN(currentPrice)) {
				iframeDom.find("#currentPriceError").html(
						"<font color='red'> * 请填写数字类型！</font>");
				return false;
			} else {
				iframeDom.find("#currentPriceError").html(
						"<font color='red'> * </font>");
			}

			if (destination == '') {
				iframeDom.find("#placeError").html(
						"<font color='red'> * 目的地不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#placeError").html(
						"<font color='red'> * </font>");
			}

			if (place == '') {
				iframeDom.find("#placeError").html(
						"<font color='red'> * 目的地不能为空！</font>");
				return false;
			} else {
				iframeDom.find("#placeError").html(
						"<font color='red'> * </font>");
			}

			$.ajax({
				type : 'POST',
				url : "${ctx}/product/save/",
				data : $(inputForm).serialize(),
				success : function(data) {
					if (data.msg != "success") {
						alert(data.msg);
						return false;
					} else {
						top.$.jBox.close(true);
						window.location.href = "${ctx}/product/list";
					}
				},
				error : function(msg) {
					debugger;
					alert(msg);
				}
			});
			return false;

		},
		loaded : function(h) {
			$(".jbox-content", top.document).css("overflow-y", "hidden");
		}
	});
}

function up() {
	var f = $("input[name='ids']").is(':checked');
	if (f) {
		$.each($("table .td_ground"), function() {
			var obj = $(this);
			var up = obj.prev();
			if ($(up).has("td").size() == 0) {
				alert("顶级元素不能上移");
				return;
			}
			$(obj).after(up);
			resetSort();
		});
	} else {
		alert("请选中一行数据");
	}
}

function down() {
	var f = $("input[name='ids']").is(':checked');

	if (f) {
		$.each($("table .td_ground"), function() {
			var obj = $(this);
			var down = obj.next();

			if ($(down).has("td").size() == 0) {
				alert("最尾部了");
				return;
			}
			$(down).after(obj);
			resetSort();
		});
	} else {
		alert("请选中一行数据");
	}
}

$(function() {
	$("table tr").click(function() {
		if ($(this).has("td").size() > 0) {
			$(this).addClass('td_ground');
			$(this).children("td").find("input :eq(0)").attr("checked", true);
			check($(this).children("td").find("input :eq(1)"));
			$(this).siblings().removeClass("td_ground");
		}
	});
});

function check(gg) {

	var shelveFlag = $(gg).val();

	if (shelveFlag == 0) {
		$("#goup").attr("disabled", true);
		$("#godown").attr("disabled", false);
	} else {
		$("#goup").attr("disabled", false);
		$("#godown").attr("disabled", true);
	}
}

function resetSort() {
	$("table tbody tr").each(function(index, obj) {
		$($(obj).find('td :eq(1)')).text(index + 1);
	})
}

function release() {
	var releaseFlag = confirm("确认发布吗？");
	if (releaseFlag) {
		var arr = [];
		$("table tbody tr").each(function(index, obj) {
			var data = $($(obj).find('td :eq(0)')).attr('suffix');
			arr.push(data);
		});
		if (arr.length <= 0) {
			alert("发布的数据为空,请稍后再试！");
			return;
		}
		var str = "";
		for (var i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				str = str + "param=" + arr[i] + "&";
			} else {
				str = str + "param=" + arr[i]
			}
		}
		window.location.href = "${ctx}/product/release?" + str;
		console.info(str);
	}
}

function getCity(destination) {// 联动加载地点列表
	$("#place").empty();// 清空下拉框

	if (null == destination || destination == "") {
		var option = $("<option></option>").text("所有").val("");
		$("#place").append(option);
		return;
	}

	$.ajax({
		type : "get",
		url : "${ctx}/destination/listForPlaceJson",
		dataType : "JSON",
		data : {
			"parentId" : destination
		}, // 参数值
		success : function(list) {
			// 遍历
			$.each(list, function(i, p) {
				var option = $("<option></option>").text(p.name).val(p.id);
				$("#place").append(option);
			});
		}
	})

}

function formReset() {
	var option = $("<option></option>").text("所有").val("");

	$("#destination").empty();// 清空下拉框
	$("#destination").append(option);

	$.ajax({
		type : "get",
		url : "${ctx}/destination/listForTypeJson",
		dataType : "JSON",
		success : function(list) {
			// 遍历
			$.each(list, function(i, p) {
				var option = $("<option></option>").text(p.name).val(p.id);
				$("#destination").append(option);
			});
		}
	})

	var option = $("<option></option>").text("所有").val("");
	$("#place").empty();// 清空下拉框
	$("#place").append(option);

	var option1 = $("<option></option>").text("全部").val("");
	var option2 = $("<option></option>").text("上架").val(0);
	var option3 = $("<option></option>").text("下架").val(1);
	$("#shelveFlag").empty();// 清空下拉框
	$("#shelveFlag").append(option1);
	$("#shelveFlag").append(option2);
	$("#shelveFlag").append(option3);

	var option = $("<option></option>").text("全部").val("");
	$("#category").empty();// 清空下拉框
	$("#category").append(option);

	$.ajax({
		type : "get",
		url : "${ctx}/product/productCategoryList",
		dataType : "JSON",
		success : function(list) {
			// 遍历
			$.each(list, function(i, p) {
				var option = $("<option></option>").text(p.name).val(p.id);
				$("#category").append(option);
			});
		}
	})

	$("#name").val("");
};

