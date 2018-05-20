var loginUrl = function () {
    return "/api/user/login";
};
var logoutUrl = function () {
    return "/api/user/logout"
};


function UserViewModel() {
    var self = this;

    self.userName = ko.observable();
    self.password = ko.observable();
    self.isLoggedIn = ko.observable(false);


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

    self.init = function () {
        self.checkIfLogged();
    };

    self.init();

}

ko.applyBindings(new UserViewModel());
