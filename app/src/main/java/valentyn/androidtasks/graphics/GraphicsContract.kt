package valentyn.androidtasks.graphics

import valentyn.androidtasks.BaseContract

interface GraphicsContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View)

        fun saveDrawing()
    }

    interface View : BaseContract.View {

        fun setDrawingView()

        fun setResult()

        fun onFinish()
    }


}