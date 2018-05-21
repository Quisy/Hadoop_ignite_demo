var loginUrl = function () {
    return "/api/user/login";
};
var logoutUrl = function () {
    return "/api/user/logout"
};

var getDataUrl = function() {
    return "http://192.168.5.11:50075/webhdfs/v1/user/vagrant/output/part-r-00000?op=OPEN&user.name=vagrant&namenoderpcaddress=hadoop-master:8020&offset=0";
};


function UserViewModel() {
    var self = this;

    self.userName = ko.observable();
    self.password = ko.observable();
    self.isLoggedIn = ko.observable(false);
    self.data = ko.observable("");


    self.loginUser = function () {
        console.log("login");
        var jsonData = JSON.stringify({"userName": self.userName(), "password": self.password()});
        console.log(jsonData);
        $.ajax({
            url: loginUrl(),
            type: "POST",
            data: jsonData,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.isLoggedIn(true);
            }
        });
    };

    self.logoutUser = function () {

        $.post(logoutUrl(), function (data) {
            self.isLoggedIn(false);
        });
    };

    self.checkIfLogged = function () {
        console.log("check");
        $.get(loginUrl(), function (data) {
            console.log(data);
            self.isLoggedIn(data);
        });
    };

    self.getData = function() {
        $.get(getDataUrl(), function (data) {
            self.data(data);
        });
    };

    self.init = function () {
        self.checkIfLogged();
        self.getData();
    };

    self.init();

}

ko.applyBindings(new UserViewModel());
