function fieldValidate() {
    var result = true;
    var name = $('#name').val();
    var login = $('#login').val();
    var password = $('#password').val();
    var email = $('#email').val();
    var message = '';
    if (name == '') {
        result = false;
        message += $('#name').attr('placeholder') + '\n';
    }
    if (login == '') {
        result = false;
        message += $('#login').attr('placeholder') + '\n';
    }
    if (password == '') {
        result = false;
        message += $('#password').attr('placeholder') + '\n';
    }
    if (email == '') {
        result = false;
        message += $('#email').attr('placeholder') + '\n';
    }
    if (!result) {
        alert(message);
    }

    return result;
}




