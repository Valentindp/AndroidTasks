package valentyn.androidtasks.graphics

import valentyn.androidtasks.BaseContract

interface GraphicsContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View)

        fun saveDrawing()
    }

    interface View : BaseContract.View {

        fun setResult()

        fun setDrawingView()

        fun onFinish()
    }


}