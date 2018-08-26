$(function () {
	 $.ajax({
        type: "GET",
        url: "/JSGrid/ManageCars"
    }).done(function () {

        $("#jsGrid").jsGrid({
            height: "400",
            width: "500",
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            pageSize: 10,
            controller: {
                loadData: function (filter) {
                    return $.ajax({
                        type: "GET",
                        url: "/JSGrid/ManageCars",
                        data: filter
                    });
                },
                insertItem: function (item) {
                    return $.ajax({
                        type: "POST",
                        url: "/JSGrid/ManageCars",
                        data: item
                    });
                },
                updateItem: function (item) {
                    return $.ajax({
                        type: "PUT",
                        url: "/JSGrid/ManageCars",
                        data: item
                    });
                },
                deleteItem: function (item) {
                    return $.ajax({
                        type: "DELETE",
                        url: "/JSGrid/ManageCars",
                        data: item
                    });
                }
            },
            fields: [
                {name: "NAME", title: "Name", type: "text", width: 60},
                {name: "PRICE", title: "Price", type: "text",  width: 50},
                {type: "control"}
            ]
        });

    });
});