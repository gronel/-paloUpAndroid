package biz.fastgroup.apps.paloup.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Smoke on 28/12/2017.
 */


/**
 * Author: Smoke
 * Created by: ModelGenerator on 12/07/2017
 */
open class LoginDetails : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var status: String? = null    // SUCCESS
    var message: String? = null
    var intParam1: Int = 0    // 0
    var intParam2: Int = 0    // 0
    var stringParam1: String? = null    // hoCzB/s6mlSEesN
    var stringParam2: String? = null    // 82846935d5578f8
    var objParam1: UserModel? = null
}