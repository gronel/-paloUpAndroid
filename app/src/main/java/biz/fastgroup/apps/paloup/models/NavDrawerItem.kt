package biz.fastgroup.apps.paloup.models

/**
 * Created by gronel on 22/04/2016.
 */
class NavDrawerItem {
    var isShowNotify: Boolean = false
    var title: String? = null


    constructor()

    constructor(showNotify: Boolean, title: String) {
        this.isShowNotify = showNotify
        this.title = title
    }
}
