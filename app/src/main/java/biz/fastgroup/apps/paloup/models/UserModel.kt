package biz.fastgroup.apps.paloup.models

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Observable



/**
 * Author: gronel
 * Created by: ModelGenerator on 27/12/2016
 */
open class UserModel : RealmObject() {

    @PrimaryKey
     var userId: Int = 0    // 65122
     var username: String? = null    // rjgonzales@fast
     var userrole: String? = null    // ADMIN
     var lastname: String? = null    // GONZALES
     var firstname: String? = null    // RONEL
     var middlename: String? = null    // JAIME
     var usertype: String? = null    // E
     var emailaddress: String? = null    // rjgonzales@fast
     var mobileno: String? = null    // 09335907882
     var hashcode: String? = null    // 82846935d5578f8
}