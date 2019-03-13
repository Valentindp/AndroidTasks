package valentyn.androidtasks.graphics

class FingerPaintPresenter : GraphicsContract.Presenter {

    private var view: GraphicsContract.View? = null

    override fun onAttach(view: GraphicsContract.View) {
        this.view = view
        this.view?.setDrawingView()
    }

    override fun saveDrawing() {
        view?.apply {
            setResult()
            onFinish()
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun start() {}
}