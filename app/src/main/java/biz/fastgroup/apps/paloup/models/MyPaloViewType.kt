package biz.fastgroup.apps.paloup.models


/**
 * Created by gronel on 15/04/2017.
 */

open class MyPaloViewType {
    private var iType: Int = 0
    open var strType: String? = null
    private var iForme: Int = 0
    private var iForothers: Int = 0
    private var iOpen: Int = 0
    private var iPark: Int = 0
    private var iClose: Int = 0
    private var iDrop: Int = 0

    open fun getiType(): Int {
        return iType
    }

    open fun setiType(iType: Int) {
        this.iType = iType
    }

    open fun getiForme(): Int {
        return iForme
    }

    open fun setiForme(iForme: Int) {
        this.iForme = iForme
    }

    open fun getiForothers(): Int {
        return iForothers
    }

    open fun setiForothers(iForothers: Int) {
        this.iForothers = iForothers
    }

    open fun getiOpen(): Int {
        return iOpen
    }

    open fun setiOpen(iOpen: Int) {
        this.iOpen = iOpen
    }

    open fun getiPark(): Int {
        return iPark
    }

    open fun setiPark(iPark: Int) {
        this.iPark = iPark
    }

    open fun getiClose(): Int {
        return iClose
    }

    open fun setiClose(iClose: Int) {
        this.iClose = iClose
    }

    open fun getiDrop(): Int {
        return iDrop
    }

    open fun setiDrop(iDrop: Int) {
        this.iDrop = iDrop
    }


}
