
var listView = function() {

    var dataTableSelector;
    var config = {
    		toolbar: "#tb",
    		fitColumns: true,
    		fit: true,
    		pagination: true,
    		rownumbers: true
    	};

    var table = null;
    function initView() {
        table = $('#dg').datagrid(config);
        initSearch();
    }

    function initSearch() {
         $(".search input[type=text]").keypress(function (event) {

                            var key = event.which;
                            if (key == 13) {
                                if(event.preventDefault) event.preventDefault();  //标准技术
                                if(event.returnValue) event.returnValue = false;  //IE
                                $(".searchButton a").click();

                                  return false;   //用于处理使用对象属性注册的处理程序
                            }
                        });
       

        $(".searchButton a").click(function() {
            var params = {};
            $(".search").find("input[type=text]").each(function() {
                var key = $(this).attr("id");
                var value = $(this).val();
                params[key] = value;
            });
            $(".search").find("input[type=hidden]").each(function() {
                var key = $(this).attr("id");
                var value = $(this).val();
                if (!key) {
                    key = $(this).attr("name");
                }
                params[key] = value;
            });

            $(".search").find(".easyui-datebox").each(function() {
                var key = $(this).attr("id");
                var value = $(this).datetimebox("getValue")
                params[key] = value;
            });

            $(".search").find(".easyui-combobox").each(function() {
                var key = $(this).attr("id");
                var value = $(this).combobox("getValue")
                params[key] = value;
            });


            $("#dg").datagrid('load',{
                search:  JSON.stringify(params)
            });
        });
    }


    return {
        init: function(userConfig) {
            $.extend(config, userConfig);
            initView();
        },
        api: function() {
            return table;
        },
        dataList: function() {
            return dataList;
        },
        getRow: function(index){
            return table.datagrid("getRows")[index];
        }
    }
}();

function getItemButton(index, name, fun) {
    return "<a href='#' onclick='" + fun +"(" + index +")' style='margin: 0 4px;'>" + name + "</a>" ;
}