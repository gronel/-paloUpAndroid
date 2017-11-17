package biz.fastgroup.apps.paloup.models

/**
 * Created by gronel on 12/04/2017.
 */

class LoginCredentials {
    var loginname: String? = null
    var password: String? = null

    constructor() {

    }

    constructor(loginname: String, password: String) {
        this.loginname = loginname
        this.password = password
    }
}
