package organize.monkeyapp.event

import organize.monkeyapp.network.models.MonkeyModel

/**
 * Created by organize on 11/12/2017.
 */
class ShowInformationEvent {
    var monkeyModel : MonkeyModel? = null

    constructor(monkeyModel: MonkeyModel?) {
        this.monkeyModel = monkeyModel
    }
}